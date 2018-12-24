package com.example.mechsrit.misfit_shine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class BeautyTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_tips);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void facetips(View view) {
        String facelink=getString(R.string.faceYouLink);
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(facelink));
        startActivity(intent);
    }

    public void lipstips(View view) {
        String lipslink=getString(R.string.lipsYouLink);
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(lipslink));
        startActivity(intent);
    }

    public void necktips(View view) {
        String necklink=getString(R.string.neckYouLink);
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(necklink));
        startActivity(intent);
    }

    public void hairtips(View view) {
        String hairlink=getString(R.string.hairYouLink);
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(hairlink));
        startActivity(intent);
    }

    public void handstips(View view) {
        String handlink=getString(R.string.handYouLink);
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(handlink));
        startActivity(intent);
    }

    public void feettips(View view) {
        String feetlink=getString(R.string.feetYoutubeLink);
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(feetlink));
        startActivity(intent);
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
