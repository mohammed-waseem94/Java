package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BillProduct {
final SimpleIntegerProperty sno;
final SimpleIntegerProperty pcs;
final SimpleStringProperty id;
final SimpleStringProperty name;
final SimpleIntegerProperty crt;
final SimpleDoubleProperty mwt;
final SimpleDoubleProperty swt;
final SimpleDoubleProperty wstg;
final SimpleDoubleProperty prc;

public BillProduct(Integer sno, String id, String name,Integer pcs, Integer carat, Double mwt, Double swt,Double wstg,Double prc) {
	super();
	this.sno = new SimpleIntegerProperty(sno);
	this.id = new SimpleStringProperty(id);
	this.name = new SimpleStringProperty(name);
	this.pcs = new SimpleIntegerProperty(pcs);
	this.crt = new SimpleIntegerProperty(carat);
	this.mwt = new SimpleDoubleProperty(mwt);
	this.swt = new SimpleDoubleProperty(swt);
	this.wstg = new SimpleDoubleProperty(wstg);
	this.prc = new SimpleDoubleProperty(prc);
}

public BillProduct(Integer sno,  String name,Integer pcs, Integer carat, Double mwt, Double swt,Double wstg,Double prc) {
	super();
	this.sno = new SimpleIntegerProperty(sno);
	this.id = null;
	this.name = new SimpleStringProperty(name);
	this.pcs = new SimpleIntegerProperty(pcs);
	this.crt = new SimpleIntegerProperty(carat);
	this.mwt = new SimpleDoubleProperty(mwt);
	this.swt = new SimpleDoubleProperty(swt);
	this.wstg = new SimpleDoubleProperty(wstg);
	this.prc = new SimpleDoubleProperty(prc);
}

public Integer getSno() {
	return sno.get();
}
public String getId() {
	return id.get();
}
public String getName() {
	return name.get();
}
public Integer getCrt() {
	return crt.get();
}
public Integer getPcs() {
	return pcs.get();
}
public Double getMwt() {
	return mwt.get();
}
public Double getSwt() {
	return swt.get();
}
public Double getWstg() {
	return wstg.get();
}
public Double getPrc() {
	return prc.get();
}

}
