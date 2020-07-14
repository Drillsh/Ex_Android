package com.example.a200709_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Sub2Activity extends AppCompatActivity {

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

        calcuator();

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

        txtResult = (TextView) findViewById(R.id.txtResult);
        btnSendResult = (Button) findViewById(R.id.btnSendResult);


        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
    }
}
