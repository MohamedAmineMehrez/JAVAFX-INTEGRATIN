/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.UserService;
import Util.MyDB;
import entities.Role;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
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
 * @author EXTRA
 */
public class UserController implements Initializable {
    Connection connexion=MyDB.getInstance().getConnection();
    Statement stm;
public static int cmd;
    @FXML
    private TableView<User> tvUser;
    @FXML
    private TableColumn<User, Integer> colid;
    @FXML
    private TableColumn<User, String> colnom;
    @FXML
    private TableColumn<User, String> colprenom;
    @FXML
    private TableColumn<User, String> colrole;
    @FXML
    private TableColumn<User, String> colemail;
    @FXML
    private TableColumn<User, String> colpseudo;
    @FXML
    private TableColumn<User, String> colpassword;
    @FXML
    private TableColumn<User, Integer> colnumero;
    @FXML
    private TableColumn<User, String> coladresse;
     @FXML
    private Button btnSupprimer;
     
     ObservableList<User> list ;
    @FXML
    private Button modiferbtn;
    @FXML
    private TextField recherchetf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    
    public void afficher(){
         UserService ps = new UserService();
        List<User> users = ps.recuperer();
        list = FXCollections.observableList(users);
        tvUser.setItems(list);      
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("nom_role_id"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("address_loc"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("num_telf"));
    }
    
    @FXML
    public void Select(MouseEvent event){
        int index;
         index = tvUser.getSelectionModel().getSelectedIndex();
          cmd = colid.getCellData(index);
            System.out.println(cmd); 
    }
     @FXML 
    public void Delete(ActionEvent event){
       UserService rs=new UserService()   ;
       rs.supprimer(cmd);
       afficher();
    }
         @FXML 
    public void Modifier(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
            Parent root = loader.load();
            ModifierUserController controller = loader.getController();
            tvUser.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
            try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestInterface.fxml"));
        Parent root = loader.load();
        TestInterfaceController controller = loader.getController();
        tvUser.getScene().setRoot(root);
    } catch (IOException ex) {
        Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @FXML
     public void searchUser(){
        
          
                
           list.clear();
           String sql = "Select * from user where nom like '%"+recherchetf.getText()+"%'"
           + " UNION Select * from user where prenom like '%"+recherchetf.getText()+"%'"
           + " UNION Select * from user where pseudo like '%"+recherchetf.getText()+"%'"
           + " UNION Select * from user where email like '%"+recherchetf.getText()+"%'"
           + " UNION Select * from user where num_telf like '%"+recherchetf.getText()+"%'"
           + " UNION Select * from user where address_loc like '%"+recherchetf.getText()+"%'";
           
                      
          try {
              
               
                PreparedStatement pst = connexion.prepareStatement(sql);
                     ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        System.out.println(""+rs.getString("email"));
                        list.add(new User(rs.getInt("id"),rs.getInt("nom_role_id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("pseudo"),rs.getString("password"),rs.getString("email"),rs.getInt("num_telf"),rs.getString("address_loc")));
                    }
                    tvUser.setItems(list);

          }catch (SQLException ex) {
              Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);}
           }
}
