package com.example.mechsrit.misfit_shine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSearch extends AppCompatActivity {
     @BindView(R.id.nice_spinner_product)
    Spinner nicespinnerproduct;
     @BindView(R.id.nice_spinner_brand)
     Spinner nicespinnerbrand;
     String selectBrand,selectProduct;
     SearchProduct searchProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void performSearch(View view) {
        selectBrand=nicespinnerbrand.getSelectedItem().toString();
        selectProduct=nicespinnerproduct.getSelectedItem().toString();
        searchProduct=new SearchProduct(this);
        searchProduct.execute(selectProduct,selectBrand);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
