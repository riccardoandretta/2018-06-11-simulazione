package it.polito.tdp.ufo.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.ufo.db.SightingsDAO;

public class Model {
	
	private List<AnnoCount> anniAvvistamenti ;
	private List<String> stati ;
	private Graph<String, DefaultEdge> graph ;
	
	public List<AnnoCount> getAnniAvvistamenti() {
		SightingsDAO dao = new SightingsDAO();
		this.anniAvvistamenti = dao.getAnni() ;
		return this.anniAvvistamenti ;
	}
	
	
	public void creaGrafo(Year anno) {
		this.graph = new SimpleDirectedGraph<>(DefaultEdge.class) ;
		
		SightingsDAO dao = new SightingsDAO() ;
		
		this.stati = dao.getStati(anno) ;
		
		Graphs.addAllVertices(this.graph, stati) ;
		
		for(String stato1: graph.vertexSet()) {
			for(String stato2: graph.vertexSet()) {
				if(!stato1.equals(stato2)) {
					if(dao.esisteArco(stato1, stato2, anno)) {
						graph.addEdge(stato1, stato2) ;
					}
				}
			}
		}
		
		System.out.println(graph.vertexSet().size()+ " "+ graph.edgeSet().size());
	}
	
	public List<String> getStatiPrecedenti(String stato) {
		return Graphs.predecessorListOf(this.graph, stato) ;
	}
	
	public List<String> getStatiSuccessivi(String stato) {
		return Graphs.successorListOf(this.graph, stato) ;
	}
	
	public List<String> getStatiRaggiungibili(String stato) {
		
		BreadthFirstIterator<String, DefaultEdge> bfv =
				new BreadthFirstIterator<String, DefaultEdge>(this.graph, stato) ;
		
		List<String> raggiungibili = new ArrayList<String>() ;
		bfv.next() ; // non voglio salvare il primo elemento
		while(bfv.hasNext()) {
			raggiungibili.add(bfv.next()) ;
		}
		return raggiungibili ;
	}

	
	public List<String> getStati() {
		return this.stati ;
	}

}
