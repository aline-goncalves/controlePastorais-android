package com.example.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.controlepastorais.Pastoral;
import java.util.List;

@Dao
public interface PastoralDao {

    @Insert
    long insert(Pastoral pastoral);

    @Delete
    void delete(Pastoral pastoral);

    @Update
    void update(Pastoral pastoral);

    @Query("SELECT * FROM pastorals WHERE id = :id")
    Pastoral queryForId(long id);

    @Query("SELECT * FROM pastorals ORDER BY name ASC")
    List<Pastoral> queryAll();
}