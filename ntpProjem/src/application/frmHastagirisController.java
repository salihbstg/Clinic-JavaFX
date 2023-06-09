package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.projemMySQL.util.VeritabaniBaglanti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class frmHastagirisController {
	public frmHastagirisController() {
		baglanti=VeritabaniBaglanti.baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_giris;

    @FXML
    private TextField txttc;
    Connection baglanti=null;
    PreparedStatement sorgu=null,sorgu2=null,sorgu3=null;
    ResultSet getirilen=null,getirilen2=null,getirilen3=null;
    String sql;
    Alert alert=new Alert(AlertType.INFORMATION);
    @FXML
    void btn_giris_click(ActionEvent event) {
    	int sayac = 0;
    	String doktor;
    	sql="select * from tbl_hastalar where TC=?";
    	try {
    		//txttc içerisine yazılan tc numarası kayıtlıysa  if içerisine girecek
    		sorgu=baglanti.prepareStatement(sql);
    		sorgu.setString(1, txttc.getText());
    		getirilen=sorgu.executeQuery();
    		if(getirilen.next()) {
    			//hastanın bilgileri ile alert oluşturulacak ve sıra bekliyor değeri true olarak değişecek
    			sql="update tbl_hastalar set sirada=true where TC=?";
    			sorgu=baglanti.prepareStatement(sql);
    			sorgu.setString(1, txttc.getText().trim());
    			sorgu.execute();
    			alert.setTitle("Sağlık Ocağı");
    			alert.setHeaderText("SIRA ALINDI. DOKTORUNUZ:"+getirilen.getString("Doktor"));
    			
    			
    			//Hastanın karşısında olan doktorun sayaç numarası 1 artacak
    			sorgu=baglanti.prepareStatement("update tbl_doktor set sayac=sayac+1 where CONCAT(Ad, Soyad)=?");
    			sorgu.setString(1, getirilen.getString("Doktor").replaceAll("\\s", ""));
    			sorgu.execute();
    			
    			//Hastanın doktorunun sayaç değeri alınacak
    			sorgu2=baglanti.prepareStatement("select sayac from tbl_doktor where CONCAT(Ad,Soyad)=? ");
    			sorgu2.setString(1, getirilen.getString("Doktor").replaceAll("\\s", ""));
    			getirilen2=sorgu2.executeQuery();
    			if(getirilen2.next()) {
    				sayac=getirilen2.getInt("sayac");
    				
    			}
    			
    			//Doktorun sayaç değeri hastaya sıra numarası olarak atanacak
    			sorgu3=baglanti.prepareStatement("update tbl_hastalar set sayac=? where TC=?");
    			sorgu3.setInt(1, sayac);
    			sorgu3.setString(2, txttc.getText());
    			sorgu3.execute();
    			alert.show();
    		}
    		
    		
    		else
    		{
    			alert.setTitle("Sağlık Ocağı");
    			alert.setHeaderText("SAĞLIK OCAĞIMIZDA KAYDINIZ YOK.\nTÜRKİYE CUMHURİYETİ SAĞLIK BAKANLIĞI'NA MÜRACAAT EDİNİZ.");
    			alert.show();
    		}
    		
   
		}
    	catch (Exception e) {
			
			e.getMessage();
		}
    	
    	
    }

    @FXML
    void initialize() {
        assert btn_giris != null : "fx:id=\"btn_giris\" was not injected: check your FXML file 'frmHastagiris.fxml'.";
        assert txttc != null : "fx:id=\"txttc\" was not injected: check your FXML file 'frmHastagiris.fxml'.";

    }

}
