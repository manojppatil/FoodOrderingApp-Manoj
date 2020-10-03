package com.global.technolabs.foodorderingapp_manoj.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.global.technolabs.foodorderingapp_manoj.OrderSummary;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter {
    public OrderAdapter(List<Order> orders, OrderSummary orderSummary) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
