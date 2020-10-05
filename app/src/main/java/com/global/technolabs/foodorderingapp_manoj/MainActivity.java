package com.global.technolabs.foodorderingapp_manoj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.global.technolabs.foodorderingapp_manoj.adapter.TableAdapter;
import com.global.technolabs.foodorderingapp_manoj.fooddb.FoodDatabase;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Order;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Table;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TableAdapter.OnTableItemClick {

    private RecyclerView table_recycler;
    private FoodDatabase foodDatabase;
    private List<Table> tables;
    private TableAdapter tableAdapter;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        displayTables();
    }

    private void initializeViews() {
        table_recycler = findViewById(R.id.table_recycler);
        table_recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        tables = new ArrayList<>();
        tableAdapter = new TableAdapter(tables, MainActivity.this);
        table_recycler.setAdapter(tableAdapter);
    }

    private void displayTables() {
        foodDatabase = FoodDatabase.getInstance(MainActivity.this);
        new RetreiveTask(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_addtable) {
            addtable();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addtable() {
        startActivityForResult(new Intent(MainActivity.this, AddTableActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == 0) {
            if (resultCode == 1) {
                tables.add((Table) data.getSerializableExtra("table"));
            } else if (resultCode == 2) {
                tables.set(pos, (Table) data.getSerializableExtra("table"));
            }
            listvisibility();
        }
    }

    private void listvisibility() {
        if (tables.size() == 0) {
            //No Items displays

        }
    }


    @Override
    public void onTableClick(final int pos) {

        new AlertDialog.Builder(MainActivity.this).setItems(new String[]{"Take Order", "Cancel Order"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case 0:
                        MainActivity.this.pos = pos;
                        startActivityForResult(new Intent(MainActivity.this, AddMenuActivity.class)
                                        .putExtra("table", tables.get(pos))
                                , 100);
                        break;
                    case 1:
                        foodDatabase.getTableDao().deleteTable(tables.get(pos));
                        tables.remove(pos);
                        break;
                }
            }
        }).show();
    }

//    public void goto_ordersummary(View view) {
//        Intent intent = new Intent(MainActivity.this, OrderSummary.class);
//        startActivity(intent);
//        finish();
//    }


    private static class RetreiveTask extends AsyncTask<Void, Void, List<Table>> {

        private WeakReference<MainActivity> activityReference;

        RetreiveTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Table> doInBackground(Void... voids) {
            if (activityReference != null) {
                return activityReference.get().foodDatabase.getTableDao().getTables();
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Table> tables) {
            if (tables != null && tables.size() > 0) {
                activityReference.get().tables.clear();
                activityReference.get().tables.addAll(tables);
                activityReference.get().tableAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        foodDatabase.cleanup();
        super.onDestroy();
    }
}