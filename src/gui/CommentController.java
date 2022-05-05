/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.articles_service;
import Service.comment_service;
import Util.MyDB;
import com.darkprograms.speech.translator.GoogleTranslate;
import entities.Comment;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MOUEFEK
 */
public class CommentController implements Initializable {
    Connection connexion = MyDB.getInstance().getConnection();
    Statement stm ;
    public static int cmd;
    @FXML
    private TableView<Comment> tvcomment;
    @FXML
    private TableColumn<Comment, Integer> colid;
    @FXML
    private TableColumn<Comment, Integer> colarticle;
    @FXML
    private TableColumn<Comment, String> colcomment;
    @FXML
    private TextField tfsearch;
    ObservableList<Comment>list ;
    @FXML
    private Button btnretour;
    @FXML
    private Button btntraduire;
    @FXML
    private Label labelcomment;
    @FXML
    private Button btnsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        afficher();
    }    
    
     public void afficher(){
        comment_service ps = new comment_service();
        List<Comment> c = ps.recuperer();
        list = FXCollections.observableList(c);
        tvcomment.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("id_article_id"));
        colcomment.setCellValueFactory(new PropertyValueFactory<>("commentaire")); 
    }
     
    @FXML
      public void Select(MouseEvent event){
        int index;
        index = tvcomment.getSelectionModel().getSelectedIndex();
        cmd=colid.getCellData(index);
        System.out.println(cmd);
    }
    
    
    @FXML
    public void Delete(ActionEvent event) {
        comment_service ps = new comment_service();
        ps.supprimer(cmd);
        afficher();
    }
    
    @FXML
    public void searchRole(){         
    list.clear();
    String sql = "Select * from comment where commentaire like '%"+tfsearch.getText()+"%'";
                          
    try {
              
        PreparedStatement pst = connexion.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id")+rs.getString("commentaire"));
                list.add(new Comment(rs.getInt("id"),rs.getString("commentaire")));
            }
            tvcomment.setItems(list);

        }catch (SQLException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);}
    }   

    @FXML
    private void retourinterface(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TestInterface.fxml"));
            Parent root = loader.load();
            TestInterfaceController controller = loader.getController();
            tvcomment.getScene().setRoot(root);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void traduire(ActionEvent event) throws IOException {
        btnretour.setText(GoogleTranslate.translate("en",btnretour.getText()));
        btntraduire.setText(GoogleTranslate.translate("en",btntraduire.getText()));
        btnsupprimer.setText(GoogleTranslate.translate("en",btnsupprimer.getText()));
        labelcomment.setText(GoogleTranslate.translate("en",labelcomment.getText()));
        colarticle.setText(GoogleTranslate.translate("en",colarticle.getText()));
        colcomment.setText(GoogleTranslate.translate("en",colcomment.getText()));       
    }
}
