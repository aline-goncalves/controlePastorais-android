package com.example.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
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