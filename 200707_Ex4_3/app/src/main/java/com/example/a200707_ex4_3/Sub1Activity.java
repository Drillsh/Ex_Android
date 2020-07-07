package com.example.a200707_ex4_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Sub1Activity extends AppCompatActivity {

    private TextView txtResult;
    private Button btnSend;
    private int result;

    ArrayList<NumParcel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        txtResult = (TextView) findViewById(R.id.txtResult);
        btnSend = (Button) findViewById(R.id.btnSend);

        Intent intent = getIntent();
        if (intent != null) {
            receiveIntent(intent);
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnSend(view);
            }
        });

    }

    private void handleBtnSend(View view) {

        Intent intent = new Intent();

        NumParcel resultNumParcel = list.get(0);

        resultNumParcel.setResult(result);

        list.clear();
        list.add(resultNumParcel);



        intent.putExtra("calOk", list);
        setResult(1011, intent);

        finish();

    }

    private void receiveIntent(Intent intent) {
        list = intent.getParcelableArrayListExtra("calculateRequest");

        NumParcel numParcel = list.get(0);

        int num1 = numParcel.getNum1();
        int num2 = numParcel.getNum2();

        String operator = numParcel.getOperator();

        switch (operator){
            case "더하기":
                result = num1 + num2;
                break;
            case "빼기":
                result = num1 - num2;
                break;
            case "곱하기":
                result = num1 * num2;
                break;
            case "나누기":
                result = num1 / num2;
                break;
            case "나머지":
                result = num1 % num2;
                break;
        }


        txtResult.setText(String.valueOf(result));
    }

}
