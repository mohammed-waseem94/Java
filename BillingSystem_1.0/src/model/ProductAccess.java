package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import application.Product;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import res.Values;

public class ProductAccess {
	
	public static Product getProductDetails(String product_id) {
		Product p = null;
		try{  
			 ResultSet rs = Values.stmt.executeQuery("select * from product where product_id='"+product_id+"'");  
			 while(rs.next())
			 p=new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs.getDouble(12),rs.getString(13));
			 return p;
		}catch(Exception e){ 
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(new Object() { }.getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		
			return null;
		}
	}
	
	public static boolean addNewProduct(Product p) {
		try {
			Values.stmt.executeUpdate("insert into product values ('"+p.product_id+"','"+p.product_name+"','"+p.barcode+"','"+p.model_no+"','"+p.material+"','"+p.category+"','"+p.carat+"','"+p.net_wt+"','"+p.stone_wt+"','"+p.gross_wt+"','"+p.making+"','"+p.wastage+"','"+p.stonedetails+"')" );
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(new Object() { }.getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		
			return false;
		}  
	}
	
	public static boolean updateProductDetails(Product p) {
		try {
			Values.stmt.executeUpdate("update product set product_name = '"+p.product_name+"',barcode = '"+p.barcode+"',model_no = '"+p.model_no+"', material = '"+p.material+"', category = '"+p.category+"',carat = '"+p.carat+"',gold_wt = '"+p.net_wt+"',stone_wt = '"+p.stone_wt+"',gross_wt = '"+p.gross_wt+"',making = '"+p.making+"',wastage = '"+p.wastage+"',stonedetails = '"+p.stonedetails+"'where product_id = '" +p.product_id+"'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(new Object() { }.getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			
			e.printStackTrace();
		
			return false;
		} 
	 
		  
	}
}