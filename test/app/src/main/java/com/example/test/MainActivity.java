package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 101;

    private EditText edtNo1;
    private EditText edtNo2;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private Button btnChange;

    private Button[] btnArray = new Button[10];
    private int[] idArray = {R.id.btn0, R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};

    private Button[] operatorArray = new Button[4];
    private int[] idArray_operator = {R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv};

    private TextView txtResult;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNo1 = (EditText) findViewById(R.id.edtNo1);
        edtNo2 = (EditText) findViewById(R.id.edtNo2);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnChange = (Button) findViewById(R.id.btnChange);


        // for문으로 연결 - 숫자 버튼
        for(int i = 0; i < btnArray.length; ++i){
            btnArray[i] = (Button) findViewById(idArray[i]);
        }

        // 연산 버튼 연결
        for(int i = 0; i < operatorArray.length; ++i){
            operatorArray[i] = (Button) findViewById(idArray_operator[i]);
        }

        // for문으로 숫자버튼 이벤트
        for(Button b : btnArray){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleBtnNumListener(view);
                }
            });
        }

        // 연산자버튼 이벤트
        for(Button b : operatorArray){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleOperatorListener(view);
                }
            });
        }

        // 화면 체인지 이벤트
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnChangeListener(view);
            }
        });

    }

    // 화면 체인지 리스너
    private void handleBtnChangeListener(View view) {

        Intent intent = new Intent(getApplicationContext(), Sub2Activity.class);

        int num1 = Integer.parseInt(edtNo1.getText().toString());
        int num2 = Integer.parseInt(edtNo2.getText().toString());

        CalculatedNum calculatedNum = new CalculatedNum(num1, num2, operator);

        intent.putExtra("calculInfo", calculatedNum);

        startActivityForResult(intent, REQUEST_CODE);
    }

    // 연산버튼 누르는 리스너
    private void handleOperatorListener(View view) {

        Button operatorBtn = (Button) view;
        operator = operatorBtn.getText().toString();

    }

    // 숫자 입력하는 리스너
    private void handleBtnNumListener(View view) {
        Button pushedBtn = (Button) view;

        if (edtNo1.isFocused() == true) {
            String input = edtNo1.getText().toString() + pushedBtn.getText().toString();
            edtNo1.setText(input);
        }else if (edtNo2.isFocused() == true) {
            String input = edtNo2.getText().toString() + pushedBtn.getText().toString();
            edtNo2.setText(input);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){

            CalculatedNum calculatedNum = (CalculatedNum) data.getSerializableExtra("result");

            txtResult.setText(String.valueOf(calculatedNum.getResult()));

        }
    }
}
