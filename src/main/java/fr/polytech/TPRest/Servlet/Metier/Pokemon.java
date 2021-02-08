package fr.polytech.TPRest.Servlet.Metier;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pokemon" )
public class Pokemon implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private int level;




    public Pokemon() {
    }

    public Pokemon(int id, String nom, int level) {
        this.id = id;
        this.nom = nom;
        this.level = level;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

