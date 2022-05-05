/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.jeux_service;
import com.darkprograms.speech.translator.GoogleTranslate;
import entities.Jeux;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
public class JeuxController implements Initializable {
 public static int cmd ; 
    @FXML
    private TableView<Jeux> tvJeux;
    @FXML
    private TableColumn<Jeux, Integer> colid;
    @FXML
    private TableColumn<Jeux, String> colnomjeu;
    @FXML
    private TableColumn<Jeux, String> colimagejeu;
    @FXML
    private TextField tfnomjeu;
    @FXML
    private TextField tfimg;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    ObservableList list ;
    @FXML
    private Label labeljeu;
    @FXML
    private Label labelimg;
    @FXML
    private Label labelgest;
    @FXML
    private Button tfreturn;
    @FXML
    private Button btntraduire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    
    
    @FXML
    private void Ajouter(ActionEvent event) {
    
        Jeux j = new Jeux();
        if(!(tfnomjeu.getText().isEmpty() || tfimg.getText().isEmpty() )){
             j.setNom_jeux(tfnomjeu.getText());
             j.setImage_jeux(tfimg.getText());    
            jeux_service ps = new jeux_service();
            ps.ajouter(j);
            afficher();
        }else{      
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Empty field !");
            a.show();
        }

    }
    public void afficher(){
        jeux_service ps = new jeux_service();
        List<Jeux> jeu = ps.recuperer();
        list = FXCollections.observableList(jeu);
        tvJeux.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnomjeu.setCellValueFactory(new PropertyValueFactory<>("nom_jeux"));
        colimagejeu.setCellValueFactory(new PropertyValueFactory<>("image_jeux"));
        
    }  
    
    @FXML
    public void Select(MouseEvent event){
        int index;
       // Role r = new Role();
        index = tvJeux.getSelectionModel().getSelectedIndex();
            cmd=colid.getCellData(index);
            System.out.println(cmd);
            String dmd=colnomjeu.getCellData(index);
            System.out.println(dmd);
            tfnomjeu.setText(dmd);
            String dmc=colimagejeu.getCellData(index);
            System.out.println(dmc);
            tfimg.setText(dmc);
    }
    @FXML
    public void update(ActionEvent event) {
    Jeux j = new Jeux();
       
        j.setImage_jeux(tfimg.getText()); 
        j.setNom_jeux(tfnomjeu.getText()); 
            jeux_service ps = new jeux_service();
            ps.modifier(j,cmd);
            afficher();
        }
    
    
                 @FXML
    public void RetrunInt(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TestInterface.fxml"));
            Parent root = loader.load();
            TestInterfaceController controller = loader.getController();
            btndelete.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    
    
        @FXML
    public void Delete(ActionEvent event) {
        jeux_service ps = new jeux_service();
        ps.supprimer(cmd);
        afficher();
    }

    @FXML
    private void traduire(ActionEvent event) throws IOException {
        btninsert.setText(GoogleTranslate.translate("en",btninsert.getText()));
        btndelete.setText(GoogleTranslate.translate("en",btndelete.getText()));
        btntraduire.setText(GoogleTranslate.translate("en",btntraduire.getText()));
        btnupdate.setText(GoogleTranslate.translate("en",btnupdate.getText()));
        labelgest.setText(GoogleTranslate.translate("en",labelgest.getText()));
        labelimg.setText(GoogleTranslate.translate("en",labelimg.getText()));
        labeljeu.setText(GoogleTranslate.translate("en",labeljeu.getText()));
        colid.setText(GoogleTranslate.translate("en",colid.getText()));
        colimagejeu.setText(GoogleTranslate.translate("en",colimagejeu.getText()));
        colnomjeu.setText(GoogleTranslate.translate("en",colnomjeu.getText()));
        
    }
}
