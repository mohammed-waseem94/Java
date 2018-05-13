package model;

import java.sql.SQLException;
import java.util.Optional;

import application.Bill;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import res.Values;

public class SalesAccess {
	
	public static boolean addToSales(Bill b) {
		try {
			Values.stmt.executeUpdate("insert into sales (invoice_no, cust_name, cust_cont, cust_email, tot_amnt) values ('"+b.invoiceno+"','"+b.custname+"','"+b.custcont+"','"+b.custemail+"','"+b.totamnt+"')" );
			return true;
		} catch (SQLException e) {
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
