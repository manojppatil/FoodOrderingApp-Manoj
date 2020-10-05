package com.global.technolabs.foodorderingapp_manoj.fooddb;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

@Entity(tableName = Constants.TABLE_NAME_ORDER)
public class Order {
    @PrimaryKey(autoGenerate = true)
    private long order_id;
    private String status;
//    List<OrderItem> orderitems;


//    public Order(List<OrderItem> orderItemList) {
//    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return order_id == order.order_id &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        int result = (int) order_id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", status='" + status + '\'' +
                '}';
    }
}
