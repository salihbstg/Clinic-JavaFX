package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.projemMySQL.util.VeritabaniBaglanti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;
public class frmDoktorgirisController {

	public frmDoktorgirisController() {
		baglanti=VeritabaniBaglanti.baglan();
	}
    @FXML
    private ResourceBundle resources;
    @FXML
    private Label lblDoktoradi;

    @FXML
    private URL location;

    @FXML
    private Button btn_giris;

    @FXML
    private PasswordField txtsifre;

    @FXML
    private TextField txttc;

    Connection baglanti=null;
    PreparedStatement sorgu=null;
    ResultSet getirilen=null;
    String sql;
    Alert alert=new Alert(AlertType.INFORMATION);
    public int id;
    
    @FXML
    void btn_giris_click(ActionEvent event) {
    	try {
    		
    		//Doktor tc ve şifresi kontrol ediliyor
    		sql="select * from tbl_doktor where TC=? and sifre=?";
    		sorgu=baglanti.prepareStatement(sql);
    		sorgu.setString(1, txttc.getText().trim());
    		sorgu.setString(2,txtsifre.getText().trim());
    		getirilen=sorgu.executeQuery();
    		
    		//TC ve şifre doğruysa if içine girecek
    		if(getirilen.next()) {
    			
    			//Giriş yapan doktorun idsi doktor panelinde kullanılmak için çekildi
    			id=getirilen.getInt("id");
    			frmDoktorpanelController a=new frmDoktorpanelController();
    			a.vericek(id);
    			
    			//Form ekranı oluşturuldu ve gösterildi
    			Stage primaryStage=new Stage();
    			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("frmDoktorpanel.fxml"));
    			Scene scene = new Scene(root,800,450);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			primaryStage.initStyle(StageStyle.UTILITY);
    			primaryStage.setMaxHeight(500);
    			primaryStage.setMaxWidth(815);
    			primaryStage.setMinHeight(480);
    			primaryStage.setMinWidth(800);
    			primaryStage.centerOnScreen();
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		}
    		else
    		{
    			alert.setTitle("Doktor Paneli");
    			alert.setHeaderText("Giriş Başarısız");
    			alert.show();	
    		}
    		
    		
			
		} catch(Exception e) {
			e.getMessage();
		}
    }

    @FXML
    void initialize() {
        

    }

}
