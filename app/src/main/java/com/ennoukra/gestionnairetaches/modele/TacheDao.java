package com.ennoukra.gestionnairetaches.modele;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// Interface DAO Room — définit les opérations SQL sur la table des tâches
@Dao
public interface TacheDao {

    @Insert
    void ajouterTache(Tache tache);

    @Delete
    void supprimerTache(Tache tache);

    @Query("DELETE FROM taches")
    void supprimerToutes();

    @Query("SELECT * FROM taches ORDER BY dateCreation DESC")
    LiveData<List<Tache>> recupererToutes();
}
