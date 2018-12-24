package com.example.mechsrit.misfit_shine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchProduct extends AsyncTask<String,Void,String> {
    ProgressDialog dialog;
    Context context;
    String product,brand;

    public SearchProduct(ProductSearch productSearch) {
        this.context=productSearch;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.load_plz_wait));
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        product=strings[0];
        brand=strings[1];
        String link="http://makeup-api.herokuapp.com/api/v1/products.json?brand="+brand+"&product_type="+product;
        try {
            URL url=new URL(link);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=connection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder=new StringBuilder();
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        if (s.isEmpty())
        {
            Toast.makeText(context, context.getString(R.string.not_avail_prod),
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent=new Intent(context,ProductView.class);
            intent.putExtra("data",s);
            context.startActivity(intent);
        }
    }
}
