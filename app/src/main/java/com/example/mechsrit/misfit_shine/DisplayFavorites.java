package com.example.mechsrit.misfit_shine;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mechsrit.misfit_shine.roomdatabase.FavoriteAdapter;
import com.example.mechsrit.misfit_shine.roomdatabase.ProductModel;
import com.example.mechsrit.misfit_shine.roomdatabase.RoomModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayFavorites extends AppCompatActivity {
    @BindView(R.id.recycler) RecyclerView favrecycler;
    ProductModel productModel;
    List<RoomModel> listproducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favorites);
        ButterKnife.bind(this);

        productModel=ViewModelProviders.of(this).get(ProductModel.class);
        productModel.getGetliveData().observe(this, new Observer<List<RoomModel>>() {
            @Override
            public void onChanged(@Nullable List<RoomModel> roomModels) {
                listproducts = roomModels;
                if (listproducts.isEmpty()) {
                    Toast.makeText(DisplayFavorites.this, getString(R.string.no_fav_avail), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    FavoriteAdapter favoriteAdapter = new FavoriteAdapter(DisplayFavorites.this, listproducts);
                    favrecycler.setLayoutManager(new LinearLayoutManager(DisplayFavorites.this));
                    favrecycler.setAdapter(favoriteAdapter);
                }
            }
        });

    }
}
