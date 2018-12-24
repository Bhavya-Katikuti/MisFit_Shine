package com.example.mechsrit.misfit_shine;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mechsrit.misfit_shine.adapterclass.ProductAdapter;
import com.example.mechsrit.misfit_shine.modelclass.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductView extends AppCompatActivity {
    @BindView(R.id.recycler) RecyclerView recyclerView;
    String data;
    ArrayList<Model> modelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        ButterKnife.bind(this);
        data=getIntent().getStringExtra("data");
        parseJsonData(data);

    }

    private void parseJsonData(String data) {
        String id,brand,name,price,imagelink,productlink,websitelink,description,rating,category,producttype;
        modelArrayList=new ArrayList<>();
        try {
            JSONArray root=new JSONArray(data);
            for (int i=0;i<root.length();i++)
            {
                JSONObject jsonObject=root.getJSONObject(i);
                id=jsonObject.optString("id");
                brand=jsonObject.getString("brand");
                name=jsonObject.optString("name");
                price=jsonObject.optString("price");
                imagelink=jsonObject.optString("image_link");
                productlink=jsonObject.optString("product_link");
                websitelink=jsonObject.optString("website_link");
                description=jsonObject.optString("description");
                rating=jsonObject.optString("rating");
                category=jsonObject.optString("category");
                producttype=jsonObject.optString("product_type");

                Model model=new Model(id,brand,name,price,imagelink,productlink,websitelink,description,rating,category,producttype);
                modelArrayList.add(model);
            }
            if(modelArrayList.isEmpty()) {

                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.colorlens);
                builder.setTitle(R.string.prod_not_avail);
                builder.setMessage(R.string.click_ok);
                builder.setPositiveButton(R.string.pos_but, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(ProductView.this,ProductSearch.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }else {
                ProductAdapter productAdapter = new ProductAdapter(this, modelArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data",data);
    }
}
