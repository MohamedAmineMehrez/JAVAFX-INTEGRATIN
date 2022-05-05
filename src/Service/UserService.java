/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.MyDB;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EXTRA
 */
public class UserService implements IService<User> {
    Connection connection;
    Statement stm;
    public UserService(){
     connection=MyDB.getInstance().getConnection();
}

    @Override
    public void ajouter(User t) {
       try {
       /* String req = "insert into role(libille) "
                + "values('"+t.getLibille()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
        String req1="insert into user (nom_role_id,nom,prenom,pseudo,password,email,num_telf,address_loc) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(req1);
        if((t.getNom()==null)||(t.getPrenom()==null)||(t.getEmail()==null)||(t.getPassword()==null)||(t.getAddress_loc()==null)||(t.getPseudo()==null))
                  System.out.println("please fill out the informations");
        else {
        ps.setString(2, t.getNom());
        ps.setString(3, t.getPrenom());
        ps.setString(4, t.getPseudo());
        ps.setString(5, t.getPassword());
        ps.setString(6, t.getEmail());
        ps.setString(8, t.getAddress_loc());
        ps.setInt(7, t.getNum_telf());
        ps.setInt(1, t.getNom_role_id());
        ps.executeUpdate();
    }} catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    }
    @Override
    public void modifier(User t,int id) {
                try{
         String req = "UPDATE user set nom_role_id = '" +t.getNom_role_id()+ "', nom = '" +t.getNom()+ "' , prenom='" +t.getPrenom()+ "',pseudo='"+t.getPseudo()+"',email='"+t.getEmail()+"',password='"+t.getPassword()+"',address_loc='"+t.getAddress_loc()+"',num_telf='"+t.getNum_telf()+"'  where `id`='"+ id+ "' ";
        stm = connection.createStatement();
        //if((t.getNom()==null)||(t.getPrenom()==null)||(t.getEmail()==null)||(t.getPassword()==null)||(t.getAddress_loc()==null)||(t.getPseudo()==null))
          //        System.out.println("please fill out the informations");
       // else {}
        stm.executeUpdate(req);}
        catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    @Override
    public void supprimer(int id) {
         try{
       String req = "delete from user where  `id`='"+id+"'";
        stm = connection.createStatement();
        
        stm.executeUpdate(req);
        }catch(SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<User> recuperer() {
              List<User> list = new ArrayList<>();
        try{
            String req = "select * from user";
            Statement st = connection.createStatement();
    
        ResultSet rs=st.executeQuery(req);
        while(rs.next())
        {
            User p = new User();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setPseudo(rs.getString("pseudo"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
            p.setNom_role_id(rs.getInt("nom_role_id"));
           // String req1 = "select libille from role where `id`='"+p.getNom_role_id()+"'";
           // ResultSet ps=st.executeQuery(req1);
            // String k = ps.getString(req1);
          //  p.setNom_role(k);
            p.setAddress_loc(rs.getString("address_loc"));
            p.setNum_telf(rs.getInt("num_telf"));
            list.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return list;
    }
    public List<String> afficher_email() throws SQLException {
        List<String> arr = new ArrayList<>();
        stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("select * from user ;");
        while (rs.next()) {
            String email = "" + rs.getString("email");
            arr.add(email);
        }
        return arr;
    }
     public List<String> afficher_mobile() throws SQLException {
        List<String> arr = new ArrayList<>();
        stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("select * from user ;");
        while (rs.next()) {
            String tels = rs.getString("num_telf");
            arr.add(tels);
        }
        return arr;
    }
     public void SignInWithCode(String mail, String code) throws SQLException {
        stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM user WHERE  email = '" + mail + "' and password = '" + code + "'");
        User user = new User();
        if (rst != null) {
            while (rst.next()) {
                user.setId(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
               // user.setRole(rst.getString("role"));
               // user.setTel(rst.getInt("telf"));
                user.setEmail(rst.getString("email"));
                user.setPassword(rst.getString("password"));
            }
        }
    }
       public void SignIn(String mail, String password) throws SQLException {
        stm = connection.createStatement();
       
        ResultSet rst = stm.executeQuery("SELECT * FROM user WHERE  email = '" + mail + "' and password = '" + password + "'");
        User user = new User();
        if (rst != null) {
            while (rst.next()) {

                user.setId(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
              //  user.setRole(rst.getString("role"));
             //   user.setTel(rst.getInt("telf"));
                user.setEmail(rst.getString("email"));
                user.setPassword(rst.getString("password"));

            }
        }
    }

    }

