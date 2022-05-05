/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Service.UserService;
import Util.MyDB;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//salam
/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class AjouterUserController implements Initializable {
     private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    // static Pattern object, since pattern is fixed
    private static Pattern pattern;

    // non-static Matcher object because it's created from the input String
    private Matcher matcher;
    User u = new User();
    UserService us = new UserService();
    @FXML
    private Button btnAnnuler;
    @FXML
     TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfpseudo;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfadresse;
    @FXML
    private ComboBox<String> cbrole;
    @FXML
    private Button Ajouterbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void Annuler(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = loader.load();
            UserController controller = loader.getController();
            tfemail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @FXML
    private void remplirCB(MouseEvent event) {
          
        try {
            String sql="select libille from role ";
            
            
            List<String> nm =new ArrayList<String>();
            PreparedStatement ste;
            Statement st;
            Connection cnx;
            cnx = MyDB.getInstance().getConnection();
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
                nm.add(rs.getString("libille"));
                   cbrole.setItems(FXCollections.observableArrayList(nm));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    @FXML
    private void AjouterUser(MouseEvent event){
        int email1=1,tel1=1;
        //take what s in the textfields
                String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String email = tfemail.getText();
        String pseudo = tfpseudo.getText();
        String password = pfpassword.getText();
        String adresse = tfadresse.getText();
        String tel = tfnumero.getText();
        Integer telint = Integer.parseInt(tel);
        
        //combo box handling
         String nomrole =  cbrole.getValue();
                String sql1="select id from role where libille='"+nomrole+"'";
                int id_role=0;
                  try {
                Statement ste;
                Connection cnx;
                cnx = MyDB.getInstance().getConnection();
                ste = cnx.prepareStatement(sql1);
                ResultSet rs = ste.executeQuery(sql1);
                if(rs.next())
                {
                  id_role =rs.getInt("id");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
             
           //  test on email existance
             
             if (us.afficher_email().contains(email) == true) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Failure");
                 alert.setHeaderText(null);
                 alert.setContentText("Email already exists");
                 alert.showAndWait();
                 tfemail.clear();
                 
                 //recuperer user a ajouter
                 
                 
             } 
         } catch (SQLException ex) {
             Logger.getLogger(AjouterUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
        if (pfpassword.getText().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Password too short, minimum length 8 characters");
                alert.showAndWait();
                pfpassword.clear();
               
                return;
            }
        if (tfnumero.getText().length() < 8 || tfnumero.getText().length() > 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("numero telephone invalid!");
                alert.showAndWait();
                tfnumero.clear();
                return;
            }
         try {
             /*
             test on phone number existance
             */
             if (us.afficher_mobile().contains(tel) == true) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Failure");
                 alert.setHeaderText(null);
                 alert.setContentText("Telephone number already exists");
                 alert.showAndWait();
                 tfnumero.clear();
                 tel1=0;
                 return;
             }
         } catch (SQLException ex) {
             Logger.getLogger(AjouterUserController.class.getName()).log(Level.SEVERE, null, ex);
         }
        if(!(tfemail.getText().matches("^(.+)@(.+)$")))
        {Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Failure");
                 alert.setHeaderText(null);
                 alert.setContentText("Format d'Email incorrect !!!");
                 alert.showAndWait();
                 tfemail.clear();
                 email1=0;
        }
        else {
User user = new User(id_role,nom,prenom,pseudo,password,email,telint,adresse);
                 us.ajouter(user);
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setTitle("Felicitation");
                 alert.setHeaderText(null);
                 alert.setContentText("Votre compte a été créer");
                 alert.showAndWait();
                  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            tfemail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
                 
        }
    
}

    @FXML
    private void Retour(ActionEvent event) {
            try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        tfemail.getScene().setRoot(root);
    } catch (IOException ex) {
        Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
