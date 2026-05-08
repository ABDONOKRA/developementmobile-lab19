# GestionTâches — Application Android de gestion de tâches

Application Android développée en Java selon l'architecture **MVVM** (Model-View-ViewModel), avec persistance locale via **Room Database**, réactivité via **LiveData** et affichage via **RecyclerView**.

---

## Description

**GestionTâches** est une application mobile permettant de gérer une liste de tâches personnelles. Chaque tâche possède un intitulé, une description et une date de création horodatée automatiquement.

L'utilisateur peut :
- Ajouter une tâche en remplissant deux champs validés
- Supprimer une tâche individuelle par **appui long**
- Supprimer toutes les tâches via un bouton avec **confirmation**
- Consulter les tâches triées du plus récent au plus ancien

Le projet illustre une architecture propre et maintenable, recommandée par Google pour les applications Android modernes.

---

## Architecture MVVM

```
AccueilActivite  (Vue)
       ↕  observe via LiveData
TacheViewModel  (ViewModel)
       ↕  délègue à
DepotTaches  (Repository)
       ↕  accède via DAO
TacheDao  →  BaseDonnees  (Room / SQLite)
```

### Structure des packages

```
com.ennoukra.gestionnairetaches/
├── modele/        → Entité Tache, interface TacheDao, singleton BaseDonnees
├── depot/         → DepotTaches (couche Repository)
├── presentation/  → TacheViewModel (logique métier, cycle de vie)
└── vue/           → AccueilActivite, AdaptateurTaches (RecyclerView)
```

---

## Fonctionnalités

| Fonctionnalité | Détail |
|---|---|
| Ajout de tâche | Intitulé (max 60 car.) + description (max 300 car.) |
| Horodatage | Date et heure de création affichées sur chaque carte |
| Suppression unitaire | Appui long sur une tâche |
| Suppression totale | Bouton avec boîte de dialogue de confirmation |
| Validation | Champs vides et dépassement de longueur détectés |
| Mise à jour efficace | DiffUtil évite les rechargements complets de la liste |
| Persistance | Room Database (SQLite), données conservées entre sessions |

---

## Technologies utilisées

| Bibliothèque | Version | Rôle |
|---|---|---|
| Room | 2.8.4 | Base de données SQLite locale |
| LiveData | 2.9.3 | Observation réactive des données |
| ViewModel | 2.9.3 | Gestion du cycle de vie Android |
| RecyclerView | 1.4.0 | Affichage performant de listes |
| DiffUtil | (inclus) | Comparaison intelligente des listes |
| CardView | 1.0.0 | Cartes visuelles pour chaque tâche |
| Material3 | 1.13.0 | Thème et composants graphiques |

---

## Prérequis

- Android Studio Giraffe (ou version ultérieure)
- SDK Android minimum : **API 24** (Android 7.0 Nougat)
- SDK Android cible : **API 36**
- Java 11

---

## Installation

1. Clonez ce dépôt :
   ```bash
   git clone hhttps://github.com/ABDONOKRA/developementmobile-lab19
   ```
2. Ouvrez le projet dans Android Studio.
3. Attendez la synchronisation Gradle.
4. Lancez l'application sur un émulateur ou un appareil physique (API >= 24).

---
<img width="490" height="930" alt="image" src="https://github.com/user-attachments/assets/af974831-7cd9-4231-bfae-16420dd73086" />

## Utilisation

1. Saisissez un intitulé dans le premier champ (60 caractères max).
2. Rédigez une description dans le second champ (300 caractères max).
3. Appuyez sur **Ajouter la tâche** pour enregistrer.
4. **Appui long** sur une carte pour supprimer cette tâche.
5. Appuyez sur **Tout supprimer** pour effacer toutes les tâches (une confirmation est demandée).

---

## Auteur

Développé par **Abdelghafour Ennoukra**
