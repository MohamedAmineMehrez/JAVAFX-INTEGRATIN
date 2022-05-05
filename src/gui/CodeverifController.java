/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import static gui.LoginController.email2;
import Service.UserService;
import Util.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class CodeverifController implements Initializable {

    @FXML
    private TextField codetf;
    @FXML
    private Button verifierbtn;
     Statement stm;
     UserService us = new UserService();
    private ResultSet rs;
Connection con = MyDB.getInstance().getConnection();;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet ;
    int b=1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void verifier(ActionEvent event) throws IOException  {
        String code=codetf.getText();
        System.out.println(code);
      String sql = "SELECT * FROM `user` WHERE points='"+code+"';";
      
         try {
                stm = con.createStatement();
                resultSet = stm.executeQuery(sql);
                if(!resultSet.next())  {
                     
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Echec ");
            
                 alert.setContentText("Donner le code envoye vers "+email2);
                 codetf.clear();
                } else {
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("succes");
                alert.setContentText("code verifier ");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPassword.fxml"));
                Parent root;
                root = loader.load();
                NewPasswordController controller = loader.getController();
            codetf.getScene().setRoot(root);
                    
            
            //This line gets the Stage information
  
           
                }
            
               
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
               
            }
    }
    
}
