package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class G3Model {
	
	private final SimpleStringProperty G3kamokusyubetuColumn;
	private final SimpleStringProperty G3jikanColumn;
	private final SimpleStringProperty G3tasseidoColumn;
	private final SimpleStringProperty G3bikouColumn;
	
	public G3Model(String G3kamokusyubetu, String G3jikan, String G3tasseido, String G3bikouColumn) {
		super();
		this.G3kamokusyubetuColumn = new SimpleStringProperty(G3kamokusyubetu);
		this.G3jikanColumn = new SimpleStringProperty(G3jikan);
		this.G3tasseidoColumn = new SimpleStringProperty(G3tasseido);
		this.G3bikouColumn = new SimpleStringProperty(G3bikouColumn);
	}
	
	//ゲッター１
	public StringProperty G3kamokusyubetuColumnProperty(){return G3kamokusyubetuColumn;}
	public StringProperty G3jikanColumnProperty(){return G3jikanColumn;}
	public StringProperty G3tasseidoColumnProperty(){return G3tasseidoColumn;}
	public StringProperty G3bikouColumnProperty(){return G3bikouColumn;}
	
	//ゲッター2
	public String getG3kamokusyubetuColumn() {return G3kamokusyubetuColumn.get();}
	public String getG3jikanColumn() {return G3jikanColumn.get();}
	public String getG3tasseidoColumn() {return G3tasseidoColumn.get();}
	public String getG3bikouColumn() {return G3bikouColumn.get();}
	
	//セッター
	public void setG3kamokusyubetuColumn(String G3kamokusyubetuColumn) {this.G3kamokusyubetuColumn.set(G3kamokusyubetuColumn);}
	public void setG3jikanColumn(String G3jikanColumn) {this.G3jikanColumn.set(G3jikanColumn);}
	public void setG3tasseidoColumn(String G3tasseidoColumn) {this.G3tasseidoColumn.set(G3tasseidoColumn);}
	public void setG3bikouColumn(String G3bikouColumn) {this.G3bikouColumn.set(G3bikouColumn);}
	
}