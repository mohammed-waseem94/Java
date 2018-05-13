package controller;

import java.io.IOException;

import application.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import res.Values;

public class LoginController {
	@FXML
	public AnchorPane basepane;
	public TextField username;
	public TextField password;
	public Label output;

	
		
	public void checkCredentials(ActionEvent ae) {
		FXMLLoader loader= new FXMLLoader();
	//	if(true) {if(true) {
				if(username.getText().equals(Values.loginusername)) {
					String generatedPassword = Encryption.encryptpwd(password.getText());
	                	if(generatedPassword.equals(Values.loginpassword)) {

	        	   output.setText("Login Suucessful");

	            	loader.setLocation(getClass().getResource("homeElements_view.fxml"));
	        		try {	
	        			Pane p=(Pane)basepane.getParent();
	        			AnchorPane ap =(AnchorPane)p.getParent();
	        			ap.getChildren().get(0).setDisable(false);
	        			p.getChildren().clear();
	        			p.getChildren().add(loader.load());
	        		} catch (IOException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	            	
	            }
	            else output.setText("Invalid password");
		}
	    else output.setText("Invalid username");
	}
}
