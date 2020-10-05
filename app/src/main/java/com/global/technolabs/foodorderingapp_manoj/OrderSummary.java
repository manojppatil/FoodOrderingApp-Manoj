package com.global.technolabs.foodorderingapp_manoj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.global.technolabs.foodorderingapp_manoj.adapter.OrderAdapter;
import com.global.technolabs.foodorderingapp_manoj.adapter.TableAdapter;
import com.global.technolabs.foodorderingapp_manoj.fooddb.FoodDatabase;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Order;
import com.global.technolabs.foodorderingapp_manoj.fooddb.OrderItem;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Table;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class OrderSummary extends AppCompatActivity {

    RecyclerView recyclerView;
    private FoodDatabase foodDatabase;
    private List<Order> orders;
    private OrderAdapter orderAdapter;
    private int pos;
    List <OrderItem> orderItemList;
    public String table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        recyclerView = findViewById(R.id.order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderSummary.this));

        orderItemList = new ArrayList<>();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        orderItemList = (ArrayList<OrderItem>) args.getSerializable("ORDERLIST");
        table_id = intent.getStringExtra("table_id");


        orderAdapter = new OrderAdapter(orderItemList, OrderSummary.this, table_id);
        recyclerView.setAdapter(orderAdapter);
    }


}