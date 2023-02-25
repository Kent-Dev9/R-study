package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.G3Model;
import sub.SubMakeG3KScolumn;


public class G3Controller{

	@FXML private TableView   G3KirokuTable;
	@FXML private TableColumn G3kamokusyubetuColumn;
	@FXML private TableColumn G3jikanColumn;
	@FXML private TableColumn G3tasseidoColumn;
	@FXML private TableColumn G3bikouColumn;
	@FXML private Label       hidukeLabel;
	@FXML private Button      goYesterdayB;
	@FXML private Button      goTomorrowB;
    @FXML private TextArea    bikouTextArea;
	private ObservableList<G3Model> data;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	static LocalDate today = LocalDate.now();
	
	public static void settoday(LocalDate siteibi) {
		today = siteibi;
	}
	
	public void ChangeMenyusentakuG(ActionEvent event) throws IOException {

		  System.out.println("login!");
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen2.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	public void ChangeKamokuSTG(ActionEvent event) throws IOException {

		  System.out.println("login!");
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen5.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	
    @FXML
    void goTomorrowAction(ActionEvent event) {
    	System.out.println("goTomorrowAction");
    	today = today.plusDays(1);
    	initialize(today);
    }

    @FXML
    void goYesterdayAction(ActionEvent event) {
    	System.out.println("goYesterdayAction");
    	today = today.plusDays(-1);
    	initialize(today);
    }
    @FXML
    void tourokuAction(ActionEvent event) {
    	DAO.upsertBikouTable(bikouTextArea.getText(), today);
    	for(int i = 0; i < 7; i++) {
        	String KS = data.get(i).getG3kamokusyubetuColumn();
        	String jikan = data.get(i).getG3jikanColumn();
        	String tasseido = data.get(i).getG3tasseidoColumn();
        	if (!(KS.equals(""))) {
        		if (!(jikan.equals(""))) {
        			if (!(tasseido.equals(""))) {
                    	String upsertkey = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "_" + (i+1);
                    	DAO.upsertG3table(KS,jikan,tasseido,upsertkey,i, today);
        			}
        		}
        	}

    	}
		Alert alert = new Alert(AlertType.NONE);
        alert.setAlertType(AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setContentText("登録は正常に終了しました");
        alert.show();
    }
	public void initialize() {
			initialize(today);

	}
	
	private void initialize(LocalDate today2) {
		
		//日付けを取得してLabelに表示する
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM月dd日(E)");
		hidukeLabel.setText(today2.format(fmt)); 
		
		//編集可能にする
		G3KirokuTable.setEditable(true);
		G3kamokusyubetuColumn.setEditable(true);
		G3jikanColumn.setEditable(true);
		G3tasseidoColumn.setEditable(true);
		G3bikouColumn.setEditable(true);
		 
		//観測可能なリスト、dataをTableの値としてセットする
		data = FXCollections.observableArrayList();
		G3KirokuTable.itemsProperty().setValue(data);
		G3KirokuTable.setItems(data);
		
		//カラムのセル"の値"を作る。また、中に入れる事ができるものを<>で制限してるのかな？
		G3kamokusyubetuColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3kamokusyubetuColumn"));
		G3jikanColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3jikanColumn"));
		G3tasseidoColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3tasseidoColumn"));
		G3bikouColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3bikouColumn"));
		
		//G3kamokusyubetuColumnは、G5で登録した科目種別から値を取得するようにしたい
		String[] strHIDARI = DAO.getG5tableKShidari();
		String[] strMIGI   = DAO.getG5tableKSmigi();
		SubMakeG3KScolumn.make(G3kamokusyubetuColumn, strHIDARI, strMIGI);
			
		//G3kamokusyubetuColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		G3jikanColumn.setCellFactory(ComboBoxTableCell.forTableColumn("0.5","1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7"));
		//G3jikanColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		G3tasseidoColumn.setCellFactory(ComboBoxTableCell.forTableColumn("1","2","3","4","5"));
		G3bikouColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		String[][] strg3data  = DAO.getg3table(today2); 
		
		data.addAll( new G3Model(strg3data[0][0], strg3data[0][1], strg3data[0][2], "1") );
		data.addAll( new G3Model(strg3data[1][0], strg3data[1][1], strg3data[1][2], "2") );
		data.addAll( new G3Model(strg3data[2][0], strg3data[2][1], strg3data[2][2], "3") );
		data.addAll( new G3Model(strg3data[3][0], strg3data[3][1], strg3data[3][2], "4") );
		data.addAll( new G3Model(strg3data[4][0], strg3data[4][1], strg3data[4][2], "5") );
		data.addAll( new G3Model(strg3data[5][0], strg3data[5][1], strg3data[5][2], "6") );
		data.addAll( new G3Model(strg3data[6][0], strg3data[6][1], strg3data[6][2], "7") );
		
		bikouTextArea.setText(DAO.getBikou(today2));
		
	}

}
