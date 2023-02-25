package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.G3Model;
import model.G4Model;
import sub.SubMakeG3KScolumn;
import sub.SubMakeG4DataOfTable;
import sub.SubMakeG4kamokuList;

public class G4Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private static Label kamokuLabelOutOfInitialize;
	private static SplitMenuButton kamokumenuOutOfInitialize;
	private static Label sougoujikanLabelOutOfInitialize;
	private static Label tasseidoLanelOutOfInitialize;
	private static TableView G4KirokuTableStatic;
	static LocalDate today = LocalDate.now();
	@FXML private  Label kamokuLabel;
	@FXML private  Label sougoujikanLabel;
	@FXML private  Label tasseidoLabel;
	@FXML private  SplitMenuButton kamokumenu;
	@FXML private  Button sengetu;
	@FXML private  Button raigetu;
	@FXML private  Button tougetu;
	@FXML private TableColumn G4kamokusyubetuColumn;
	@FXML private TableColumn G4jikanColumn;
	@FXML private TableColumn G4tasseidoColumn;
	@FXML private TableColumn G4bikouColumn;
	@FXML private TableColumn G4hidukeColumn;
	@FXML private TableColumn G4youbiColumn;
	@FXML private TableView G4KirokuTable;
	private static int syokaihGhyoujiFLG = 0;
	private ObservableList<G4Model> data;
	
	public static void setsyokaihGhyoujiFLG(int i) {
		syokaihGhyoujiFLG = i;
		
	}
	
	public static void settoday(LocalDate siteibi) {
		today = siteibi;
	}

	public void initialize() {
		initialize(today);
	}
	
	
	public void initialize(LocalDate today2) {
		syokaihGhyoujiFLG ++;		
		
		G4KirokuTableStatic = this.G4KirokuTable;
		kamokuLabelOutOfInitialize = this.kamokuLabel;
		sougoujikanLabelOutOfInitialize = this.sougoujikanLabel;
		tasseidoLanelOutOfInitialize = this.tasseidoLabel;
		
		G4KirokuTable.setRowFactory( tv -> {
			TableRow<G4Model> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			int index = getIndex();
			try {
				G6Controller.settoday(today.withDayOfMonth(1).plusDays(index));
				ChangeKirokuG(event);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			}
			});
			return row ;
	     	});
		
		//月を取得してLabelに表示する
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM月");
		sengetu.setText("◁" + today2.plusMonths(-1).format(fmt));
		raigetu.setText(today2.plusMonths(1).format(fmt) + "▷");
		tougetu.setText(today2.format(fmt));
		
		//観測可能なリスト、dataをTableの値としてセットする
		data = FXCollections.observableArrayList();
		G4KirokuTable.itemsProperty().setValue(data);
		G4KirokuTable.setItems(data);
		
		//カラムのセル"の値"を作る。また、中に入れる事ができるものを<>で制限してるのかな？
		G4hidukeColumn.setCellValueFactory(new PropertyValueFactory<G4Model, String>("G4hidukeColumn"));
		G4youbiColumn.setCellValueFactory(new PropertyValueFactory<G4Model, String>("G4youbiColumn"));
		G4kamokusyubetuColumn.setCellValueFactory(new PropertyValueFactory<G4Model, String>("G4kamokusyubetuColumn"));
		G4jikanColumn.setCellValueFactory(new PropertyValueFactory<G4Model, String>("G4jikanColumn"));
		G4tasseidoColumn.setCellValueFactory(new PropertyValueFactory<G4Model, String>("G4tasseidoColumn"));
		G4bikouColumn.setCellValueFactory(new PropertyValueFactory<G4Model, String>("G4bikouColumn"));
		
		SubMakeG4DataOfTable.makeData(data, today2);
		
		if (syokaihGhyoujiFLG == 2) {
			List menuItemList = new ArrayList();
			String[] strHIDARI = DAO.getG5tableKShidari();
			String[] strMIGI   = DAO.getG5tableKSmigi();
			menuItemList = SubMakeG4kamokuList.make(menuItemList, strHIDARI, strMIGI);
			kamokumenu.getItems().addAll(menuItemList);
		} 
		//menuItemList.get(0).setOnAction(e-> 
		
	}
	
	public void ChangeMenyusentakuG(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen2.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	public void ChangeSyousaiG(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen6.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	
	public void ChangeKirokuG(MouseEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen6.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		
	}
	
	public void goSengetuAction() {
    	today = today.plusMonths(-1);
    	initialize(today);
	}
	
	public void goRaigetuAction() {
    	today = today.plusMonths(1);
    	initialize(today);
	}
	
	public void kamokusentaku() {

	}
	
	public static int getIndex() {
		return G4KirokuTableStatic.getSelectionModel().selectedIndexProperty().get();
	}

	public static void setkamokuLabel(String text) {
		kamokuLabelOutOfInitialize.setText(text);
		Double sougoujikan = DAO.getsougoujikan(today,text);
		Double tasseido = DAO.gettasseidoheikin(today,text);
		Double Roundtasseido = (double)Math.round(tasseido * 10)/10;
		sougoujikanLabelOutOfInitialize.setText(sougoujikan.toString() + " （ｈ）");
		tasseidoLanelOutOfInitialize.setText(Roundtasseido.toString());
		
	}
	
}