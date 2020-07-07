package com.example.a200707_ex4_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtNum1;
    private EditText edtNum2;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private Button btnRest;
    private TextView edtResult;

    private String operator;

    private ArrayList<NumParcel> list = new ArrayList<>();
    private final static int REQUEST_SUB1 = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum1 = (EditText) findViewById(R.id.edtNum1);
        edtNum2 = (EditText) findViewById(R.id.edtNum2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnRest = (Button) findViewById(R.id.btnRest);
        edtResult = (TextView) findViewById(R.id.edtResult);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnListener(view);
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnListener(view);
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnListener(view);
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnListener(view);
            }
        });
        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnListener(view);
            }
        });}

    private void handleBtnListener(View view) {

        // 빈칸인데 클릭시 메시지
        if (edtNum1.getText().toString().equals("") || edtNum2.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();

            return;
        }

        Button clickedBtn = (Button) view;

        switch (clickedBtn.getText().toString()) {

            case "더하기":
                operator = "더하기";
                break;
            case "빼기":
                operator = "빼기";
                break;
            case "곱하기":
                operator = "곱하기";
                break;
            case "나누기":
                operator = "나누기";
                break;
            case "나머지":
                operator = "나머지";
                break;
        }

        int num1 = Integer.parseInt(edtNum1.getText().toString());
        int num2 = Integer.parseInt(edtNum2.getText().toString());

        NumParcel numParcel = new NumParcel(num1, num2, operator, 0);

        list.add(numParcel);

        Intent intent = new Intent(getApplicationContext(), Sub1Activity.class);

        intent.putExtra("calculateRequest", list);

        startActivityForResult(intent,REQUEST_SUB1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_SUB1:
                ArrayList<NumParcel> list = data.getParcelableArrayListExtra("calOk");

                NumParcel numParcel = list.get(0);
                edtResult.setText(String.valueOf(numParcel.getResult()));
                break;
        }
    }
}
