package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.G3Model;

public class G6Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	static LocalDate today = LocalDate.now();
	private ObservableList<G3Model> data;
	private LocalDate siteibi;
	
    @FXML
    private TableView G6KirokuTable;
    @FXML
    private TableColumn G6bikouColumn;
    @FXML
    private TableColumn G6jikanColumn;
    @FXML
    private TableColumn G6kamokusyubetuColumn;
    @FXML
    private TableColumn G6tasseidoColumn;
    @FXML
    private Label TitleLabel;
    @FXML
    private TextArea bikouTextArea;
    @FXML
    private Button goTomorrowB;
    @FXML
    private Button goYesterdayB;
    @FXML
    private Label hidukeLabel;
    @FXML
    private AnchorPane myPain;
    @FXML
    private Button tourokuB;

    @FXML
    void ChangeMenyusentakuG(ActionEvent event) {

    }

    @FXML
    void goTomorrowAction(ActionEvent event) {
    	
    	today = today.plusDays(1);
    	initialize(today);
    }

    @FXML
    void goYesterdayAction(ActionEvent event) {
    	
    	today = today.plusDays(-1);
    	initialize(today);
    }

    @FXML
    void ChangeKirokuG(ActionEvent event) throws IOException {
    	  //ここでLabelに表示させている日時を取得する。
    	  G3Controller.settoday(siteibi);
		  
		  root = FXMLLoader.load(getClass().getResource("/GamenFxml/Gamen3.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
    }
	
	public static void settoday(LocalDate siteibi) {
		today = siteibi;
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
	
	public void initialize() {
		initialize(today);

    }
	
	private void initialize(LocalDate today2) {
		//日付けを取得してLabelに表示する
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM月dd日(E)");
		hidukeLabel.setText(today2.format(fmt));
		siteibi = today2;
		 
		//観測可能なリスト、dataをTableの値としてセットする
		data = FXCollections.observableArrayList();
		G6KirokuTable.itemsProperty().setValue(data);
		G6KirokuTable.setItems(data);
		
		//カラムのセル"の値"を作る。また、中に入れる事ができるものを<>で制限してるのかな？
		G6kamokusyubetuColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3kamokusyubetuColumn"));
		G6jikanColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3jikanColumn"));
		G6tasseidoColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3tasseidoColumn"));
		G6bikouColumn.setCellValueFactory(new PropertyValueFactory<G3Model, String>("G3bikouColumn"));

		String[][] strG3data  = DAO.getg3table(today2); 
		
		data.addAll( new G3Model(strG3data[0][0], strG3data[0][1], strG3data[0][2], "1") );
		
		data.addAll( new G3Model(strG3data[1][0], strG3data[1][1], strG3data[1][2], "2") );
		
		data.addAll( new G3Model(strG3data[2][0], strG3data[2][1], strG3data[2][2], "3") );
		data.addAll( new G3Model(strG3data[3][0], strG3data[3][1], strG3data[3][2], "4") );
		data.addAll( new G3Model(strG3data[4][0], strG3data[4][1], strG3data[4][2], "5") );
		data.addAll( new G3Model(strG3data[5][0], strG3data[5][1], strG3data[5][2], "6") );
		data.addAll( new G3Model(strG3data[6][0], strG3data[6][1], strG3data[6][2], "7") );
				


		
		bikouTextArea.setText(DAO.getBikou(today2));
		
	}

}
