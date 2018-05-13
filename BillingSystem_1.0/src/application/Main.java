package application;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DBAccess;
import model.GlobalAccess;
import res.Values;

public class Main extends Application {

	@FXML public Pane homepane;	
	@FXML public ToolBar toolbar;	
	@FXML public MenuButton product;	
	@FXML public MenuButton setup;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("home_view.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 1500, 950);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Meezan Jewellers");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "mjlogo.png" ))); 
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(final WindowEvent event) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation Dialog");
					alert.setHeaderText("Are you sure to Exit?");
					DialogPane dialogPane = alert.getDialogPane();
					dialogPane.getStylesheets().add(
					   getClass().getResource("application.css").toExternalForm());
					dialogPane.getStyleClass().add("myDialog");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						if(Values.stmt!=null)DBAccess.closeDB();
						System.exit(0);	
					} else {
						event.consume();
					}
				}
			});
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		
		Values.stmt=DBAccess.connectToDB();
		GlobalAccess.getGlobalValues();
		MenuItem k,l,m,n; 
		k= new MenuItem("New Product");
		l=new MenuItem("Update Product");
		product.getItems().clear();
		product.getItems().add(k);
		product.getItems().add(l);
		k.setOnAction(event -> {
		   productclick(1); 
		});
		l.setOnAction(event -> {
		   productclick(2);
		});
		m=new MenuItem("Reset Password");
		n=new MenuItem("DB Setup");
		setup.getItems().clear();
		setup.getItems().add(m);
		setup.getItems().add(n);
		m.setOnAction(event -> {
		   setupclick(1); 
		});
		n.setOnAction(event -> {
		   setupclick(2);
		});
		toolbar.setDisable(true);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/login_view.fxml"));
		try {
			homepane.getChildren().clear();
			homepane.getChildren().add(loader.load());
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void homeclick(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/homeElements_view.fxml"));
		try {
			homepane.getChildren().clear();
			homepane.getChildren().add(loader.load());
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}		
	}
	
	public void productclick(int x) {
		FXMLLoader loader = new FXMLLoader();
		if(x==1)
			loader.setLocation(getClass().getResource("/controller/addproduct_view.fxml"));
		else if(x==2)
			loader.setLocation(getClass().getResource("/controller/updateproduct_view.fxml"));
		try {
			homepane.getChildren().clear();
			homepane.getChildren().add(loader.load());
		} catch (IOException e) {			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}
	}
	
	public void setupclick(int x) {
		FXMLLoader loader = new FXMLLoader();
		if(x==1)
			loader.setLocation(getClass().getResource("/controller/passwordreset_view.fxml"));
		else if(x==2)
			loader.setLocation(getClass().getResource("/controller/dbsetup_view.fxml"));
		try {
			homepane.getChildren().clear();
			homepane.getChildren().add(loader.load());
		} catch (IOException e) {			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}
	}

	public void helpclick(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/help_view.fxml"));
		try {
			homepane.getChildren().clear();
			homepane.getChildren().add(loader.load());
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}		
	}	
	public void aboutclick(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/about_view.fxml"));
		try {
			homepane.getChildren().clear();
			homepane.getChildren().add(loader.load());
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}		
	}
	

	public void logoutclick(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/login_view.fxml"));
		try {
			toolbar.setDisable(true);
			homepane.getChildren().clear();
			homepane.getChildren().add(loader.load());
		//	DBAccess.closeDB();
		} catch (IOException e) {			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
			} else {
			}
			e.printStackTrace();
		}		
	}
}
