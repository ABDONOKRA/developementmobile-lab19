package com.ennoukra.gestionnairetaches.vue;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ennoukra.gestionnairetaches.R;
import com.ennoukra.gestionnairetaches.modele.Tache;
import com.ennoukra.gestionnairetaches.presentation.TacheViewModel;

// Activité principale — point d'entrée de l'interface utilisateur
public class AccueilActivite extends AppCompatActivity {

    private static final int LONGUEUR_MAX_LIBELLE = 60;
    private static final int LONGUEUR_MAX_CONTENU = 300;

    private TacheViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        EditText champLibelle = findViewById(R.id.champLibelle);
        EditText champContenu = findViewById(R.id.champContenu);
        Button btnAjouter = findViewById(R.id.btnAjouter);
        Button btnSupprimerTout = findViewById(R.id.btnSupprimerTout);
        RecyclerView liste = findViewById(R.id.listeTaches);

        liste.setLayoutManager(new LinearLayoutManager(this));
        liste.setHasFixedSize(true);

        AdaptateurTaches adaptateur = new AdaptateurTaches();
        liste.setAdapter(adaptateur);

        viewModel = new ViewModelProvider(this).get(TacheViewModel.class);
        viewModel.getToutesLesTaches().observe(this, adaptateur::mettreAJour);

        adaptateur.setOnSupprimerListener(tache -> viewModel.supprimer(tache));

        btnAjouter.setOnClickListener(v -> ajouterTache(champLibelle, champContenu));
        btnSupprimerTout.setOnClickListener(v -> confirmerSuppression());
    }

    private void ajouterTache(EditText champLibelle, EditText champContenu) {
        String libelle = champLibelle.getText().toString().trim();
        String contenu = champContenu.getText().toString().trim();

        if (TextUtils.isEmpty(libelle) || TextUtils.isEmpty(contenu)) {
            Toast.makeText(this, getString(R.string.erreur_champs_vides), Toast.LENGTH_SHORT).show();
            return;
        }

        if (libelle.length() > LONGUEUR_MAX_LIBELLE) {
            Toast.makeText(this, getString(R.string.erreur_libelle_long), Toast.LENGTH_SHORT).show();
            return;
        }

        if (contenu.length() > LONGUEUR_MAX_CONTENU) {
            Toast.makeText(this, getString(R.string.erreur_contenu_long), Toast.LENGTH_SHORT).show();
            return;
        }

        Tache tache = new Tache(libelle, contenu, System.currentTimeMillis());
        viewModel.ajouter(tache);

        champLibelle.setText("");
        champContenu.setText("");
    }

    private void confirmerSuppression() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.titre_confirmation))
                .setMessage(getString(R.string.message_confirmation_suppression))
                .setPositiveButton(getString(R.string.oui), (d, w) -> viewModel.supprimerTout())
                .setNegativeButton(getString(R.string.non), null)
                .show();
    }
}
