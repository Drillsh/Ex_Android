package com.example.a200706_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Sub1Activity extends AppCompatActivity {

    private EditText s1edtNo1;
    private EditText s1edtNo2;
    private EditText s1edtResult;
    private Button s1btnCalculate;
    private Button s1btnReturn;

    ArrayList<StudentParse> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        s1edtNo1 = (EditText) findViewById(R.id.s1edtNo1);
        s1edtNo2 = (EditText) findViewById(R.id.s1edtNo2);
        s1edtResult = (EditText) findViewById(R.id.s1edtResult);
        s1btnCalculate = (Button) findViewById(R.id.s1btnCalculate);
        s1btnReturn = (Button) findViewById(R.id.s1btnReturn);

        Intent intent = getIntent();
        if (intent != null) {
            getDataFromMain(intent);
        }

        s1btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnReturn(view);
            }
        });

    }

    private void handleBtnReturn(View view) {
        Intent intent = new Intent();

        StudentParse studentParse = list.get(0);
        studentParse.setResult(Integer.parseInt(s1edtResult.getText().toString()));
        list.remove(0);
        list.add(0, studentParse);

        intent.putExtra("returnList", list);

        setResult(1011, intent);
        finish();
    }

    private void getDataFromMain(Intent intent) {

        list = intent.getParcelableArrayListExtra("list");

        StudentParse studentParse = list.get(0);

        int num1 = studentParse.getNumber1();
        int num2 = studentParse.getNumber2();
        String operation = studentParse.getCalculate();
        int sum = 0;
        switch (operation){
            case "덧셈":
                s1btnCalculate.setText("덧셈");
                sum = num1 + num2;
                break;
            case "뺄셈":
                s1btnCalculate.setText("뺄셈");
                sum = num1 - num2;
                break;
            case "곱셈":
                s1btnCalculate.setText("곱셈");
                sum = num1 * num2;
                break;
            case "나눗셈":
                s1btnCalculate.setText("나눗셈");
                sum = num1 / num2;
                break;
        }

        s1edtNo1.setText(String.valueOf(num1));
        s1edtNo2.setText(String.valueOf(num2));
        s1edtResult.setText(String.valueOf(sum));
    }



}
