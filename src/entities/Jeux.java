/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MOUEFEK
 */
public class Jeux {
    private int id;
    private String nom_jeux, image_jeux;
    
    
public Jeux(int id, String nom_jeux, String image_jeux) {
        this.id = id;
        this.nom_jeux = nom_jeux;
        this.image_jeux = image_jeux;
}

public Jeux(String nom_jeux, String image_jeux) {
        this.nom_jeux = nom_jeux;
        this.image_jeux = image_jeux;
}

public Jeux() {
    
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_jeux() {
        return nom_jeux;
    }

    public void setNom_jeux(String nom_jeux) {
        this.nom_jeux = nom_jeux;
    }

    public String getImage_jeux() {
        return image_jeux;
    }

    public void setImage_jeux(String image_jeux) {
        this.image_jeux = image_jeux;
    }

    @Override
    public String toString() {
        return "Jeux{" + "id=" + id + ", nom_jeux=" + nom_jeux + ", image_jeux=" + image_jeux + '}';
    }   
}
