package com.example.mechsrit.misfit_shine.roomdatabase;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertData(RoomModel model);

    @Delete
    void deleteData(RoomModel model);

    @Query("SELECT * FROM product WHERE prid == :id")
    RoomModel model(int id);


    @Query("SELECT * FROM product")
    LiveData<List<RoomModel>> getliveData();


}
