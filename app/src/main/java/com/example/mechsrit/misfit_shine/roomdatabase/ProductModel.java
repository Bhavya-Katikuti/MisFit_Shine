package com.example.mechsrit.misfit_shine.roomdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ProductModel extends AndroidViewModel {
    ProductRepository mrepository;
    LiveData<List<RoomModel>> getliveData;

    public ProductModel(@NonNull Application application) {
        super(application);
        mrepository=new ProductRepository(application);
        getliveData=mrepository.getAllData();

    }
    public LiveData<List<RoomModel>> getGetliveData()
    {
        return getliveData;
    }
    public RoomModel checkFav(int id)
    {
        RoomModel roomModel=mrepository.checkFav(id);
        return roomModel;
    }
    public void insertData(RoomModel roomModel)
    {
        mrepository.insertData(roomModel);
    }
    public void deleteData(RoomModel roomModel)
    {
        mrepository.deleteData(roomModel);
    }
}
