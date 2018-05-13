package controller;

import javafx.beans.property.SimpleStringProperty;

public class Val{

	final SimpleStringProperty metal;
	final SimpleStringProperty rate;
	public Val(String m, String r) {
		// TODO Auto-generated constructor stub
		metal=new SimpleStringProperty(m);
		rate=new SimpleStringProperty(r);
	}
	public String getMetal() {
		return metal.get();
	}
	public String getRate() {
		return rate.get();
	}
}