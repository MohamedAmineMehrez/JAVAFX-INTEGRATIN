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
public class Comment {
    private int id , id_article_id;
    private String commentaire;

    public Comment(int id, int id_article_id, String commentaire) {
        this.id = id;
        this.id_article_id = id_article_id;
        this.commentaire = commentaire;
    }

    public Comment(int id_article_id, String commentaire) {
        this.id_article_id = id_article_id;
        this.commentaire = commentaire;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_article_id() {
        return id_article_id;
    }

    public void setId_article_id(int id_article_id) {
        this.id_article_id = id_article_id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", id_article_id=" + id_article_id + ", commentaire=" + commentaire + '}';
    }


   
}
