package com.example.a200703_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class completeActivity extends AppCompatActivity {

    private ImageView ivPhoto;
    private TextView tvName;
    private TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String phone = intent.getStringExtra("PhoneNo");
        String photo = intent.getStringExtra("Photo");

        boolean onOff = intent.getBooleanExtra("HideNum",true);


        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);

        if(!name.equals("") && !phone.equals("")){
            tvName.setText(name);
            tvPhone.setText(phone);
        }

        if(!photo.equals("")){
            setImage(photo);
        }

        if (onOff) {
            tvPhone.setVisibility(View.INVISIBLE);
        }else{
            tvPhone.setVisibility(View.VISIBLE);
        }


    }

    private void setImage(String photo) {
        switch (photo){
            case "LION":
                ivPhoto.setImageResource(R.drawable.lion);
                break;
            case "MUJI":
                ivPhoto.setImageResource(R.drawable.muji);
                break;
            case "NEO":
                ivPhoto.setImageResource(R.drawable.neo);
                break;
            case "PRODO":
                ivPhoto.setImageResource(R.drawable.prodo);
                break;
        }
    }
}
