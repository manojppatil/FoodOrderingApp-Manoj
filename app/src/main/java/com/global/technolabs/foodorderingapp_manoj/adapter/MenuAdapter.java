package com.global.technolabs.foodorderingapp_manoj.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.global.technolabs.foodorderingapp_manoj.MenulistActivity;
import com.global.technolabs.foodorderingapp_manoj.R;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Menu;
import com.global.technolabs.foodorderingapp_manoj.fooddb.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<Menu> menuList;
    private Context context;
    private LayoutInflater layoutInflater;
    private MenuAdapter.OnMenuItemClick onMenuItemClick;
    int minteger = 0;
    private List<OrderItem> orderItemList = new ArrayList<>();

    public MenuAdapter(List<Menu> menuList, Context context) {
        layoutInflater = layoutInflater.from(context);
        this.menuList = menuList;
        this.context = context;
        this.onMenuItemClick = (MenuAdapter.OnMenuItemClick) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.menu_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Menu menu = menuList.get(position);
        holder.menuname.setText(menu.getMenu_name());
        holder.menuprice.setText("Rs. " + menu.getPrice());
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minteger = Integer.parseInt(holder.quantitycount.getText().toString());
                if (minteger != 0) {
                    int total_item = Integer.parseInt(holder.quantitycount.getText().toString());
                    total_item--;
                    holder.quantitycount.setText(total_item + "");
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minteger = Integer.parseInt(holder.quantitycount.getText().toString());
                if (minteger >= 0) {
                    int total_item = Integer.parseInt(holder.quantitycount.getText().toString());
                    total_item++;
                    holder.quantitycount.setText(total_item + "");
                }
            }
        });

        holder.checkBox.setText("" + position);
        holder.checkBox.setChecked(Boolean.parseBoolean(menuList.get(position).getMenu_name()));
//        holder.tvAnimal.setText(imageModelArrayList.get(position).getAnimal());

        // holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkBox.getTag();
                Toast.makeText(context, menuList.get(pos).getMenu_id() + " clicked!", Toast.LENGTH_SHORT).show();
                OrderItem orderItem = new OrderItem();
                orderItem.setMenu_id(String.valueOf(menu.getMenu_id()));
                orderItem.setMenu_name(menu.getMenu_name());
                orderItem.setMenu_quantity(holder.quantitycount.getText().toString());

                int menuprice = Integer.parseInt(menu.getPrice());
                int menuquantity = Integer.parseInt(holder.quantitycount.getText().toString());

                int menutotal = menuprice * menuquantity;

                orderItem.setMenu_total(String.valueOf(menutotal));

                orderItemList.add(orderItem);

                ((MenulistActivity) context).onMenuClick(position);
                ((MenulistActivity) context).push(orderItemList);

            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface OnMenuItemClick {
        void onMenuClick(int pos);

        void push(List<OrderItem> orderItemList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView menuname;
        TextView menuprice;
        ImageView minus, plus;
        TextView quantitycount;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menuname = itemView.findViewById(R.id.tv_menuname);
            menuprice = itemView.findViewById(R.id.tv_menuprice);
            minus = itemView.findViewById(R.id.minus_menu);
            plus = itemView.findViewById(R.id.plus_menu);
            quantitycount = itemView.findViewById(R.id.quantitycount);
            checkBox = itemView.findViewById(R.id.cb_menu);

        }
    }
}