package com.global.technolabs.foodorderingapp_manoj.fooddb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MenuDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_MENU)
    List<Menu> getMenus();

    @Insert
    void insertAll(Menu... menus);

    @Insert
    long insertMenu(Menu menu);

    @Update
    void updateMenu(Menu menu);

    @Delete
    void deleteMenu(Menu menu);
}
