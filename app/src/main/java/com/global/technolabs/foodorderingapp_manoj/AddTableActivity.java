package com.global.technolabs.foodorderingapp_manoj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.global.technolabs.foodorderingapp_manoj.fooddb.FoodDatabase;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Table;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.ref.WeakReference;

public class AddTableActivity extends AppCompatActivity {
    TextInputEditText tablename;
    Table table;
    FoodDatabase foodDatabase;
    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);
        tablename = findViewById(R.id.ed_tablename);
        setTitle("Add Table");

        foodDatabase = FoodDatabase.getInstance(AddTableActivity.this);

    }

    public void add_table(View view) {
        table = new Table();
        table.setContent(tablename.getText().toString());
        new InsertTask(AddTableActivity.this, table).execute();
    }

    private void setResult(Table table, int flag) {
        setResult(flag, new Intent().putExtra("table", table));
        finish();
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean>{
        private WeakReference<AddTableActivity> activityWeakReference;
        private Table table;

        InsertTask(AddTableActivity context, Table table){
            activityWeakReference  = new WeakReference<>(context);
            this.table = table;
            Toast.makeText(context, "Table Added", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            long j = activityWeakReference.get().foodDatabase.getTableDao().insertTable(table);
            table.setTable_id(j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean){
                activityWeakReference.get().setResult(table,1);
                activityWeakReference.get().finish();
            }
        }
    }
}