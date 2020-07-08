package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Sub2Activity extends AppCompatActivity {

    private Button sBtn1;
    private Button sBtn2;
    private Button sBtn3;
    private Button btnResult;

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;

    private TextView txtResult;
    private Button btnSendResult;

    private CalculatedNum calculatedNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        findViewByIdFunction();

        // 인텐트
        Intent intent = getIntent();
        if (intent != null) {
            getIntentFunction(intent);
        }

        // 버튼1 화면 노출
        sBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.INVISIBLE);
                linearLayout3.setVisibility(View.INVISIBLE);
                linearLayout4.setVisibility(View.INVISIBLE);
            }
        });
        // 버튼2 화면 노출
        sBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.INVISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.INVISIBLE);
                linearLayout4.setVisibility(View.INVISIBLE);
            }
        });
        // 버튼3 화면 노출
        sBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.INVISIBLE);
                linearLayout2.setVisibility(View.INVISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.INVISIBLE);
            }
        });
        // 버튼4 화면 노출
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcuator();
                linearLayout1.setVisibility(View.INVISIBLE);
                linearLayout2.setVisibility(View.INVISIBLE);
                linearLayout3.setVisibility(View.INVISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);
            }
        });


        // 결과값 전송 버튼 이벤트
        btnSendResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnSendResultListener(view);
            }
        });
    }

    // 계산
    private void calcuator() {

        int num1 = calculatedNum.getNum1();
        int num2 = calculatedNum.getNum2();
        String operator = calculatedNum.getOperator();

        int result = 0;

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
        }

        calculatedNum.setResult(result);
        txtResult.setText(String.valueOf(result));
    }

    //인텐트 받아오기
    private void getIntentFunction(Intent intent) {
        calculatedNum = (CalculatedNum) intent.getSerializableExtra("calculInfo");
    }

    // 결과값 전송 리스너
    private void handleBtnSendResultListener(View view) {
        Intent intent = new Intent();

        intent.putExtra("result", calculatedNum);
        setResult(1011, intent);
        finish();
    }

    // 아이디 초기화
    private void findViewByIdFunction() {
        sBtn1 = (Button) findViewById(R.id.sBtn1);
        sBtn2 = (Button) findViewById(R.id.sBtn2);
        sBtn3 = (Button) findViewById(R.id.sBtn3);
        btnResult = (Button) findViewById(R.id.btnResult);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnSendResult = (Button) findViewById(R.id.btnSendResult);

        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
    }
}
