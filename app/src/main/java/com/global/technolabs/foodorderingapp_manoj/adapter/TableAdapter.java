package com.global.technolabs.foodorderingapp_manoj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.global.technolabs.foodorderingapp_manoj.MenulistActivity;
import com.global.technolabs.foodorderingapp_manoj.R;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Table;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<Table> tableList;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnTableItemClick onTableItemClick;

    public TableAdapter(List<Table> tableList, Context context) {
        layoutInflater = layoutInflater.from(context);
        this.tableList = tableList;
        this.context = context;
        this.onTableItemClick = (OnTableItemClick) context;

    }

    public interface OnTableItemClick {
        void onTableClick(int pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.table_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.title.setText(tableList.get(position).getContent());
        String status = tableList.get(position).getStatus();
        if (status.equals("1")) {
            holder.table_status.setImageResource(R.drawable.ic_baseline_bookmark_24);
        } else {
            holder.table_status.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
        }

        holder.take_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenulistActivity.class);
                intent.putExtra("table", tableList.get(position));
                context.startActivity(intent);
            }
        });

        holder.cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Order Cleared Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        holder.table_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.table_status.setImageResource(R.drawable.ic_baseline_bookmark_24);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView take_order;
        private ImageView cancel_order;
        private ImageView table_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            take_order = itemView.findViewById(R.id.iv_takeorder);
            cancel_order = itemView.findViewById(R.id.iv_cancelorder);
            table_status = itemView.findViewById(R.id.iv_status);
        }

        @Override
        public void onClick(View view) {
            onTableItemClick.onTableClick(getAdapterPosition());
        }
    }
}
