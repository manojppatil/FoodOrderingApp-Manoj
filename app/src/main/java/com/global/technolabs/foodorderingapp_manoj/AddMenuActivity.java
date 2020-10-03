package com.global.technolabs.foodorderingapp_manoj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.global.technolabs.foodorderingapp_manoj.fooddb.FoodDatabase;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Menu;
import com.global.technolabs.foodorderingapp_manoj.fooddb.Table;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.ref.WeakReference;

public class AddMenuActivity extends AppCompatActivity {

    TextInputEditText menuname;
    TextInputEditText menuprice;
    Button addmenu;
    Menu menu;
    FoodDatabase foodDatabase;
    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        menuname = findViewById(R.id.ed_menuname);
        menuprice = findViewById(R.id.ed_menuprice);
        addmenu = findViewById(R.id.add_menu);
        setTitle("Add Menu");

        foodDatabase = FoodDatabase.getInstance(AddMenuActivity.this);

        if ((menu = (Menu) getIntent().getSerializableExtra("menu")) != null) {
            getSupportActionBar().setTitle("Update Menu");
            update = true;
            addmenu.setText("Update Menu");
            menuname.setText(menu.getMenu_name());
            menuprice.setText(menu.getPrice());
        }

        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update) {
                    menu.setMenu_name(menuname.getText().toString());
                    menu.setPrice(menuprice.getText().toString());
                    foodDatabase.getMenuDao().updateMenu(menu);
                    setResult(menu, 2);
                } else {
                    menu = new Menu(menuname.getText().toString(), menuprice.getText().toString());
                    new InsertTask(AddMenuActivity.this, menu).execute();
                }
            }
        });
    }

    private void setResult(Menu menu, int flag) {
        setResult(flag, new Intent().putExtra("menu", menu));
        finish();
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<AddMenuActivity> activityWeakReference;
        private Menu menu;

        InsertTask(AddMenuActivity context, Menu menu){
            activityWeakReference  = new WeakReference<>(context);
            this.menu = menu;
            Toast.makeText(context, "Menu Added", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            long j = activityWeakReference.get().foodDatabase.getMenuDao().insertMenu(menu);
            menu.setMenu_id(j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean){
                activityWeakReference.get().setResult(menu,1);
                activityWeakReference.get().finish();
            }
        }
    }
}