package com.global.technolabs.foodorderingapp_manoj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.global.technolabs.foodorderingapp_manoj.adapter.MenuAdapter;
import com.global.technolabs.foodorderingapp_manoj.adapter.TableAdapter;
import com.global.technolabs.foodorderingapp_manoj.fooddb.FoodDatabase;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Menu;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Order;
import com.global.technolabs.foodorderingapp_manoj.fooddb.OrderDao;
import com.global.technolabs.foodorderingapp_manoj.fooddb.OrderItem;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Table;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MenulistActivity extends AppCompatActivity implements MenuAdapter.OnMenuItemClick {

    private RecyclerView menu_recycler;
    private FoodDatabase foodDatabase;
    private List<Menu> menus;
    private MenuAdapter menuAdapter;
    private int pos;
    FloatingActionButton fab_submit;
    private Table table;
    String table_id;
    List<Order> orderList;
    List<OrderItem> orderItemList;
    Order order;
    OrderDao orderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulist);
        initializeViews();
        displayList();

        if ((table = (Table) getIntent().getSerializableExtra("table")) != null) {
            table_id = String.valueOf(table.getTable_id());
        }
    }

    private void initializeViews() {
        menu_recycler = findViewById(R.id.menu_recycler);
        menu_recycler.setLayoutManager(new LinearLayoutManager(MenulistActivity.this));
        menus = new ArrayList<>();
        orderList = new ArrayList<>();
        orderItemList = new ArrayList<>();
        menuAdapter = new MenuAdapter(menus, MenulistActivity.this);
        menu_recycler.setAdapter(menuAdapter);

        fab_submit = findViewById(R.id.submit_order);
        fab_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit_order();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_addtable) {
            addmenu();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addmenu() {
        startActivityForResult(new Intent(MenulistActivity.this, AddMenuActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && requestCode > 0) {
            if (resultCode == 1) {
                menus.add((Menu) data.getSerializableExtra("menu"));
            } else if (resultCode == 2) {
                menus.set(pos, (Menu) data.getSerializableExtra("menu"));
            }
        }
    }

    private void submit_order() {
//        order = new Order(orderItemList);
//        new InsertTask(MenulistActivity.this, (Order) orderList).execute();

        Intent intent = new Intent(MenulistActivity.this, OrderSummary.class);
        Bundle args = new Bundle();
        args.putSerializable("ORDERLIST",(Serializable)orderItemList);
        intent.putExtra("BUNDLE",args);
        intent.putExtra("table_id",table_id);
        startActivity(intent);

    }

    private void displayList() {
        foodDatabase = FoodDatabase.getInstance(MenulistActivity.this);
        new RetreiveTask(MenulistActivity.this).execute();
    }

    @Override
    public void onMenuClick(final int pos) {

    }

    @Override
    public void push(List<OrderItem> orderIteList) {
        this.orderItemList = orderIteList;
//        Toast.makeText(MenulistActivity.this, "pushon" + orderItemList, Toast.LENGTH_SHORT).show();
        //    private List<OrderItem> orderItemList = new ArrayList<>();
    }

    private static class RetreiveTask extends AsyncTask<Void, Void, List<Menu>> {

        private WeakReference<MenulistActivity> activityReference;

        RetreiveTask(MenulistActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Menu> doInBackground(Void... voids) {
            if (activityReference != null) {
                return activityReference.get().foodDatabase.getMenuDao().getMenus();
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Menu> menus) {
            if (menus != null && menus.size() > 0) {
                activityReference.get().menus.clear();
                activityReference.get().menus.addAll(menus);
                activityReference.get().menuAdapter.notifyDataSetChanged();
            }
        }
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<MenulistActivity> activityWeakReference;
        private Order order;

        InsertTask(MenulistActivity context, Order order) {
            activityWeakReference = new WeakReference<>(context);
            this.order = order;
            Toast.makeText(context, "Order Added", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            long j = activityWeakReference.get().foodDatabase.getOrderDao().insertOrder(order);
            order.setOrder_id(j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                activityWeakReference.get().setResult(order, 1);
                activityWeakReference.get().finish();
            }
        }

    }

    private void setResult(Order order, int flag) {
        setResult(flag, new Intent().putExtra("order", String.valueOf(order)));
        finish();
    }

    @Override
    protected void onDestroy() {
        foodDatabase.cleanup();
        super.onDestroy();
    }
}