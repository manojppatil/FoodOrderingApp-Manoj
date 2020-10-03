package com.global.technolabs.foodorderingapp_manoj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.global.technolabs.foodorderingapp_manoj.adapter.OrderAdapter;
import com.global.technolabs.foodorderingapp_manoj.adapter.TableAdapter;
import com.global.technolabs.foodorderingapp_manoj.fooddb.FoodDatabase;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Order;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        recyclerView = findViewById(R.id.order_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(OrderSummary.this, 2));

        orders = new ArrayList<>();
        orderAdapter = new OrderAdapter(orders, OrderSummary.this);
        recyclerView.setAdapter(orderAdapter);
        displayTables();
    }

    private void displayTables() {
        foodDatabase = FoodDatabase.getInstance(OrderSummary.this);
        new RetreiveTask(OrderSummary.this).execute();
    }

    private static class RetreiveTask extends AsyncTask<Void, Void, List<Order>> {

        private WeakReference<OrderSummary> activityReference;

        RetreiveTask(OrderSummary context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Order> doInBackground(Void... voids) {
            if (activityReference != null) {
                return activityReference.get().foodDatabase.getOrderDao().getOrders();
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Order> orders) {
            if (orders != null && orders.size() > 0) {
                activityReference.get().orders.clear();
                activityReference.get().orders.addAll(orders);
                activityReference.get().orderAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        foodDatabase.cleanup();
        super.onDestroy();
    }
}