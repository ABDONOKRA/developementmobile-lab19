package com.ennoukra.gestionnairetaches.depot;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ennoukra.gestionnairetaches.modele.BaseDonnees;
import com.ennoukra.gestionnairetaches.modele.Tache;
import com.ennoukra.gestionnairetaches.modele.TacheDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Dépôt central — isole le ViewModel de la source de données
public class DepotTaches {

    private final TacheDao dao;
    private final LiveData<List<Tache>> toutes;
    private final ExecutorService executeur;

    public DepotTaches(Application app) {
        BaseDonnees bd = BaseDonnees.getInstance(app);
        dao = bd.tacheDao();
        toutes = dao.recupererToutes();
        executeur = Executors.newSingleThreadExecutor();
    }

    public void ajouter(Tache tache) {
        executeur.execute(() -> dao.ajouterTache(tache));
    }

    public void supprimer(Tache tache) {
        executeur.execute(() -> dao.supprimerTache(tache));
    }

    public void supprimerTout() {
        executeur.execute(dao::supprimerToutes);
    }

    public LiveData<List<Tache>> getTout() {
        return toutes;
    }
}
