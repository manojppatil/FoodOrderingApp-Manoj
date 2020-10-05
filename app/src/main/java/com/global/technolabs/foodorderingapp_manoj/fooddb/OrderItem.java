package com.global.technolabs.foodorderingapp_manoj.fooddb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME_ORDERITEM)
public class OrderItem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    long order_id;

    String table_id;
    String menu_name;
    String menu_id;
    String menu_quantity;
    String menu_total;

    public OrderItem(String table_id, String menu_name, String menu_id, String menu_quantity, String menu_total) {
        this.table_id = table_id;
        this.menu_name = menu_name;
        this.menu_id = menu_id;
        this.menu_quantity = menu_quantity;
        this.menu_total = menu_total;
    }

    public OrderItem() {
    }


    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_quantity() {
        return menu_quantity;
    }

    public void setMenu_quantity(String menu_quantity) {
        this.menu_quantity = menu_quantity;
    }

    public String getMenu_total() {
        return menu_total;
    }

    public void setMenu_total(String menu_total) {
        this.menu_total = menu_total;
    }
}
