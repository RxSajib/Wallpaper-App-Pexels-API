package com.example.walpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class FullScreenActivity extends AppCompatActivity {

    private PhotoView photoView;
    private String URi;
    private MaterialButton set_walpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        photoView = findViewById(R.id.PhotoView);
        Intent intent = getIntent();
        URi = intent.getStringExtra(DataManager.ClickImageUri);
        getSupportActionBar().hide();

        Picasso.with(getApplicationContext()).load(URi).into(photoView);

        set_walpaper = findViewById(R.id.SetupWalperButon);
        set_walpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                Bitmap bitmap = ((BitmapDrawable) photoView.getDrawable()).getBitmap();

                try {
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "wallpaper is setup success", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}