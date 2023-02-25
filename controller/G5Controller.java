package controller;

import java.io.IOException;

import application.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.G5Model;

public class G5Controller {
	
	@FXML private TableView   KSTable;
	@FXML private TableColumn KSColumn1;
	@FXML private TableColumn KSColumn2;
	private ObservableList<G5Model> data;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void initialize() {
		//テーブル、カラムを編集可能にする
		KSTable.setEditable(true);
		KSColumn1.setEditable(true);
		KSColumn2.setEditable(true);
		
		//観測可能なリスト、dataをTableの値としてセットする
		data = FXCollections.observableArrayList();
		KSTable.itemsProperty().setValue(data);
		KSTable.setItems(data);
		
		KSColumn1.setCellValueFactory(new PropertyValueFactory<G5Model, String>("kamokusyubetu1"));
		KSColumn2.setCellValueFactory(new PropertyValueFactory<G5Model, String>("kamokusyubetu2"));
		//セルにTextFieldを仕込む
		KSColumn1.setCellFactory(TextFieldTableCell.forTableColumn());
		KSColumn2.setCellFactory(TextFieldTableCell.forTableColumn()); 
		
		//データベースから値を取得し、配列に代入する
		String[] strHIDARI = DAO.getG5tableKShidari();
		String[] strMIGI   = DAO.getG5tableKSmigi();
		
		
		//テーブルにデータを追加
		data.addAll( new G5Model(strHIDARI[0], strMIGI[0]) );
		data.addAll( new G5Model(strHIDARI[1], strMIGI[1]) );
		data.addAll( new G5Model(strHIDARI[2], strMIGI[2]) );
		data.addAll( new G5Model(strHIDARI[3], strMIGI[3]) );
		data.addAll( new G5Model(strHIDARI[4], strMIGI[4]) );
		data.addAll( new G5Model(strHIDARI[5], strMIGI[5]) );
		data.addAll( new G5Model(strHIDARI[6], strMIGI[6]) );
	}
	
	public void KStouroku(ActionEvent event) {
		for(int i = 0; i < 7; i++) {
			String str = data.get(i).getKamokusyubetu1();
			DAO.setg5tableKShidari(str, i+1);
		}
		for(int i = 0; i < 7; i++) {
			String str = data.get(i).getKamokusyubetu2();
			DAO.setg5tableKSmigi(str, i+1);
		}
		//最後にダイアログを表示する。
		Alert alert = new Alert(AlertType.NONE);
        alert.setAlertType(AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setContentText("科目種別の更新が正常に終了しました");
        alert.show();
	}
	
	public void ChangeKirokuG(ActionEvent event) throws IOException {

		  
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen3.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}

}
