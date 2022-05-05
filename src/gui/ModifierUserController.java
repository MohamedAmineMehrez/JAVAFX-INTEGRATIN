/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.UserService;
import Util.MyDB;
import entities.User;
import static gui.UserController.cmd;
import java.awt.BorderLayout;
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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class ModifierUserController implements Initializable {
  UserService ps = new UserService();
    @FXML
    private Button modifierbtn;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField tfnom;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void ModifierUser(MouseEvent event) {
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
                  System.out.println(id_role);
             User u = new User(id_role,nom,prenom,pseudo,password,email,telint,adresse);
          
            ps.modifier(u,cmd);
    }

    @FXML
    private void Annuler(ActionEvent event) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = loader.load();
            UserController controller = loader.getController();
            tfemail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
   
    
    
}
