package com.ennoukra.gestionnairetaches.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ennoukra.gestionnairetaches.depot.DepotTaches;
import com.ennoukra.gestionnairetaches.modele.Tache;

import java.util.List;

// ViewModel exposant les tâches à la vue — survit aux rotations d'écran
public class TacheViewModel extends AndroidViewModel {

    private final DepotTaches depot;
    private final LiveData<List<Tache>> toutesLesTaches;

    public TacheViewModel(@NonNull Application application) {
        super(application);
        depot = new DepotTaches(application);
        toutesLesTaches = depot.getTout();
    }

    public void ajouter(Tache tache) {
        depot.ajouter(tache);
    }

    public void supprimer(Tache tache) {
        depot.supprimer(tache);
    }

    public void supprimerTout() {
        depot.supprimerTout();
    }

    public LiveData<List<Tache>> getToutesLesTaches() {
        return toutesLesTaches;
    }
}
