package com.example.fouractivityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ImageActivity extends AppCompatActivity {

    private Button btnToGallary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        btnToGallary = (Button) findViewById(R.id.btnToGallary);

        btnToGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnToGallListener(view);
            }
        });
    }

    private void handleBtnToGallListener(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
        startActivity(intent);
    }
}
