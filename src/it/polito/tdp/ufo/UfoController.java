/**
 * Sample Skeleton for 'Ufo.fxml' Controller Class
 */

package it.polito.tdp.ufo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.model.AnnoCount;
import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class UfoController {

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

	Model model;

	@FXML
	void handleAnalizza(ActionEvent event) {

		try {
			txtResult.clear();
			String stato = boxStato.getValue();
			if (stato == null) {
				txtResult.setText("Selezionare uno stato!");
				return;
			} else {
				List<String> successivi = model.getStatiSuccessivi(stato);
				List<String> precedenti = model.getStatiPrecedenti(stato);
				List<String> raggiungibili = model.getStatiRaggiungibili(stato);
				
				txtResult.appendText("Stati SUCCESSIVI:\n");
				txtResult.appendText(successivi.toString()+"\n");
				
				txtResult.appendText("\nStati PRECEDENTI:\n");
				txtResult.appendText(precedenti.toString()+"\n");
				
				txtResult.appendText("\nStati RAGGIUNGIBILI:\n");
				txtResult.appendText(raggiungibili.toString()+"\n");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			txtResult.setText("Errore di connessione al DB!");
		}

	}

	@FXML
	void handleAvvistamenti(ActionEvent event) {
		try {
			boxStato.getItems().clear();
			txtResult.clear();

			AnnoCount ac = boxAnno.getValue();
			if (ac == null) {
				txtResult.setText("Selezionare un anno!");
				return;
			} else {
				model.creaGrafo(boxAnno.getValue().getAnno());
				txtResult.setText("Grafo creato!");
				boxStato.setDisable(false);
				boxStato.getItems().addAll(model.getStates(boxAnno.getValue().getAnno()));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			txtResult.setText("Errore di connessione al DB!");
		}

	}

	@FXML
	void handleSequenza(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Ufo.fxml'.";
		assert boxStato != null : "fx:id=\"boxStato\" was not injected: check your FXML file 'Ufo.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Ufo.fxml'.";
		boxStato.setDisable(true);

	}

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getYears());
	}
}
