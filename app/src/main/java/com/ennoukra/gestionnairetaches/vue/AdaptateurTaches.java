package com.ennoukra.gestionnairetaches.vue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ennoukra.gestionnairetaches.R;
import com.ennoukra.gestionnairetaches.modele.Tache;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

// Adaptateur RecyclerView — utilise DiffUtil pour éviter les mises à jour brutales
public class AdaptateurTaches extends RecyclerView.Adapter<AdaptateurTaches.VueTache> {

    private List<Tache> taches = new ArrayList<>();
    private OnSupprimerListener ecouteurSuppression;

    private final SimpleDateFormat formatDate =
            new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);

    public interface OnSupprimerListener {
        void onSupprimer(Tache tache);
    }

    public void setOnSupprimerListener(OnSupprimerListener ecouteur) {
        this.ecouteurSuppression = ecouteur;
    }

    @NonNull
    @Override
    public VueTache onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vue = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tache, parent, false);
        return new VueTache(vue);
    }

    @Override
    public void onBindViewHolder(@NonNull VueTache holder, int position) {
        Tache tache = taches.get(position);

        holder.tvLibelle.setText(tache.getLibelle());
        holder.tvContenu.setText(tache.getContenu());
        holder.tvDate.setText(formatDate.format(new Date(tache.getDateCreation())));

        // Suppression individuelle par appui long
        holder.itemView.setOnLongClickListener(v -> {
            if (ecouteurSuppression != null) {
                ecouteurSuppression.onSupprimer(tache);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return taches.size();
    }

    public void mettreAJour(List<Tache> nouvelleListe) {
        DiffUtil.DiffResult resultat = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() { return taches.size(); }

            @Override
            public int getNewListSize() { return nouvelleListe.size(); }

            @Override
            public boolean areItemsTheSame(int ancienPos, int nouveauPos) {
                return taches.get(ancienPos).getIdentifiant()
                        == nouvelleListe.get(nouveauPos).getIdentifiant();
            }

            @Override
            public boolean areContentsTheSame(int ancienPos, int nouveauPos) {
                Tache a = taches.get(ancienPos);
                Tache b = nouvelleListe.get(nouveauPos);
                return Objects.equals(a.getLibelle(), b.getLibelle())
                        && Objects.equals(a.getContenu(), b.getContenu());
            }
        });
        taches = nouvelleListe;
        resultat.dispatchUpdatesTo(this);
    }

    static class VueTache extends RecyclerView.ViewHolder {
        final TextView tvLibelle;
        final TextView tvContenu;
        final TextView tvDate;

        VueTache(@NonNull View vue) {
            super(vue);
            tvLibelle = vue.findViewById(R.id.tvLibelle);
            tvContenu = vue.findViewById(R.id.tvContenu);
            tvDate = vue.findViewById(R.id.tvDate);
        }
    }
}
