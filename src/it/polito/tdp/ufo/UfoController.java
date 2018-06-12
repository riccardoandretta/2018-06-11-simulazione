/**
 * Sample Skeleton for 'Ufo.fxml' Controller Class
 */

package it.polito.tdp.ufo;

import java.net.URL;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.model.AnnoCount;
import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class UfoController {
	
	private Model model ;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<AnnoCount> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxStato"
    private ComboBox<String> boxStato; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleAnalizza(ActionEvent event) {
    	String stato = boxStato.getValue() ;
    	if(stato==null) {
    		txtResult.appendText("ERRORE: devi selezionare uno stato\n");
    		return ;
    	}
    	
    	List<String> successivi = model.getStatiSuccessivi(stato) ;
    	List<String> precedenti = model.getStatiPrecedenti(stato) ;
    	List<String> raggiungibili = model.getStatiRaggiungibili(stato) ;
    	
    	txtResult.appendText("\nStato di partenza: "+stato+"\n");

    	txtResult.appendText("Stati SUCCESSIVI\n");
    	txtResult.appendText(successivi.toString()+"\n");
    	txtResult.appendText("Stati PRECEDENTI\n");
    	txtResult.appendText(precedenti.toString()+"\n");
    	txtResult.appendText("Stati RAGGIUNGIBILI\n");
    	txtResult.appendText(raggiungibili.toString()+"\n");
    }

    @FXML
    void handleAvvistamenti(ActionEvent event) {
    	AnnoCount anno = boxAnno.getValue() ;
    	if(anno==null) {
    		txtResult.appendText("ERRORE: selezionare un anno\n");
    		return ;
    	}
    	
    	model.creaGrafo(anno.getAnno());
    	
    	boxStato.getItems().clear();
    	boxStato.getItems().addAll(model.getStati()) ;
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	
    	String stato = boxStato.getValue() ;
    	if(stato==null) {
    		txtResult.appendText("ERRORE: devi selezionare uno stato\n");
    		return ;
    	}

    	List<String> sequenza = model.getPercorsoMassimo(stato) ;
    	txtResult.appendText("\nStato di partenza: "+stato+"\n");
    	txtResult.appendText("Percorso massimo: "+sequenza+"\n") ;
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert boxStato != null : "fx:id=\"boxStato\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Ufo.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model = m ;
    	boxAnno.getItems().addAll(model.getAnniAvvistamenti()) ;
    }
}
