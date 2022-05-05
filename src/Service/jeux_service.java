/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Service.Ipifx;
import Util.MyDB;
import entities.Jeux;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author MOUEFEK
 */
public class jeux_service implements Ipifx<Jeux> {
Connection connection;
    private Statement stm;

public jeux_service(){
    connection=MyDB.getInstance().getConnection();
}

    @Override
    public void ajouter(Jeux t) {
            try {
      /*  String req = "insert into personne(nom,prenom,age) "
                + "values('"+t.getNom()+"','"+t.getPrenom()+"',"+t.getAge()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
        String req1="insert into jeux (nom_jeux,image_jeu) values(?,?)";
        PreparedStatement ps=connection.prepareStatement(req1);
        ps.setString(1, t.getNom_jeux());
        ps.setString(2, t.getImage_jeux());
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    }

    @Override
    public void modifier(Jeux t , int id) {
        try{
        String req = "UPDATE jeux set nom_jeux = '" +t.getNom_jeux()+ "' , image_jeu = '" +t.getImage_jeux()+"' where `id`='"+id+"' ";
        stm = connection.createStatement();
        stm.executeUpdate(req);
        }catch(SQLException ex ){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
               try{
        String req = "delete from jeux  where `id`='"+id+"' ";
        stm = connection.createStatement();
        stm.executeUpdate(req);
        }catch(SQLException ex ){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Jeux> recuperer() {
            List<Jeux> list = new ArrayList<Jeux>();
        try{
            String req = "select * from jeux";
            Statement st = connection.createStatement();
    
        ResultSet rs=st.executeQuery(req);
        while(rs.next())
        {
            Jeux j = new Jeux();
            j.setId(rs.getInt("id"));
            j.setNom_jeux(rs.getString("nom_jeux"));
            j.setImage_jeux(rs.getString("image_jeu"));
            list.add(j);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return list;
    }

    
}
