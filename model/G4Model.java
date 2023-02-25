package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class G4Model {
	
	private final SimpleStringProperty G4hidukeColumn;
	private final SimpleStringProperty G4youbiColumn;
	private final SimpleStringProperty G4kamokusyubetuColumn;
	private final SimpleStringProperty G4jikanColumn;
	private final SimpleStringProperty G4tasseidoColumn;
	private final SimpleStringProperty G4bikouColumn;
	
	public G4Model(String G4hiduke, String G4youbi, String G4kamokusyubetu, String G4jikan, String G4tasseido, String G4bikouColumn) {
		super();
		this.G4hidukeColumn = new SimpleStringProperty(G4hiduke);
		this.G4youbiColumn = new SimpleStringProperty(G4youbi);
		this.G4kamokusyubetuColumn = new SimpleStringProperty(G4kamokusyubetu);
		this.G4jikanColumn = new SimpleStringProperty(G4jikan);
		this.G4tasseidoColumn = new SimpleStringProperty(G4tasseido);
		this.G4bikouColumn = new SimpleStringProperty(G4bikouColumn);
	}
	
	//ゲッター１
	public StringProperty G4hidukeColumnProperty(){return G4hidukeColumn;}
	public StringProperty G4youbiColumnProperty(){return G4youbiColumn;}
	public StringProperty G4kamokusyubetuColumnProperty(){return G4kamokusyubetuColumn;}
	public StringProperty G4jikanColumnProperty(){return G4jikanColumn;}
	public StringProperty G4tasseidoColumnProperty(){return G4tasseidoColumn;}
	public StringProperty G4bikouColumnProperty(){return G4bikouColumn;}
	
	//ゲッター2
	public String getG4hidukeColumn() {return G4hidukeColumn.get();}
	public String getG4youbiCnolumn() {return G4youbiColumn.get();}
	public String getG4kamokusyubetuColumn() {return G4kamokusyubetuColumn.get();}
	public String getG4jikanColumn() {return G4jikanColumn.get();}
	public String getG4tasseidoColumn() {return G4tasseidoColumn.get();}
	public String getG4bikouColumn() {return G4bikouColumn.get();}
	
	//セッター
	public void setG4hidukeColumn(String G4hidukeColumn) {this.G4hidukeColumn.set(G4hidukeColumn);}
	public void setG4youbiColumn(String G4youbiColumn) {this.G4youbiColumn.set(G4youbiColumn);}
	public void setG4kamokusyubetuColumn(String G4kamokusyubetuColumn) {this.G4kamokusyubetuColumn.set(G4kamokusyubetuColumn);}
	public void setG4jikanColumn(String G4jikanColumn) {this.G4jikanColumn.set(G4jikanColumn);}
	public void setG4tasseidoColumn(String G4tasseidoColumn) {this.G4tasseidoColumn.set(G4tasseidoColumn);}
	public void setG4bikouColumn(String G4bikouColumn) {this.G4bikouColumn.set(G4bikouColumn);}
	
}