/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Jeux;
import entities.Articles;
import Service.jeux_service;
import Service.articles_service;
import Service.comment_service;
import java.sql.SQLException;

/**
 *
 * @author MOUEFEK
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        //jeux_service p = new jeux_service();
        comment_service c = new comment_service();
        //articles_service ar = new articles_service();
        //p.ajouter(new Jeux("testing","tekken-7-650x366-62162f6a7610d-6224f3988157e.png"));
        //p.supprimer(3);
        //p.modifier(new Jeux("ahla","xxxxxxxx"),41);
        //ar.supprimer(57);
        //ar.modifier(new Articles(1,"blo","caping","2000-01-22","tekken-7-650x366-62162f6a7610d-6224f3988157e.png"), 8);
    //ar.ajouter(new Articles(2,"hallo","mdying",1245/02/21,"rl-season2series-core-1-e84a9f5c1e622635a7576cba638d36f5-62162ecf2acd5-6224f0cf56366.jpeg"));
        //System.out.println(ar.recuperer());
        //System.out.println(p.recuperer());
        System.out.println(c.recuperer());
        
    }
}
