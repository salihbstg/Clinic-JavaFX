package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class girisController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_doktorGiris;

    @FXML
    private Button btn_hastaGiris;

    

    @FXML
    void btn_doktorGiris_click(ActionEvent event) {
    	
    	try {
    		Stage primaryStage=new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("frmDoktorgiris.fxml"));
			Scene scene = new Scene(root,400,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.setMaxHeight(350);
			primaryStage.setMaxWidth(450);
			primaryStage.setMinHeight(300);
			primaryStage.setMinWidth(400);
			primaryStage.centerOnScreen();
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void btn_hastaGiris_click(ActionEvent event) {
    	try {
    		Stage primaryStage=new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("frmHastagiris.fxml"));
			Scene scene = new Scene(root,400,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.setMaxHeight(350);
			primaryStage.setMaxWidth(450);
			primaryStage.setMinHeight(300);
			primaryStage.setMinWidth(400);
			primaryStage.centerOnScreen();
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
       

    }

}
