package com.example.mechsrit.misfit_shine.adapterclass;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mechsrit.misfit_shine.MakeUpWidget;
import com.example.mechsrit.misfit_shine.ProductDetails;
import com.example.mechsrit.misfit_shine.ProductView;
import com.example.mechsrit.misfit_shine.R;
import com.example.mechsrit.misfit_shine.modelclass.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholders> {
    Context context;
    ArrayList<Model> modelArrayList;
    public static final String  SPH_KEY="Bunni";
    public static final String PREF_KEY="KEY";
    public static final String DESCRIPTION="description";
    SharedPreferences sharedPreferences;

    public ProductAdapter(ProductView productView, ArrayList<Model> modelArrayList) {
        this.context=productView;
        this.modelArrayList=modelArrayList;
    }

    @NonNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.singleitem,viewGroup,false);
        return new Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholders viewHolder, int i) {
        Picasso.with(context).load(modelArrayList.get(i).getImageLink())
                .placeholder(R.mipmap.ic_launcher).into(viewHolder.imageView);
        viewHolder.productprice.setText(modelArrayList.get(i).getPrice());
        viewHolder.productName.setText(modelArrayList.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productName,productprice;
        public Viewholders(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            productName=itemView.findViewById(R.id.productName);
            productprice=itemView.findViewById(R.id.productPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if (pos!=RecyclerView.NO_POSITION)
                    {
                        Intent intent=new Intent(context,ProductDetails.class);
                        intent.putExtra("id",modelArrayList.get(pos).getId());
                        intent.putExtra("brand",modelArrayList.get(pos).getBrand());
                        intent.putExtra("name",modelArrayList.get(pos).getName());
                        intent.putExtra("price",modelArrayList.get(pos).getPrice());
                        intent.putExtra("imglink",modelArrayList.get(pos).getImageLink());
                        intent.putExtra("productlink",modelArrayList.get(pos).getProductLink());
                        intent.putExtra("weblink",modelArrayList.get(pos).getWebsiteLink());
                        intent.putExtra("desc",modelArrayList.get(pos).getDescription());
                        intent.putExtra("rating",modelArrayList.get(pos).getRating());
                        intent.putExtra("category",modelArrayList.get(pos).getCategory());
                        intent.putExtra("productType",modelArrayList.get(pos).getProductType());

                       sharedPreferences=context.getSharedPreferences(SPH_KEY,MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString(PREF_KEY,modelArrayList.get(pos).getName());
                        editor.putString(DESCRIPTION,modelArrayList.get(pos).getDescription());

                        editor.apply();
                        Intent inten=new Intent(context,MakeUpWidget.class);
                        inten.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                        int[] ints=AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context,MakeUpWidget.class));
                        inten.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ints);
                        context.sendBroadcast(inten);

                        context.startActivity(intent);

                    }

                }
            });

        }
    }
}
