package com.ennoukra.gestionnairetaches.modele;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Singleton thread-safe de la base de données Room
@Database(entities = {Tache.class}, version = 1, exportSchema = false)
public abstract class BaseDonnees extends RoomDatabase {

    public abstract TacheDao tacheDao();

    private static volatile BaseDonnees INSTANCE;

    public static BaseDonnees getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BaseDonnees.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    BaseDonnees.class,
                                    "bd_taches"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
