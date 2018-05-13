package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class TodaysRateController implements Initializable {
	
	@FXML
	public TextField goldrate18;
	public TextField goldrate22;
	public TextField goldrate24;
	public TextField silverrate;
	public Label error;
	public AnchorPane basepane;

	
	public void updateRates() {
		String a,b,c,d;
		error.setText("");
		a=goldrate24.getText();
		b=goldrate22.getText();
		c=goldrate18.getText();
		d=silverrate.getText();
		if(a.equals("")||b.equals("")||c.equals("")||d.equals("")) {
			error.setText("Fields cannot be empty");
		}else {
		Values.goldrate24=Double.parseDouble(a);
		Values.goldrate22=Double.parseDouble(b);
		Values.goldrate18=Double.parseDouble(c);
		Values.silverrate=Double.parseDouble(d);
		double[] rates= {Values.goldrate18,Values.goldrate22,Values.goldrate24,Values.silverrate};
		GlobalAccess.updateRates(rates);
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(getClass().getResource("homeElements_view.fxml"));
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
    	alert.setHeaderText("Today's Rate");
        alert.setContentText(" Rates Succefully Updated");
    	DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    // ... user chose OK        	
    		try {	
    			Pane p=(Pane)basepane.getParent();
    			p.getChildren().clear();
    			p.getChildren().add(loader.load());
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	} else {
    	    // ... user chose CANCEL or closed the dialog	        		
    		}	       

		}
	}
	
	public void clearTexts() {
		error.setText("");
		goldrate18.setText("");
		goldrate24.setText("");
		goldrate22.setText("");
		silverrate.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		error.setText("");
		goldrate18.setText(""+Values.goldrate18);
		goldrate24.setText(""+Values.goldrate24);
		goldrate22.setText(""+Values.goldrate22);
		silverrate.setText(""+Values.silverrate);
	}
}
