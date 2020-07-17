package com.mohammad_fathi.store.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mohammad_fathi.store.model.room.dao.Database_DAO;
import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.room.entity.Products;

@Database(entities = {Cart_item_entity.class , Discount_Items_entity.class, Products.class},version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //---- Arbitrary database name --(for further use)------------
    private static final String DATABASE_NAME = "AppDB";

    //---- All DAO Class--(Create an instance)--------------------
    public abstract Database_DAO getDatabaseDAO();

    //---- Singlton method----------------------
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
