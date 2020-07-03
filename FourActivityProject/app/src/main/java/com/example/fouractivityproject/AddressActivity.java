package com.example.fouractivityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddressActivity extends AppCompatActivity {

    private RadioGroup rdoGroup;
    private RadioButton rdoNaver;
    private RadioButton rdoGoogle;
    private RadioButton rdoDaum;

    private EditText editTextAdr;

    private Button btnMove;

    private String internetAddr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
        rdoNaver = (RadioButton) findViewById(R.id.rdoNaver);
        rdoGoogle = (RadioButton) findViewById(R.id.rdoGoogle);
        rdoDaum = (RadioButton) findViewById(R.id.rdoDaum);
        editTextAdr = (EditText) findViewById(R.id.editTextAdr);
        btnMove = (Button) findViewById(R.id.btnMove);

        // 그룹 선택
        rdoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

               handleRdoGroupListener(i);
            }
        });


        //이동 버튼
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnMoveListener(view);
            }
        });

    }

    // 그룹 선택
    private void handleRdoGroupListener(int i) {
        switch (i){
            case R.id.rdoNaver:
                internetAddr = "http://m.naver.com";
                break;
            case R.id.rdoGoogle:
                internetAddr = "http://google.com";
                break;
            case R.id.rdoDaum:
                internetAddr = "http://m.daum.net";
                break;
        }
    }

    // 이동 버튼
    private void handleBtnMoveListener(View view) {

        if (editTextAdr.getText().toString().equals("")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(internetAddr));
            startActivity(intent);
        }else{
            rdoGroup.clearCheck();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+editTextAdr.getText().toString()));
            startActivity(intent);
        }
    }
}