/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.darkprograms.speech.translator.GoogleTranslate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MOUEFEK
 */
public class TestInterfaceController implements Initializable {

    @FXML
    private Button btnArticle;
    @FXML
    private Button btnJeux;
    @FXML
    private Button btncommentaire;
    @FXML
    private Button btntraduire;
    @FXML
    private Label welcomelabel;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnrole;
    @FXML
    private Button btnproduit;
    @FXML
    private Button btntournoi;
    @FXML
    private Button btncommande;
    @FXML
    private Button btnlogout;
     // i need help
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void redirectArticle(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Article.fxml"));
            Parent root = loader.load();
            ArticleController controller = loader.getController();
            btnArticle.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
        @FXML
    public void redirectJeux(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Jeux.fxml"));
            Parent root = loader.load();
            JeuxController controller = loader.getController();
            btnJeux.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectioncommentaire(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("comment.fxml"));
            Parent root = loader.load();
            CommentController controller = loader.getController();
            btnJeux.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void traduire(ActionEvent event) throws IOException {
        btntraduire.setText(GoogleTranslate.translate("en",btntraduire.getText()));
        btnJeux.setText(GoogleTranslate.translate("en",btnJeux.getText()));
        btncommentaire.setText(GoogleTranslate.translate("en",btncommentaire.getText()));
        btnArticle.setText(GoogleTranslate.translate("en",btnArticle.getText()));
        welcomelabel.setText(GoogleTranslate.translate("en",welcomelabel.getText()));
        btncommentaire.setText(GoogleTranslate.translate("en",btncommentaire.getText()));
        btnuser.setText(GoogleTranslate.translate("en",btnuser.getText()));
        btntournoi.setText(GoogleTranslate.translate("en",btntournoi.getText()));
        btncommande.setText(GoogleTranslate.translate("en",btncommande.getText()));
        btnrole.setText(GoogleTranslate.translate("en",btnrole.getText()));
        btnproduit.setText(GoogleTranslate.translate("en",btnproduit.getText()));
    }

    @FXML
    private void onclickuser(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = loader.load();
            UserController controller = loader.getController();
            btnuser.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onclickRole(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Role.fxml"));
            Parent root = loader.load();
            RoleController controller = loader.getController();
            btnrole.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            btnuser.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
        
    }
    
}
