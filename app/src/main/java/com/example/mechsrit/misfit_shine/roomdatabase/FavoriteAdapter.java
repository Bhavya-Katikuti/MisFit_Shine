package com.example.mechsrit.misfit_shine.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mechsrit.misfit_shine.DisplayFavorites;
import com.example.mechsrit.misfit_shine.ProductDetails;
import com.example.mechsrit.misfit_shine.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Context context;
    List<RoomModel> modelArrayList;

    public FavoriteAdapter(DisplayFavorites displayFavorites, List<RoomModel> listproducts) {
        this.context=displayFavorites;
        this.modelArrayList=listproducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.singleitem,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(modelArrayList.get(i).getPrimageLink()).placeholder(R.mipmap.ic_launcher).into(viewHolder.imageView);
        viewHolder.productprice.setText(modelArrayList.get(i).getPrprice());
        viewHolder.productName.setText(modelArrayList.get(i).getPrname());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productName,productprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            productName=itemView.findViewById(R.id.productName);
            productprice=itemView.findViewById(R.id.productPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, ProductDetails.class);
                        intent.putExtra("id",""+modelArrayList.get(pos).getPrid());
                        intent.putExtra("brand", modelArrayList.get(pos).getPrbrand());
                        intent.putExtra("name", modelArrayList.get(pos).getPrname());
                        intent.putExtra("price", modelArrayList.get(pos).getPrprice());
                        intent.putExtra("imglink", modelArrayList.get(pos).getPrimageLink());
                        intent.putExtra("productlink", modelArrayList.get(pos).getPproductLink());
                        intent.putExtra("weblink", modelArrayList.get(pos).getPrwebsiteLink());
                        intent.putExtra("desc", modelArrayList.get(pos).getPrdescription());
                        intent.putExtra("rating", modelArrayList.get(pos).getPrrating());
                        intent.putExtra("category", modelArrayList.get(pos).getPrcategory());
                        intent.putExtra("productType", modelArrayList.get(pos).getPproductType());
                        context.startActivity(intent);

                    }
                }
            });
        }
    }
}
