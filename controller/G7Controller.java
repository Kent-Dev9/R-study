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

public class G7Controller {
	
	@FXML
	TextField oldPasswd;
	
	@FXML
	TextField newPasswd;
	
	@FXML
	TextField TWnewPasswd;
	
	@FXML
	Label msgLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void ChangeMenyusentakuG(ActionEvent event) throws IOException {

		  System.out.println("login!");
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen2.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	
	public void passwdHenkou() {
		//Lはローカル変数を意味して付与しています
		String Loldpasswd = oldPasswd.getText();
		String Lnewpasswd = newPasswd.getText();
		String Ltwnewpasswd = TWnewPasswd.getText();
		
		Kinko kinko = new Kinko();
		String checkResult = kinko.passwdHenkouCheck(Loldpasswd, Lnewpasswd, Ltwnewpasswd);
		if (checkResult.equals("OK")) {
			String updateResult = kinko.passwdHenkou(Lnewpasswd);
			if (updateResult.equals("success")) System.out.println("successが返ってきた");
			else System.out.println("success以外が返ってきた");
		}
		else {
			msgLabel.setText(checkResult);
		}
		kinko = null;//ガーベジコレクション対象にするため、nullを代入する
	}

}
