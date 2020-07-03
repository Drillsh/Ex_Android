package com.example.fouractivityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnToAddress;
    private Button btnToPhoneCall;
    private Button btnToImage;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToAddress = (Button) findViewById(R.id.btnToAddress);
        btnToPhoneCall = (Button) findViewById(R.id.btnToPhoneCall);
        btnToImage = (Button) findViewById(R.id.btnToImage);
        btnExit = (Button) findViewById(R.id.btnExit);

        //주소연결 이벤트
        btnToAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnToAdrListener(view);
            }
        });

        //전화연결 이벤트
        btnToPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnToCallListener(view);
            }
        });

        //갤러리연결 이벤트
        btnToImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnToImageListener(view);
            }
        });

        //종료 이벤트
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Bye Bye~", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    //갤러리 연결 이벤트
    private void handleBtnToImageListener(View view) {
        Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
        startActivity(intent);
    }

    //전화연결 이벤트
    private void handleBtnToCallListener(View view) {
        Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);
        startActivity(intent);
    }

    //주소연결 이벤트
    private void handleBtnToAdrListener(View view) {

        Intent intent = new Intent(getApplicationContext(), AddressActivity.class);
        startActivity(intent);
    }
}
