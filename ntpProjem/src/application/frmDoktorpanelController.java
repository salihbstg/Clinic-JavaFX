package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.projemMySQL.util.VeritabaniBaglanti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class frmDoktorpanelController {

	public frmDoktorpanelController() {
		baglanti=VeritabaniBaglanti.baglan();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_sonraki;

    @FXML
    private Label lblHasta;

    @FXML
    private TableView<Kayitlar> table;
    
    @FXML
    private TableColumn<Kayitlar, String> tblad;
    
    @FXML
    private TableColumn<Kayitlar, Integer> tblid;

    @FXML
    private TableColumn<Kayitlar, String> tblsoyad;

    @FXML
    private TableColumn<Kayitlar, String> tbltc;
    
    @FXML
    private Label lblDoktoradi;

    private static int id;
    
    ObservableList<Kayitlar> liste=FXCollections.observableArrayList();
    
    @FXML
    void sonraki_click(ActionEvent event) {
    	
    	lblHasta.setText(liste.get(0).ad+" "+liste.get(0).soyad+"\n"+liste.get(0).tc);
    	try {
			sorgu=baglanti.prepareStatement("update tbl_hastalar set sirada=false where sayac=?");
			sorgu.setInt(1, liste.get(0).id);
			sorgu.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	liste.remove(0);

    }

    Connection baglanti=null;
    PreparedStatement sorgu=null;
    ResultSet getirilen=null;
    String sql;
    Alert alert=new Alert(AlertType.INFORMATION);
    
    @FXML
    void initialize() {
    
    	tblid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblad.setCellValueFactory(new PropertyValueFactory<>("ad"));
        tblsoyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        tbltc.setCellValueFactory(new PropertyValueFactory<>("tc"));
    	
        sql = "SELECT * FROM tbl_doktor WHERE id=?";
    	try {
    		
			sorgu=baglanti.prepareStatement(sql);
			sorgu.setInt(1, this.id);
			getirilen=sorgu.executeQuery();
			if(getirilen.next()) {
				lblDoktoradi.setText(getirilen.getString("Ad")+" "+getirilen.getString("Soyad"));
			}
			sql="select * from tbl_hastalar where Doktor=? and sirada=true ORDER BY sayac";
			sorgu=baglanti.prepareStatement(sql);
			sorgu.setString(1, lblDoktoradi.getText());
			getirilen=sorgu.executeQuery();
			while(getirilen.next()) {
				liste.add(new Kayitlar(getirilen.getInt("sayac"),getirilen.getString("Ad"),
				getirilen.getString("Soyad"),getirilen.getString("TC") ) );
			
			}
			lblHasta.setText("");
			kayitlariListele();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   
			
		
    	

    }
    
    public void kayitlariListele() {
    	
    	table.setItems(liste);
    	
    }
    
    public class Kayitlar{
    	private String ad,soyad,tc;
    	private int id;
    	
    	
    	Kayitlar(){
    		
    	}
    	
    	Kayitlar(int id,String ad,String soyad,String tc){
    		this.id=id;
    		this.ad=ad;
    		this.soyad=soyad;
    		this.tc=tc;
    		
    		
    	}
    	
		public String getAd() {
			return ad;
		}
		public void setAd(String ad) {
			this.ad = ad;
		}
		public String getSoyad() {
			return soyad;
		}
		public void setSoyad(String soyad) {
			this.soyad = soyad;
		}
		public String getTc() {
			return tc;
		}
		public void setTc(String tc) {
			this.tc = tc;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		
    	
    	
    	
    }

    public void vericek(int id) {
    	
    	this.id=id;
    	
    }
}
