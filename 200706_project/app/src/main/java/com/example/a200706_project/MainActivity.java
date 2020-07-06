package com.example.a200706_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtNo1;
    private EditText edtNo2;
    private EditText edtResult;
    private RadioGroup rgCalculate;
    private RadioButton rbAdd;
    private RadioButton rbSub;
    private RadioButton rbMul;
    private RadioButton rbDiv;
    private Button btnSend;

    private String strCalResult;

    private final static int REQUEST_SUB1 = 101;
    private final static int REQUEST_SUB2 = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNo1 = (EditText) findViewById(R.id.edtNo1);
        edtNo2 = (EditText) findViewById(R.id.edtNo2);
        edtResult = (EditText) findViewById(R.id.edtResult);

        rgCalculate = (RadioGroup) findViewById(R.id.rgCalculate);
        rbAdd = (RadioButton) findViewById(R.id.rbAdd);
        rbSub  = (RadioButton) findViewById(R.id.rbSub);
        rbMul = (RadioButton) findViewById(R.id.rbMul);
        rbDiv = (RadioButton) findViewById(R.id.rbDiv);
        btnSend = (Button) findViewById(R.id.btnSend);


        rgCalculate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                handleRgCalListener(i);
            }
        });



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnSend(view);
            }
        });


    }

    private void handleRgCalListener(int i) {
        switch (i){
            case R.id.rbAdd: strCalResult = "덧셈"; break;
            case R.id.rbSub: strCalResult = "뺄셈"; break;
            case R.id.rbMul: strCalResult = "곱셈"; break;
            case R.id.rbDiv: strCalResult = "나눗셈"; break;
        }
    }

    private void handleBtnSend(View view) {
        Intent intent = new Intent(getApplicationContext(), Sub1Activity.class);
        ArrayList<StudentParse> list = new ArrayList<>();

        int num1 = Integer.parseInt(edtNo1.getText().toString());
        int num2 = Integer.parseInt(edtNo2.getText().toString());

        StudentParse studentParse = new StudentParse(num1, num2, strCalResult, 0);

        list.add(studentParse);

        intent.putExtra("list", list);

        startActivityForResult(intent,REQUEST_SUB1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_SUB1:
                ArrayList<StudentParse> list = data.getParcelableArrayListExtra("returnList");

                StudentParse studentParse = list.get(0);
                edtResult.setText(String.valueOf(studentParse.getResult()));
                break;
        }
    }
}
