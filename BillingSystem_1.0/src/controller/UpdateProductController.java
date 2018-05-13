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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.ProductAccess;

public class UpdateProductController implements Initializable{
	
	@FXML
	public TextField product_id;
	public TextField barcode;
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
	public Label error;
	public TextArea stonedetails;
	
	public void getProductDetails(ActionEvent ae) {
		error.setText("");
		Product p = ProductAccess.getProductDetails(product_id.getText());
		if(p==null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Info");
		    	alert.setHeaderText("Database Error");
		        alert.setContentText(product_id.getText()+" is an invalid Product ID");
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
		barcode.setText(""+p.barcode);
		model_no.setText(p.model_no);
		material.setText(p.material);
		category.setText(p.category);
		carat.setText(""+p.carat);
		net_wt.setText(""+p.net_wt);
		stone_wt.setText(""+p.stone_wt);
		gross_wt.setText(""+p.gross_wt);
		making.setText(""+p.making);
		wastage.setText(""+p.wastage);
		stonedetails.setText(p.stonedetails);
		}
	}
	
	public void clearProdDetails() {
		error.setText("");
		product_id.setText("");
		product_name.setText("");
		barcode.setText("");
		model_no.setText("");
		material.setText("");
		making.setText("");
		category.setText("");
		carat.setText("");
		net_wt.setText("");
		stone_wt.setText("");
		gross_wt.setText("");
		wastage.setText("");
		stonedetails.setText("");
	}
	
	public void updateProd() {
		error.setText("");
		String a,b,c,d,e,f,g,h,i,j,k,l,m;
		a=product_id.getText();
		b=product_name.getText();
		c=barcode.getText();
		d=model_no.getText();
		e=material.getText();
		f=category.getText();
		g=carat.getText();
		h=net_wt.getText();
		i=stone_wt.getText();
		j=gross_wt.getText();
		k=making.getText();
		l=wastage.getText();
		m=stonedetails.getText();
		if(a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals("")||f.equals("")||j.equals("")||h.equals("")||
				i.equals("")||j.equals("")||k.equals("")||l.equals(""))
			error.setText("Fields cannot be empty");
		else {
			
			Product  p = new Product(a,b,c,d,
					e,f,Integer.parseInt(g),Double.parseDouble(h),
					Double.parseDouble(i),Double.parseDouble(j),Double.parseDouble(k),
					Double.parseDouble(l),m);

		
		if(ProductAccess.updateProductDetails(p)) {
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("Info");
	        	alert.setHeaderText("Database Updated");
		        alert.setContentText(product_name.getText()+" succesfully Updated");
	        	DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				dialogPane.getStyleClass().add("myDialog");
				
	        	Optional<ButtonType> result = alert.showAndWait();
	        	if (result.get() == ButtonType.OK){
	        	    // ... user chose OK	        		
	        	} else {
	        	    // ... user chose CANCEL or closed the dialog	        		
	        	}	        	
			}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Info");
	    	alert.setHeaderText("Database Error");
	        alert.setContentText(" Couldn't update product details");
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
		making.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*\\.?\\d*")) making.setText(oldValue);
			}); 
		wastage.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*\\.?\\d*")) wastage.setText(oldValue);
			}); 
	}
}
