package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.ProductAccess;

public class AddProdController implements Initializable{

@FXML
public TextField product_id;
public TextField barcode;
public TextField product_name;
public TextField model_no;
public ChoiceBox<String> material;
public TextField category;
public TextField carat;
public TextField net_wt;
public TextField stone_wt;
public TextField gross_wt;
public TextField making;
public TextField wastage;
public TextArea stonedetails;
public Label error;
 

public void addNewProd(ActionEvent ae) {
	error.setText("");
	
	String a,b,c,d,e,f,g,h,i,j,k,l,m;
	a=product_id.getText();
	b=product_name.getText();
	c=barcode.getText();
	d=model_no.getText();
	e=material.getValue();
	f=category.getText();
	g=carat.getText();
	h=net_wt.getText();
	i=stone_wt.getText();
	j=gross_wt.getText();
	k=making.getText();
	l=wastage.getText();
	m=stonedetails.getText();
	if(a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals("")||f.equals("")||j.equals("")||h.equals("")||
			i.equals("")||j.equals("")||l.equals(""))
		error.setText("Fields cannot be empty");
	else {
		Product	 p = new Product(a,b,c,d,
				e,f,Integer.parseInt(g),Double.parseDouble(h),
				Double.parseDouble(i),Double.parseDouble(j),Double.parseDouble(k),
				Double.parseDouble(l),m);

	  if(ProductAccess.addNewProduct(p)) {
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("Info");
	        	alert.setHeaderText("Database Updated");
		        alert.setContentText(product_name.getText()+" succesfully added");
		    	DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				dialogPane.getStyleClass().add("myDialog");
			
	        	Optional<ButtonType> result = alert.showAndWait();
	        	if (result.get() == ButtonType.OK){
	        	    // ... user chose OK       		
	        	} else {
	        	    // ... user chose CANCEL or closed the dialog	        		
	        	}	        	
			}	else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Info");
		    	alert.setHeaderText("Database Error");
		        alert.setContentText(" Couldn't add product details");
		    	DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				dialogPane.getStyleClass().add("myDialog");

	        	Optional<ButtonType> result = alert.showAndWait();
	        	if (result.get() == ButtonType.OK){
	        	    // ... user chose OK        		
	        	} else {
	        	    // ... user chose CANCEL or closed the dialog	        		
	        	}	       }
		
			}
		}
	

public void clearAll() {
	error.setText("");
	product_id.setText("");
	product_name.setText("");
	barcode.setText("");
	model_no.setText("");
	material.setId("");
	making.setText("");
	category.setText("");
	carat.setText("");
	net_wt.setText("");
	stone_wt.setText("");
	gross_wt.setText("");
	wastage.setText("");
	stonedetails.setText("");
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	material.getItems().add("Gold_24");
	material.getItems().add("Gold_22");
	material.getItems().add("Gold_18");
	material.getItems().add("Silver");
	barcode.textProperty().addListener((observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*")) barcode.setText(oldValue);
		});
	carat.textProperty().addListener((observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*")) carat.setText(oldValue);
		});
	net_wt.textProperty().addListener((observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*\\.?\\d*")) net_wt.setText(oldValue);
		});
	stone_wt.textProperty().addListener((observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*\\.?\\d*")) stone_wt.setText(oldValue);
		});
	gross_wt.textProperty().addListener((observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*\\.?\\d*")) gross_wt.setText(oldValue);
		});
	making.textProperty().addListener((observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*\\.?\\d*")) making.setText(oldValue);
		}); 
	wastage.textProperty().addListener((observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*\\.?\\d*")) wastage.setText(oldValue);
		}); 
}
}
