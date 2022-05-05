/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Service.RoleService;
import entities.Role;
import entities.User;
import Service.UserService;

/**
 *
 * @author EXTRA
 */
public class PIDEV {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        RoleService ps = new RoleService();
        UserService us = new UserService();
       // ps.ajouter(new Role());
       // ps.modifier(new Role("test5"),9);
      // ps.supprimer(8);
      us.ajouter(new User());
    // us.ajouter(new User(1,"mehrez","moueefek","ezyyy","12345678","mehrez1545@gmail.com",12345678,"binzerte"));
      us.modifier(new User(2,"mehferez","mohafemed","ezfey","98765fefe4321","mehrezedcfedcfhlk1111@gmail.com",12345678,"binzerte"), 11);
      // us.supprimer(5);
       // System.out.println(ps.recuperer());
        System.out.println(us.recuperer());
    }
    
}
