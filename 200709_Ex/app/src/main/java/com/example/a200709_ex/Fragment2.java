package com.example.a200709_ex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private final int REQUEST_CODE = 101;

    private EditText edtNo1;
    private EditText edtNo2;

    private Button btnChange;

    private Button[] btnArray = new Button[10];
    private int[] idArray = {R.id.btn0, R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};

    private Button[] operatorArray = new Button[4];
    private int[] idArray_operator = {R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv};

    private TextView txtResult;
    private String operator;
    private MainActivity mainActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.flagment_2, container, false);

        findViewByIdFunction(rootView);

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

        return rootView;
    }

    // 화면 체인지 리스너
    private void handleBtnChangeListener(View view) {

        Intent intent = new Intent(mainActivity, Sub2Activity.class);

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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){

            CalculatedNum calculatedNum = (CalculatedNum) data.getSerializableExtra("result");

            txtResult.setText(String.valueOf(calculatedNum.getResult()));

        }
    }

    private void findViewByIdFunction(ViewGroup rootView) {
        edtNo1 = (EditText) rootView.findViewById(R.id.edtNo1);
        edtNo2 = (EditText)  rootView.findViewById(R.id.edtNo2);
        txtResult = (TextView) rootView.findViewById(R.id.txtResult);
        btnChange = (Button)  rootView.findViewById(R.id.btnChange);

        // for문으로 연결 - 숫자 버튼
        for(int i = 0; i < btnArray.length; ++i){
            btnArray[i] = (Button)  rootView.findViewById(idArray[i]);
        }

        // 연산 버튼 연결
        for(int i = 0; i < operatorArray.length; ++i){
            operatorArray[i] = (Button)  rootView.findViewById(idArray_operator[i]);
        }
    }
}
