package com.example.mechsrit.misfit_shine;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechsrit.misfit_shine.roomdatabase.ProductModel;
import com.example.mechsrit.misfit_shine.roomdatabase.RoomModel;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetails extends AppCompatActivity {



 @BindView(R.id.detailImg)
 ImageView imageView;
 @BindView(R.id.detail_pid)
    TextView tid;
 @BindView(R.id.detail_pname)
 TextView tname;
 @BindView(R.id.detail_pbrand)
 TextView tbrand;
 @BindView(R.id.detail_pdesc)
 TextView tdesc;
 @BindView(R.id.detail_pcategory)
 TextView tcate;
 @BindView(R.id.detail_weblink)
 TextView tweb;
 @BindView(R.id.detail_pprdlink)
 TextView tpr;
 @BindView(R.id.detail_prating)
 TextView trating;
 @BindView(R.id.detail_pprice)
 TextView tprice;
 @BindView(R.id.detail_pptype)
 TextView tpType;
 @BindView(R.id.likebtn)
 LikeButton likeButton;

  ProductModel roomModel;

    String imglink, pid, pname, pbrand, pdesc, pcate, pweb, ppr, prating, pprice, ptype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        roomModel=ViewModelProviders.of(this).get(ProductModel.class);
        imglink=getIntent().getStringExtra("imglink");
        pid=getIntent().getStringExtra("id");
        pname=getIntent().getStringExtra("name");
        pbrand=getIntent().getStringExtra("brand");
        pdesc=getIntent().getStringExtra("desc");
        pcate=getIntent().getStringExtra("category");
        pweb=getIntent().getStringExtra("weblink");
        ppr=getIntent().getStringExtra("productlink");
        prating=getIntent().getStringExtra("rating");
        pprice=getIntent().getStringExtra("price");
        ptype=getIntent().getStringExtra("productType");

        Picasso.with(this).load(imglink).placeholder(R.mipmap.ic_launcher).into(imageView);
        tid.setText(pid);
        tname.setText(pname);
        tbrand.setText(pbrand);
        tdesc.setText(pdesc);
        tcate.setText(pcate);
        tweb.setText(pweb);
        tpr.setText(ppr);
        trating.setText(prating);
        tprice.setText(pprice);
        tpType.setText(ptype);

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                savedata();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
             deleteData();
            }
        });
        
        checkProduct(pid);
        
        tweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(pweb));
                startActivity(intent);
            }
        });
        tpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(ppr));
                startActivity(intent);
            }
        });


    }



    public void savedata()
    {
        RoomModel favMovie=new RoomModel();
        favMovie.setPrid(Integer.parseInt(pid));
        favMovie.setPrbrand(pbrand);
        favMovie.setPrname(pname);
        favMovie.setPrprice(pprice);
        favMovie.setPrimageLink(imglink);
        favMovie.setPproductLink(ppr);
        favMovie.setPrwebsiteLink(pweb);
        favMovie.setPrrating(prating);
        favMovie.setPrdescription(pdesc);
        favMovie.setPproductType(ptype);
        favMovie.setPrcategory(pcate);
        roomModel.insertData(favMovie);
        Toast.makeText(this, getString(R.string.insert_msg_success), Toast.LENGTH_SHORT).show();
    }
    public void deleteData()
    {
        RoomModel favMovie=new RoomModel();
        favMovie.setPrid(Integer.parseInt(pid));
        favMovie.setPrbrand(pbrand);
        favMovie.setPrname(pname);
        favMovie.setPrprice(pprice);
        favMovie.setPrimageLink(imglink);
        favMovie.setPproductLink(ppr);
        favMovie.setPrwebsiteLink(pweb);
        favMovie.setPrrating(prating);
        favMovie.setPrdescription(pdesc);
        favMovie.setPproductType(ptype);
        favMovie.setPrcategory(pcate);
        roomModel.deleteData(favMovie);
        Toast.makeText(this, getString(R.string.del_data_msg), Toast.LENGTH_SHORT).show();
    }


    private void checkProduct(String id) {
        RoomModel model=roomModel.checkFav(Integer.parseInt(id));
        if (model!=null)
        {
            likeButton.setLiked(true);
        }
        else {
            likeButton.setLiked(false);
        }

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
