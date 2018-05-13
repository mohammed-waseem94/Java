package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import res.Values;

public class HomeElementController implements Initializable{
	@FXML
	public AnchorPane baseap;
	public TableView<Val> table;
	public TableColumn<Val, String> metal;
	public TableColumn<Val, String> rate;
	
	public void setTodaysRate(ActionEvent ae) {
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(getClass().getResource("todaysrate_view.fxml"));
		try {
			Pane p=(Pane)baseap.getParent();
			p.getChildren().clear();
			p.getChildren().add(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public void productDetails(ActionEvent ae) {
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(getClass().getResource("getproduct_view.fxml"));
		try {
			Pane p=(Pane)baseap.getParent();
			p.getChildren().clear();
			p.getChildren().add(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateBill(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("generatebill_view.fxml"));
		try {
			Pane p=(Pane)baseap.getParent();
			p.getChildren().clear();
			p.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void getQuotation() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("quotation_view.fxml"));
		try {
			Pane p=(Pane)baseap.getParent();
			p.getChildren().clear();
			p.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<Val> list= FXCollections.observableArrayList(new Val("Gold 24ct",""+Values.goldrate24),new Val("Gold 22ct",""+Values.goldrate22),
				new Val("Gold 18ct",""+Values.goldrate18),new Val("Silver",""+Values.silverrate));
		metal.setCellValueFactory(new PropertyValueFactory<Val,String>("metal"));
		rate.setCellValueFactory(new PropertyValueFactory<Val,String>("rate"));	
		table.getItems().addAll(list);
	}
}