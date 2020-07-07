package com.example.a200707_ex4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Switch swStart;
    private RadioGroup rgChoice;
    private RadioButton rdo1;
    private RadioButton rdo2;
    private RadioButton rdo3;
    private ImageView imgChange;
    private Button btnExit;
    private Button btnReset;
    private LinearLayout layoutHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swStart = (Switch) findViewById(R.id.swStart);
        rgChoice = (RadioGroup) findViewById(R.id.rgChoice);
        rdo1 = (RadioButton) findViewById(R.id.rdo1);
        rdo2 = (RadioButton) findViewById(R.id.rdo2);
        rdo3 = (RadioButton) findViewById(R.id.rdo3);
        imgChange = (ImageView) findViewById(R.id.imgChange);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnReset = (Button) findViewById(R.id.btnReset);
        layoutHide = (LinearLayout) findViewById(R.id.layoutHide);

        // 스위치
//        swStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                txtLike.setVisibility(View.VISIBLE);
//                rgChoice.setVisibility(View.VISIBLE);
//                rdo1.setVisibility(View.VISIBLE);
//                rdo2.setVisibility(View.VISIBLE);
//                rdo3.setVisibility(View.VISIBLE);
//                imgChange.setVisibility(View.VISIBLE);
//                btnExit.setVisibility(View.VISIBLE);
//                btnReset.setVisibility(View.VISIBLE);
//            }
//        });

        layoutHide.setVisibility(View.INVISIBLE);

        // 스위치 리스너
        swStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    layoutHide.setVisibility(View.VISIBLE);
                }else{
                    layoutHide.setVisibility(View.INVISIBLE);
                }
            }
        });


        // 라디오 그룹 리스너
        rgChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rdo1:
                        imgChange.setImageResource(R.drawable.lollipop);
                        break;
                    case R.id.rdo2:
                        imgChange.setImageResource(R.drawable.kitkat);
                        break;
                    case R.id.rdo3:
                        imgChange.setImageResource(R.drawable.jellybean);
                        break;
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgChange.setImageResource(R.mipmap.ic_launcher_round);
                rgChoice.clearCheck();
                swStart.setChecked(false);
            }
        });

    }
}
