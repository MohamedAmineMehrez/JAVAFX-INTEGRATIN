/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.UserService;
import Util.MyDB;
import static Util.MyDB.conDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import Util.Ticker;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class LoginController implements Initializable {
    public static User u;
    public static int role;
    public static String email2;
    Statement stm;
     UserService us = new UserService();
    private ResultSet rs;
    @FXML
    private Label lblErrors;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnlogin;
    

    /**
     * Initializes the controller class.
     */
      Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Button creerbtn;
    @FXML
    private Label emailsent1;
     
    public String generate(int id) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = id;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println("code:"+generatedString);

        return generatedString;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           // TODO
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
           
        }
    }    
    public LoginController() {
        con =MyDB. conDB();
    }
    @FXML
      public void handleButtonAction(MouseEvent event) throws SQLException {

        if (event.getSource() == btnlogin) {
            //login here
            if (logIn().equals("Success")) {
                try {
                    email2=email.getText();
                   String sql="SELECT * FROM user Where email ='"+email2+"';";
                    Statement st = con.createStatement();
                    ResultSet rs=st.executeQuery(sql);
                    while(rs.next()){
                        role=rs.getInt("nom_role_id");
                        System.out.println(role);
                    }
                    if(role==1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TestInterface.fxml"));
            Parent root = loader.load();
            TestInterfaceController controller=loader.getController();
            email.getScene().setRoot(root);}
                    else{
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterUser.fxml"));
            Parent root = loader.load();
            AjouterUserController controller = loader.getController();
            creerbtn.getScene().setRoot(root);
           
                    }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

            }
        }
    }

    private String logIn() {
        String status = "";
        String email1 = email.getText();
        String password1 = password.getText();
        if(email1.isEmpty() || password1.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM user Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email1);
                preparedStatement.setString(2, password1);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                   
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    while(resultSet.next()){
                     //u=new User();
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setPseudo(rs.getString("pseudo"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setNom_role_id(rs.getInt("nom_role_id"));
           role=rs.getInt("nom_role_id");
                        System.out.println(u.getNom_role_id());
                        System.out.println(role);
            u.setAddress_loc(rs.getString("address_loc"));
            u.setNum_telf(rs.getInt("num_telf"));}
                     status = "Success";
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    @FXML
    private void createUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterUser.fxml"));
            Parent root = loader.load();
            AjouterUserController controller = loader.getController();
            creerbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 @FXML
    public void forgot_password(ActionEvent event) throws SQLException {
        String login = email.getText();
        String res = "";
        TextInputDialog dialog = new TextInputDialog(login);
        dialog.setTitle("Changing password");
        dialog.setHeaderText("Your password will be reset, confirm");
        dialog.setContentText("Please enter your email");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            res = result.get();

            if (us.afficher_email().contains(res)) {
                String rand = generate(7);
                SendingMail sm = new SendingMail("This is your account redemption code : (Note: the code will expire in 2 hours) " + rand, res, "Redeem your account");
                SendingMail.sendMail();
                //String sql="update user set points = '" + rand + "' where email = '" + res + "';";
              //  System.out.println(sql);
               // stm = connexion.createStatement();
               // stm.executeUpdate(sql);
                con.createStatement().execute("update `user` set `points` = '" + rand + "' where `email` = '" + res + "';");
                Ticker t = new Ticker(7200, res);
                System.out.println(t.toString());
                
                emailsent1.setTextFill(Color.web("#34ff2d"));
                emailsent1.setText("Check your email.");
                email2=res;
                System.out.println(email2);
                 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Codeverif.fxml"));
            Parent root = loader.load();
            CodeverifController controller = loader.getController();
            creerbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
                
            } else {
                emailsent1.setTextFill(Color.web("#ed0e0e"));
                emailsent1.setText("Entered email does not exist.");
            }

        }

        email.clear();
        password.clear();

    }

    public class Ticker {

        Timer timer;
        String login = email.getText();

        public Ticker(int seconds, String s) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
            login = s;
        }

        @Override
        public String toString() {
            return "Ticker{" + "timer=" + timer + ", login=" + login + '}';
        }
        

        class RemindTask extends TimerTask {

            @Override
            public void run() {
               
                    timer.cancel(); //Terminate the timer thread
                try {
                    con.createStatement().execute("update `user` set `points` = NULL where `email` = '" + login + "';");
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println("operation succeeded");
              
            }
        }
}
}
