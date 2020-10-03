package com.global.technolabs.foodorderingapp_manoj.fooddb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Table.class, Menu.class, Order.class, OrderItem.class}, version = 1, exportSchema = false)
@TypeConverters({DataRoomConverter.class})
public abstract class FoodDatabase extends RoomDatabase {
    public abstract TableDao getTableDao();

    public abstract MenuDao getMenuDao();

    public abstract OrderDao getOrderDao();

    private static FoodDatabase foodDatabase;

    public static /*synchronized*/ FoodDatabase getInstance(Context context) {
        if (null == foodDatabase) {
            foodDatabase = buildDatabaseInstance(context);
        }
        return foodDatabase;
    }

    private static FoodDatabase buildDatabaseInstance(final Context context) {
        return Room.databaseBuilder(context,
                FoodDatabase.class,
                Constants.DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).getTableDao().insertAll(Table.populateData());

                            }
                        });
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).getMenuDao().insertAll(Menu.populateMenu());

                            }
                        });
                    }
                }).build();
    }

    public void cleanup() {
        foodDatabase = null;
    }

}
