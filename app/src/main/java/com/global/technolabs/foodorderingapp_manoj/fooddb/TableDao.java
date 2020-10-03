package com.global.technolabs.foodorderingapp_manoj.fooddb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TableDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_TABLE)
    List<Table> getTables();

    @Insert
    void insertAll(Table... tables);

    @Insert
    long insertTable(Table table);

    @Update
    void updateTable(Table table);

    @Delete
    void deleteTable(Table table);

//    void deleteTables(Table... tables);
}
