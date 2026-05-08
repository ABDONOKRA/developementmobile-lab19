package com.ennoukra.gestionnairetaches.modele;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Entité Room représentant une tâche persistée en base locale
@Entity(tableName = "taches")
public class Tache {

    @PrimaryKey(autoGenerate = true)
    private int identifiant;

    private String libelle;
    private String contenu;
    private long dateCreation;

    public Tache(String libelle, String contenu, long dateCreation) {
        this.libelle = libelle;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
    }

    public int getIdentifiant() { return identifiant; }
    public void setIdentifiant(int identifiant) { this.identifiant = identifiant; }

    public String getLibelle() { return libelle; }
    public String getContenu() { return contenu; }
    public long getDateCreation() { return dateCreation; }
}
