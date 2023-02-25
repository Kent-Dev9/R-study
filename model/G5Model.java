package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class G5Model {
	
	private final SimpleStringProperty kamokusyubetu1;
	private final SimpleStringProperty kamokusyubetu2;
	
	public G5Model(String kamokusyubetu1, String kamokusyubetu2) {
		super();
		this.kamokusyubetu1 = new SimpleStringProperty(kamokusyubetu1);
		this.kamokusyubetu2 = new SimpleStringProperty(kamokusyubetu2);
	}
	public StringProperty kamokusyubetu1Property(){return kamokusyubetu1;}
	public StringProperty kamokusyubetu2Property(){return kamokusyubetu2;}
	public String getKamokusyubetu1() {return kamokusyubetu1.get();}
	public String getKamokusyubetu2() {return kamokusyubetu2.get();}
	public void setKamokusyubetu1(String kamokusyubetu1) {this.kamokusyubetu1.set(kamokusyubetu1);}
	public void setKamokusyubetu2(String kamokusyubetu2) {this.kamokusyubetu2.set(kamokusyubetu2);}
	
}
