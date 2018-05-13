package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.ProductAccess;
import res.Values;

public class GetProdController implements Initializable{

@FXML
public TextField product_id;
public TextField product_name;
public TextField model_no;
public TextField material;
public TextField category;
public TextField carat;
public TextField net_wt;
public TextField stone_wt;
public TextField gross_wt;
public TextField making;
public TextField wastage;
public TextField totamnt;
public TextField discount;
public Label error;
public TableView<BillProduct> table;
public TableColumn<BillProduct, Integer> sno;
public TableColumn<BillProduct, Integer> pcs;
public TableColumn<BillProduct, String> id;
public TableColumn<BillProduct, Integer> crt;
public TableColumn<BillProduct, String> name;
public TableColumn<BillProduct, Double> mwt;
public TableColumn<BillProduct, Double> swt;
public TableColumn<BillProduct, Double> wstg;
public TableColumn<BillProduct, Double> prc;

int cnt=1;
double tot=0.0;
public void getProductDetails(ActionEvent ae) {
	error.setText("");
	Product p = ProductAccess.getProductDetails(product_id.getText());
	if(p==null) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Info");
    	alert.setHeaderText("Database Error");
        alert.setContentText(" Couldn't get product details");
    	DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    // ... user chose OK        		
    	} else {
    	    // ... user chose CANCEL or closed the dialog	        		
    		}	       
	}else {
	
		product_name.setText(p.product_name);
		model_no.setText(p.model_no);
		material.setText(p.material);
		category.setText(p.category);
		carat.setText(""+p.carat);	
		net_wt.setText(""+p.net_wt);
		stone_wt.setText(""+p.stone_wt);
		gross_wt.setText(""+p.gross_wt);
		making.setText(""+p.making);
		wastage.setText(""+p.wastage);
		}
	}

public void add2Bill(ActionEvent ae) {
	error.setText("");
if(product_name.getText().equals(""))error.setText("product details cannot be null"); 
	else{
	int c=Integer.parseInt(carat.getText());
	double w=Double.parseDouble(wastage.getText());
	double ta, nwt=Double.parseDouble(net_wt.getText());
	double mak=Double.parseDouble(making.getText());
	String mat=material.getText();
	if(mat.equals("Gold_24"))ta=Values.goldrate24*nwt;
	else if(mat.equals("Gold_22"))ta=Values.goldrate22*nwt;
	else if(mat.equals("Gold_18"))ta=Values.goldrate18*nwt;
	else ta=Values.silverrate*nwt;
	ta+=ta*(w/100);
	ta+=mak;
	DecimalFormat df = new DecimalFormat("###.##");
	ta=Double.valueOf(df.format(ta));
	tot+=ta;
	boolean idfnd=false;
	int index=0;
	String id=product_id.getText();
	if(id.equals(""))id="0";
	if(!table.getItems().isEmpty()) {
	Iterator<BillProduct> it=table.getItems().iterator();
		while(it.hasNext()) {
			if((it.next().getId()).equals(id)) {
				idfnd=true;
				break;
			}else index++;
		}}
		if(idfnd) {
			BillProduct bp=table.getItems().get(index);
			BillProduct nbp=new BillProduct(bp.getSno(),bp.getId(), bp.getName(), bp.getPcs()+1, 
					bp.getCrt(), bp.getMwt(), bp.getSwt(), bp.getWstg(),
					Double.valueOf(df.format(bp.getPrc()+ta)));
			table.getItems().set(index, nbp);
			totamnt.setText(""+tot);
}else {
	BillProduct p=new BillProduct(cnt++,id,product_name.getText(),1,
			c,nwt,Double.parseDouble(stone_wt.getText()),w,ta);
	
	totamnt.setText(""+tot);
	ObservableList<BillProduct> list=FXCollections.observableArrayList(p);
	table.getItems().addAll(list);
	int a1=1;
	Iterator<BillProduct> it=table.getItems().iterator();
	while(it.hasNext()) {
		it.next().sno.set(a1++);
	}

}
}
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	sno.setCellValueFactory(new PropertyValueFactory<BillProduct,Integer>("sno"));
	pcs.setCellValueFactory(new PropertyValueFactory<BillProduct,Integer>("pcs"));
	crt.setCellValueFactory(new PropertyValueFactory<BillProduct,Integer>("crt"));
	name.setCellValueFactory(new PropertyValueFactory<BillProduct,String>("name"));
	mwt.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("mwt"));
	swt.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("swt"));
	wstg.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("wstg"));
	prc.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("prc"));			
}

public void clearProdDetails() {
	error.setText("");
	product_id.setText("");
	product_name.setText("");
	model_no.setText("");
	material.setText("");
	making.setText("");
	category.setText("");
	carat.setText("");
	net_wt.setText("");
	stone_wt.setText("");
	gross_wt.setText("");
	wastage.setText("");
}

public void clearBill() {
	error.setText("");
	int i = table.getSelectionModel().getSelectedIndex();
	if(i>=0) {
		tot=Double.parseDouble(totamnt.getText())-table.getItems().get(i).prc.doubleValue();
		DecimalFormat df = new DecimalFormat("###.##");
		tot=Double.valueOf(df.format(tot));
		totamnt.setText(""+tot);
		table.getItems().remove(i);
		int a=1;
		Iterator<BillProduct> it=table.getItems().iterator();
		while(it.hasNext()) {
			it.next().sno.set(a++);
		}
	}
}

public void generateBill(ActionEvent ae) {
	error.setText("");
	GridPane grid = new GridPane();
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(20, 150, 10, 10));
	
	TextField name = new TextField();
	name.setPromptText("Name");
	TextField address = new TextField();
	address.setPromptText("Address");
	TextField cont = new TextField();
	cont.setPromptText("Contact No.");
	TextField email = new TextField();
	email.setPromptText("Email address");
	grid.add(new Label("Name:"), 0, 0);
	grid.add(name, 1, 0);
	grid.add(new Label("Address:"), 0, 1);
	grid.add(address, 1, 1);
	grid.add(new Label("Contact No.:"), 0, 2);
	grid.add(cont, 1, 2);
	grid.add(new Label("Email:"), 0, 3);
	grid.add(email, 1, 3);
	
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Customer Details");
	alert.setHeaderText("");
	
	DialogPane dialogPane = alert.getDialogPane();
	dialogPane.setContent(grid);
	dialogPane.getStylesheets().add(
	   getClass().getResource("/application/application.css").toExternalForm());
	dialogPane.getStyleClass().add("myDialog");
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {
		String d=discount.getText();
		if(d.equals(""))d="0.0";
		String a,b,c;
		a=name.getText();
		b=address.getText();
		c=cont.getText();

		if(a.equals("")||b.equals("")||c.equals("")) 
			error.setText("Customer details cannot be empty");
		else {	
			ObservableList<BillProduct> val=table.getItems();
			GenerateInvoice.generateInvoice(a,b,c,email.getText(),val,
			Double.parseDouble(d),Double.parseDouble(totamnt.getText()),false);
		}
		} else {}
	}
}
