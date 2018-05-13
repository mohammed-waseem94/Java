package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.ProductAccess;
import res.Values;

public class QuotationController implements Initializable{

	@FXML
	public Label error;
	public TextField productid;
	public TextField wastage;
	public TextField making;
	public TextField material;
	public TextField carat;
	public TextField discount;
	public TextField description;
	public TextField net_wt;
	public TextField stone_wt;
	public TextField price;
	public TextField totamnt;
	public TextArea stonedetails;
	public TableView<BillProduct> table;
	public TableColumn<BillProduct, Integer> sno;
	public TableColumn<BillProduct, Integer> pcs;
	public TableColumn<BillProduct, Integer> crt;
	public TableColumn<BillProduct, String> name;
	public TableColumn<BillProduct, Double> mwt;
	public TableColumn<BillProduct, Double> swt;
	public TableColumn<BillProduct, Double> wstg;
	public TableColumn<BillProduct, Double> prc;
	
	
	public int cnt=1;
	Double pr,tot=0.0;
	
	
	public void addToBill(ActionEvent ae) {
		String a,b,c,d,e,f;
		error.setText("");
		a=description.getText();
		b=carat.getText();
		c=net_wt.getText();
		d=stone_wt.getText();
		e=price.getText();
		f=wastage.getText();
		if(a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals("")||f.equals(""))
			error.setText("Fields cannot be empty");
		else {
			DecimalFormat df = new DecimalFormat("###.##");
			pr=Double.valueOf(df.format(Double.parseDouble(e)));
			tot+=pr;
			Double.valueOf(df.format(tot));
			boolean idfnd=false;
			int index=0;
			String id=productid.getText();
			if(id.equals(""))id="0";
			if(!table.getItems().isEmpty()) {
			Iterator<BillProduct> it=table.getItems().iterator();
		while(it.hasNext()) {
			if((it.next().getId()).equals(id)) {
				idfnd=true;
				break;
			}else index++;
		}}
		if(idfnd) {
			BillProduct bp=table.getItems().get(index);
			BillProduct nbp=new BillProduct(bp.getSno(),bp.getId(), bp.getName(), bp.getPcs()+1, 
					bp.getCrt(), bp.getMwt(), bp.getSwt(), bp.getWstg(), Double.valueOf(df.format(bp.getPrc()+pr)));
			table.getItems().set(index, nbp);
			totamnt.setText(""+tot);
		}
		else {
			BillProduct p=new BillProduct(cnt++,id, a,1,Integer.parseInt(b),Double.parseDouble(c),
			Double.parseDouble(d),Double.parseDouble(f),pr);
			totamnt.setText(""+tot);
			ObservableList<BillProduct> value=FXCollections.observableArrayList(p);		
			table.getItems().addAll(value);
			int a1=1;
			Iterator<BillProduct> it=table.getItems().iterator();
			while(it.hasNext()) {
				it.next().sno.set(a1++);
			}

			}
		}
	}
	public void generateBill(ActionEvent ae) {
		error.setText("");
		if(table.getItems().isEmpty())error.setText("add products to bill");
		else {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		TextField custname = new TextField();
		custname.setPromptText("Name");
		TextField address = new TextField();
		address.setPromptText("Address");
		TextField cont = new TextField();
		cont.setPromptText("Contact No.");
		TextField email = new TextField();
		email.setPromptText("Email address");
		
		grid.add(new Label("Name:"), 0, 0);
		grid.add(custname, 1, 0);
		grid.add(new Label("Address:"), 0, 1);
		grid.add(address, 1, 1);
		grid.add(new Label("Contact No.:"), 0, 2);
		grid.add(cont, 1, 2);
		grid.add(new Label("Email:"), 0, 3);
		grid.add(email, 1, 3);
		Label l=new Label("");
		grid.add(l, 0, 3);
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Customer Details");
		alert.setHeaderText("");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setContent(grid);
		dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String d=discount.getText();
			if(d.equals(""))d="0.0";
			String a,b,c;
			a=custname.getText();
			b=address.getText();
			c=cont.getText();

			if(a.equals("")||b.equals("")||c.equals(""))
				error.setText("User details caonnot be empty");
			else{
				ObservableList<BillProduct> val=table.getItems();
				GenerateInvoice.generateInvoice(a,b,c,email.getText(),val,
				Double.parseDouble(d),Double.parseDouble(totamnt.getText()),true);
				}
				} else {
		}	
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		error.setText("");
		sno.setCellValueFactory(new PropertyValueFactory<BillProduct,Integer>("sno"));
		pcs.setCellValueFactory(new PropertyValueFactory<BillProduct,Integer>("pcs"));
		crt.setCellValueFactory(new PropertyValueFactory<BillProduct,Integer>("crt"));
		name.setCellValueFactory(new PropertyValueFactory<BillProduct,String>("name"));
		mwt.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("mwt"));
		swt.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("swt"));
		wstg.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("wstg"));
		prc.setCellValueFactory(new PropertyValueFactory<BillProduct,Double>("prc"));			
	}	
	
	public void getDetails() {
		error.setText("");
		Product p = ProductAccess.getProductDetails(productid.getText());
		if(p==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Info");
	    	alert.setHeaderText("Database Error");
	        alert.setContentText(" Couldn't get product details");
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
			description.setText(p.product_name);
			material.setText(p.material);
			carat.setText(""+p.carat);
			net_wt.setText(""+p.net_wt);
			stone_wt.setText(""+p.stone_wt);
			making.setText(""+p.making);
			wastage.setText(""+p.wastage);
			stonedetails.setText(p.stonedetails);
			double ta, nwt=p.net_wt;
			String mat=p.material;
			if(mat.equals("Gold_24"))ta=Values.goldrate24*nwt;
			else if(mat.equals("Gold_22"))ta=Values.goldrate22*nwt;
			else if(mat.equals("Gold_18"))ta=Values.goldrate18*nwt;
			else ta=Values.silverrate*nwt;
			ta+=ta*(p.wastage/100);
			ta+=p.making;			
			price.setText(""+ta);
			
			}
	}

	public void clearDetails() {
		error.setText("");
		making.setText("");
		wastage.setText("");
		discount.setText("");
		description.setText("");
		net_wt.setText("");
		stone_wt.setText("");
		price.setText("");
		productid.setText("");
		price.setText("");
	}

	public void clearBill() {
		int i = table.getSelectionModel().getSelectedIndex();
		if(i>=0) {
			tot=Double.parseDouble(totamnt.getText())-table.getItems().get(i).prc.doubleValue();
			DecimalFormat df = new DecimalFormat("###.##");
			tot=Double.valueOf(df.format(tot));
			totamnt.setText(""+tot);
			table.getItems().remove(i);
			int a=1;
			Iterator<BillProduct> it=table.getItems().iterator();
			while(it.hasNext()) {
				it.next().sno.set(a++);
			}
		}
	}
}
