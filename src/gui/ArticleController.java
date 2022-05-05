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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MOUEFEK
 */
public class ArticleController implements Initializable {
    Connection connexion = MyDB.getInstance().getConnection();
    Statement stm ;
public static int cmd;
    @FXML
    private TableView<Articles> tvarticle;
    @FXML
    private TableColumn<Articles, Integer> colid;
    @FXML
    private TableColumn<Articles, Integer> colnomjeu;
    @FXML
    private TableColumn<Articles, String> coltitre;
    @FXML
    private TableColumn<Articles, String> coldescription;
    @FXML
    private TableColumn<Articles, Date> coldate;
    @FXML
    private TableColumn<Articles, String> colimg;
    ObservableList<Articles>list ;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btninsert;
    @FXML
    private Button btwreturn;
    @FXML
    private TextField tfsearch;
    @FXML
    private Button btntraduire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        afficher();
    }    
    
        public void afficher(){
        articles_service ps = new articles_service();
        List<Articles> ar = ps.recuperer();
        list = FXCollections.observableList(ar);
        tvarticle.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnomjeu.setCellValueFactory(new PropertyValueFactory<>("nom_jeux_id"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colimg.setCellValueFactory(new PropertyValueFactory<>("image"));   
    } 
        
        @FXML
    public void modifier(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierArticle.fxml"));
            Parent root = loader.load();
            ModifierArticleController controller = loader.getController();
            tvarticle.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    @FXML
    public void interfaceajouterArticle(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterArticle.fxml"));
            Parent root = loader.load();
            AjouterArticleController controller = loader.getController();
            tvarticle.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    public void RetrunInterface(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TestInterface.fxml"));
            Parent root = loader.load();
            TestInterfaceController controller = loader.getController();
            tvarticle.getScene().setRoot(root);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }    
        
    @FXML
    public void Select(MouseEvent event){
        int index;
        index = tvarticle.getSelectionModel().getSelectedIndex();
        cmd=colid.getCellData(index);
        System.out.println(cmd);
    }
    
    @FXML
    public void Delete(ActionEvent event) {
        articles_service ps = new articles_service();
        ps.supprimer(cmd);
        afficher();
    } 
    
    @FXML
        public void searchRole(){      
    list.clear();
    String sql = "Select * from articles where titre like '%"+tfsearch.getText()+"%'"
    + " UNION Select * from articles where description like '%"+tfsearch.getText()+"%'"
    + " UNION Select * from articles where image like '%"+tfsearch.getText()+"%'";
                          
    try {
              
        PreparedStatement pst = connexion.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id")+rs.getString("titre")+rs.getString("description"));
                list.add(new Articles(rs.getInt("id"),rs.getInt("nom_jeux_id"),rs.getString("titre"),rs.getString("description"),rs.getString("image")));
            }
            tvarticle.setItems(list);

        }catch (SQLException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);}
    }

    @FXML
    private void traduire(ActionEvent event) throws IOException {
        btntraduire.setText(GoogleTranslate.translate("en",btntraduire.getText()));
        btndelete.setText(GoogleTranslate.translate("en",btndelete.getText()));
        btninsert.setText(GoogleTranslate.translate("en",btninsert.getText()));
        btnmodifier.setText(GoogleTranslate.translate("en",btnmodifier.getText()));
        colid.setText(GoogleTranslate.translate("en",colid.getText()));
        coldate.setText(GoogleTranslate.translate("en",coldate.getText()));
        coldescription.setText(GoogleTranslate.translate("en",coldescription.getText()));
        colimg.setText(GoogleTranslate.translate("en",colimg.getText()));
        colnomjeu.setText(GoogleTranslate.translate("en",colnomjeu.getText()));
        coltitre.setText(GoogleTranslate.translate("en",coltitre.getText()));
        btwreturn.setText(GoogleTranslate.translate("en",btwreturn.getText()));
        
    }
    
    
}
