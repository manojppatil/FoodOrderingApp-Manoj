package com.global.technolabs.foodorderingapp_manoj.fooddb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {
    @Query("SELECT * FROM " + Constants.TABLE_NAME_ORDER)
    List<Order> getOrders();

    @Insert
    void insertAll(Order... orders);

    @Insert
    long insertOrder(Order order);

    @Update
    void updateOrder(Order order);

    @Delete
    void deleteOrder(Order order);
}
