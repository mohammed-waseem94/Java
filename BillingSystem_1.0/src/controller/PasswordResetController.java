package controller;

import java.io.IOException;
import java.util.Optional;

import application.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.GlobalAccess;
import res.Values;

public class PasswordResetController {
	@FXML
	public TextField oldpwd;
	public TextField newpwd;
	public TextField confirmpwd;
	public Label error;
	public AnchorPane basepane;
	
	public void changepwd(ActionEvent ae) {
		String a,b,c;
		error.setText("");
		a=oldpwd.getText();
		b=newpwd.getText();
		c=confirmpwd.getText();
		if(a.equals("")||b.equals("")||c.equals(""))
			error.setText("Fields cannot be empty");
		 String oldpassword = Encryption.encryptpwd(a);
		 if(oldpassword.equals(Values.loginpassword)) {
			 if(b.equals(c)) {
				 Values.loginpassword=Encryption.encryptpwd(newpwd.getText());
				 GlobalAccess.updatAdminPwd(Values.loginpassword);
				 FXMLLoader loader= new FXMLLoader();		
				 loader.setLocation(getClass().getResource("homeElements_view.fxml"));
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Info");
			    	alert.setHeaderText("Reset Password");
			        alert.setContentText(" Password Succefully Changed");
			    	DialogPane dialogPane = alert.getDialogPane();
					dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					dialogPane.getStyleClass().add("myDialog");

			    	Optional<ButtonType> result = alert.showAndWait();
			    	if (result.get() == ButtonType.OK){
			    	    // ... user chose OK        	
			    		try {	
			    			Pane p=(Pane)basepane.getParent();
			    	//		AnchorPane ap=(AnchorPane)basepane.getParent();
			    			p.getChildren().clear();
			    			p.getChildren().add(loader.load());
			    		} catch (IOException e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}
			    	}
			 }else {
				 error.setText("Confirm password is different from new password");
			 }
		 }else {
			 error.setText("old password is incorrect");
		 }
	     
	}

}
