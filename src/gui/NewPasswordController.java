/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import static gui.LoginController.email2;
import Service.UserService;
import Util.MyDB;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class NewPasswordController implements Initializable {
 UserService us = new UserService();
    User u = new User();
    private Connection con;
    private Statement ste;
    @FXML
    private TextField passwordtf;
    @FXML
    private Button resetbtn;
    @FXML
    private TextField comfirmtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(email2);
    }    
      public NewPasswordController() {
        con = MyDB.getInstance().getConnection();
    }

    @FXML
    private void changepass(ActionEvent event) throws SQLException, IOException {
        String pas = passwordtf.getText();
        System.out.println(pas);
        String confirm = comfirmtf.getText();
        /*
            *
            *test on password length
            *
         */
        if (pas.length() < 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Password too short, minimum length 5 characters");
            alert.showAndWait();
            passwordtf.clear();
            comfirmtf.clear();
            return;
        }
        /*
        *
        *testing the match between the two passwords
        *
         */
        if (!confirm.equals(pas)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Passwords don't match");
            alert.showAndWait();

            return;
        } else {
            try {
                System.out.println(email2);
                System.out.println(u.getEmail());
                 System.out.println(pas);
                con.createStatement().executeUpdate("update user SET points = NULL, password = '"+pas+"'  WHERE email = '" +email2 + "';");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("succes");
            
                 alert.setContentText("Password changed");
                alert.showAndWait();
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
                return;
            } catch (Exception e) {
                System.out.println("erreur");
            }

            
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException, SQLException {
       // us.SignOut();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
