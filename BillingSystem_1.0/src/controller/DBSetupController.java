package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import res.Values;

public class DBSetupController {
	
	@FXML
	public Label output;
	@FXML
	public TextField curdbpwd;
	@FXML
	public TextField newdbaddr;
	@FXML
	public TextField newdbusername;
	@FXML
	public TextField newdbpwd;
	
	public void dbSetup(ActionEvent ae) {
		if(curdbpwd.getText().equals(Values.dbPassword)) {
			Values.dbAddress=newdbaddr.getText();
			Values.dbAdmin=newdbusername.getText();
			Values.dbPassword=newdbpwd.getText();
			
			output.setText("DB details updated succesfully");
		}else
			output.setText("Old DB password is incorrect");
	}
	
}
