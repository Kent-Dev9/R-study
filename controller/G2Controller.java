package controller;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class G2Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void ChangeKirokuG(ActionEvent event) throws IOException {
		  G3Controller.settoday(LocalDate.now());
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen3.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	public void ChangeTukibetuitiranG(ActionEvent event) throws IOException {
		  G4Controller.setsyokaihGhyoujiFLG(1);
		  G4Controller.settoday(LocalDate.now());
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen4.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	public void ChangePasshenkouG(ActionEvent event) throws IOException {

		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen7.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	
	public void ChangeLoginG(ActionEvent event) throws IOException {

		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen1.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
}
