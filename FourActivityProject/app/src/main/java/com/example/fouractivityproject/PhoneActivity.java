package com.example.fouractivityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneActivity extends AppCompatActivity {

    private EditText editPhone;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        editPhone  = (EditText) findViewById(R.id.editPhone);
        btnCall = (Button) findViewById(R.id.btnCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnCallListener(view);
            }
        });
    }

    private void handleBtnCallListener(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/"+editPhone.getText().toString()));
        startActivity(intent);
    }
}
