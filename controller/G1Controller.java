package controller;

import java.io.IOException;

import application.Kinko;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class G1Controller {

	@FXML
	TextField idField;
	
	@FXML
	TextField passField;
	
	@FXML
	Label TitleLabel_G1;
	
	@FXML
	Label msgLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void login(ActionEvent event) throws IOException {
		String id = idField.getText();
		String pass = passField.getText();
		Kinko kinko = new Kinko();
		String checkResult = kinko.loginCheck(id, pass);
		if (checkResult.equals("OK")) {
			  
			  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen2.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			  stage.setScene(scene);
			  stage.show();
		} else {
			msgLabel.setText(checkResult);
		}
		kinko = null;//ガーベジコレクション対象にするため、nullを代入する
	}
}