package com.global.technolabs.foodorderingapp_manoj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.global.technolabs.foodorderingapp_manoj.OrderSummary;
import com.global.technolabs.foodorderingapp_manoj.R;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Order;
import com.global.technolabs.foodorderingapp_manoj.fooddb.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<OrderItem> orderItemList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    public String table_id;


    public OrderAdapter(List<OrderItem> orderItemList, Context context, String table_id) {
        this.context = context;
        this.orderItemList = orderItemList;
        layoutInflater = layoutInflater.from(context);
        this.table_id = table_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.order_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tableno.setText("Table no: " + table_id);
        holder.orderno.setText("Order no.: " + orderItemList.get(position).getMenu_id());
        holder.ordertotal.setText("Order Total: " + orderItemList.get(position).getMenu_total());

    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderno, tableno, ordertotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderno = itemView.findViewById(R.id.orderno);
            ordertotal = itemView.findViewById(R.id.ordertotal);
            tableno = itemView.findViewById(R.id.tableno);
        }
    }
}
