package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import res.Values;

public class GlobalAccess {
	
	public static void getGlobalValues() {
		try{  
			 ResultSet rs = Values.stmt.executeQuery("select * from global");  
			 while(rs.next()) {
			 Values.loginusername =rs.getString(1);
			 Values.loginpassword=rs.getString(2);
			 Values.dbAddress=rs.getString(3);
			 Values.dbAdmin=rs.getString(4);
			 Values.dbPassword=rs.getString(5);
			 Values.goldrate18=rs.getDouble(6);
			 Values.goldrate22=rs.getDouble(7);
			 Values.goldrate24=rs.getDouble(8);
			 Values.silverrate=rs.getDouble(9);
			 }
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
		
		}
	}
	
	public static boolean updatAdminPwd(String p) {
		try {
			Values.stmt.executeUpdate("update global set userpwd = '"+p+"'");
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
	public static boolean updateRates(double[] d) {
		try {
			Values.stmt.executeUpdate("update global set gold18 = '"+d[0]+"',gold22 = '"+d[1]+"',gold24 = '"+d[2]+"', silver = '"+d[3]+"'");
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
