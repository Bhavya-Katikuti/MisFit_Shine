package com.example.mechsrit.misfit_shine.roomdatabase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RoomModel.class},version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    private static volatile ProductDatabase INSTANCE;

    static ProductDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (ProductDatabase.class){
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class,
                            "favPro.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
