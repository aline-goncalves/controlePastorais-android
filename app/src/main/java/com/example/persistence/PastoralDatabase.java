package com.example.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.controlepastorais.Pastoral;

@Database(entities = {Pastoral.class}, version = 1, exportSchema = false)
public abstract class PastoralDatabase extends RoomDatabase {

    public abstract PastoralDao pastoralDao();

    private static PastoralDatabase instance;

    public static PastoralDatabase getDatabase(final Context context) {
        if (instance == null) {

            synchronized (PastoralDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                                                    PastoralDatabase.class,
                                                    "pastorals.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
