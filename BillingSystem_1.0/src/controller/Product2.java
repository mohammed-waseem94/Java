package controller;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product2 {
	final SimpleIntegerProperty sno;
	final SimpleStringProperty desc;
	final SimpleDoubleProperty mwt;
	final SimpleDoubleProperty swt;
	final SimpleDoubleProperty prc;
	
public Product2(int sno, String name,Double mwt,Double swt,Double prc) {
	super();
	this.sno = new SimpleIntegerProperty(sno);
	this.desc = new SimpleStringProperty(name);
	this.mwt=new SimpleDoubleProperty(mwt);
	this.swt=new SimpleDoubleProperty(swt);
	this.prc=new SimpleDoubleProperty(prc);
}
public int getSno() {
	return sno.get();
}
public String getDesc() {
	return desc.get();
}

public Double getMwt() {
	return mwt.get();
}
public Double getSwt() {
	return swt.get();
}
public Double getPrc() {
	return prc.get();
}
}
