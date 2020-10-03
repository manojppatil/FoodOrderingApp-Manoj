package com.global.technolabs.foodorderingapp_manoj.fooddb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = Constants.TABLE_NAME_MENU)
public class Menu implements Serializable {
    //Columns
    @PrimaryKey(autoGenerate = true)
    private long menu_id;

    @ColumnInfo(name = "menu_name")
    private String menu_name;

    private String price;

    public Menu(String menu_name, String price) {
        this.menu_name = menu_name;
        this.price = price;
    }
@Ignore
    public Menu() {
    }

    public long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(long menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return menu_id == menu.menu_id &&
                Objects.equals(menu_name, menu.menu_name) &&
                Objects.equals(price, menu.price);
    }

    @Override
    public int hashCode() {
        int result = (int) menu_id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public static Menu[] populateMenu() {
        return new Menu[] {
                new Menu("Menu 1", "100"),
                new Menu("Menu 2", "120"),
                new Menu("Menu 3", "80"),
                new Menu("Menu 4", "100"),
                new Menu("Menu 5", "140"),
                new Menu("Menu 6", "150"),
                new Menu("Menu 7", "50"),
                new Menu("Menu 8", "100"),

        };
    }
}
