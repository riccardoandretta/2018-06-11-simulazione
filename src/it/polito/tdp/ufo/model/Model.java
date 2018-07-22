package it.polito.tdp.ufo.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.ufo.db.SightingsDAO;

public class Model {

	private SightingsDAO sDAO;
	private List<AnnoCount> annoCount;
	private Graph<String, DefaultEdge> grafo;
	private List<String> states;

	public Model() {
		this.sDAO = new SightingsDAO();
	}

	public List<AnnoCount> getYears() {
		this.annoCount = sDAO.getYears();
		return annoCount;
	}

	public void creaGrafo(int anno) {
		this.grafo = new SimpleDirectedGraph<>(DefaultEdge.class);

		states = this.sDAO.getStatesByYear(anno);

		Graphs.addAllVertices(grafo, states);

		List<CoppiaStati> coppie = sDAO.getCoppiaStatesByYear(anno);
		
		for (CoppiaStati c : coppie) {
			if(grafo.vertexSet().contains(c.getState1()) && grafo.vertexSet().contains(c.getState2())) {
				Graphs.addEdgeWithVertices(grafo, c.getState1(), c.getState2());
			}
		}
		System.out.println("Vertici: "+grafo.vertexSet().size());
		System.out.println("Archi: "+grafo.edgeSet().size());
	}

	public List<String> getStates(int anno) {
		List<String> stati = this.sDAO.getStatesByYear(anno);
		return stati;
	}
	
	public List<String> getStatiPrecedenti(String stato){
		//Dando per scontato che il grafo ci sia e 'stato' ne sia un vertice
		return Graphs.predecessorListOf(grafo, stato);
	}
	
	public List<String> getStatiSuccessivi(String stato){
		//Dando per scontato che il grafo ci sia e 'stato' ne sia un vertice
		return Graphs.successorListOf(grafo, stato);
	}
	
	public List<String> getStatiRaggiungibili(String statoPartenza){
		BreadthFirstIterator<String, DefaultEdge> bfi = new BreadthFirstIterator<>(grafo, statoPartenza);
		
		List<String> raggiungibili = new ArrayList<>();
		
		bfi.hasNext(); // non voglio salvare il primo elemento(statoPartenza)
		while(bfi.hasNext()) {
			raggiungibili.add(bfi.next());
		}
		return raggiungibili;
	}
}
