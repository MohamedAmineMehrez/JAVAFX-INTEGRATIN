/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.articles_service;
import Util.MyDB;
import com.darkprograms.speech.translator.GoogleTranslate;
import entities.Articles;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MOUEFEK
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfimg;
    @FXML
    private ComboBox<String> cbjeu;
    @FXML
    private Button btnannuler;

    Articles ar = new Articles();
    articles_service ars = new articles_service();
    @FXML
    private Button btnajouter;
    @FXML
    private Label titrelabel;
    @FXML
    private Label jeulabel;
    @FXML
    private Label labelaj;
    @FXML
    private Label desclabel;
    @FXML
    private Label datelabel;
    @FXML
    private Label labelimg;
    @FXML
    private Button btntraduire;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    @FXML
    public void annuler(){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Article.fxml"));
            Parent root = loader.load();
            ArticleController controller = loader.getController();
            tfimg.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    
    @FXML
    private void remplirCB(MouseEvent event) {
          
        try {
            String sql="select nom_jeux from jeux ";
            
            
            List<String> nm =new ArrayList<String>();
            PreparedStatement ste;
            Statement st;
            Connection cnx;
            cnx = MyDB.getInstance().getConnection();
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
                nm.add(rs.getString("nom_jeux"));
                   cbjeu.setItems(FXCollections.observableArrayList(nm));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void AjouterArticle(MouseEvent event){
        //take what s in the textfields
        java.util.Date dt = java.util.Date.from(tfdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String titre = tftitre.getText();
        String description = tfdescription.getText();
        String image = tfimg.getText();
        Date sqlDate = new Date(dt.getTime());
        //System.out.println(sqlDate);
        //combo box handling
         String nomjeu =  cbjeu.getValue();
                String sql1="select id from jeux where nom_jeux='"+nomjeu+"'";
                int id_jeu=0;
                  try {
                Statement ste;
                Connection cnx;
                cnx = MyDB.getInstance().getConnection();
                ste = cnx.prepareStatement(sql1);
                ResultSet rs = ste.executeQuery(sql1);
                if(rs.next())
                {
                  id_jeu =rs.getInt("id");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  System.out.println("heyhhh");
              //recuperer user a ajouter     int nom_jeux_id, String titre, String description, Date date, String image
             Articles aa = new Articles(id_jeu,titre,description,sqlDate,image);
             System.out.println("controle saisie");
             if(!(titre.isEmpty() || description.isEmpty() || image.isEmpty())){
             ars.ajouter(aa);
              }else{ Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Empty field !");
            a.show();}

    }

    @FXML
    private void traduire(ActionEvent event) {
    try {         
        titrelabel.setText(GoogleTranslate.translate("en",titrelabel.getText()));
        btntraduire.setText(GoogleTranslate.translate("en",btntraduire.getText()));
        labelaj.setText(GoogleTranslate.translate("en",labelaj.getText()));
        labelimg.setText(GoogleTranslate.translate("en",labelimg.getText()));
        desclabel.setText(GoogleTranslate.translate("en",desclabel.getText()));
        datelabel.setText(GoogleTranslate.translate("en",datelabel.getText()));
        jeulabel.setText(GoogleTranslate.translate("en",jeulabel.getText()));
        btnajouter.setText(GoogleTranslate.translate("en",btnajouter.getText()));
        btnannuler.setText(GoogleTranslate.translate("en",btnannuler.getText()));
                                        
        } catch (IOException ex) {
        Logger.getLogger(AjouterArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
