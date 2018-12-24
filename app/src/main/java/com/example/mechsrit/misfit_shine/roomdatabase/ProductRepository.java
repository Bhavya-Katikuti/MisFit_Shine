package com.example.mechsrit.misfit_shine.roomdatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;
    LiveData<List<RoomModel>> getData;

    ProductRepository(Application application)
    {
        ProductDatabase database=ProductDatabase.getDatabase(application);
        productDao=database.productDao();
        getData=productDao.getliveData();
    }
    LiveData<List<RoomModel>> getAllData()
    {
        return getData;
    }
    public RoomModel checkFav(int id)
    {
        RoomModel roomModel=productDao.model(id);
        return roomModel;
    }
    public void insertData(RoomModel model)
    {
        new insertAsync(productDao).execute(model);
    }

    public void deleteData(RoomModel roomModel)
    {
        new deleteAsync(productDao).execute(roomModel);
    }

    private class insertAsync extends AsyncTask<RoomModel,Void,Void> {
        ProductDao productDao;
        public insertAsync(ProductDao productDao)
        {
            this.productDao=productDao;
        }
        @Override
        protected Void doInBackground(RoomModel... roomModels) {
            productDao.insertData(roomModels[0]);
            return null;
        }
    }

    private class deleteAsync extends AsyncTask<RoomModel,Void,Void>{
        ProductDao deleteDao;
        public deleteAsync(ProductDao productDao)
        {
            this.deleteDao=productDao;
        }
        @Override
        protected Void doInBackground(RoomModel... roomModels) {
            deleteDao.deleteData(roomModels[0]);
            return null;
        }
    }
}
