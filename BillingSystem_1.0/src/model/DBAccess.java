package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import res.Values;

public class DBAccess {
	
	
	public static Connection con;
	public static Statement stmt;

	public static Statement connectToDB() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			 con=DriverManager.getConnection(  
			Values.dbAddress,Values.dbAdmin,Values.dbPassword);  
			 return stmt=con.createStatement(); 	
			}
			catch(Exception ex){ Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("DB Error");
			alert.setHeaderText("Couldn't connect to DataBase");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(new Object() { }.getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if(Values.stmt!=null) DBAccess.closeDB();
			//	System.exit(0);	
			} else {
				
			}
		}
		return null;
	}
	
	public static void closeDB() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
