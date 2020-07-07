package com.example.a200707_ex4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Switch swStart;
    private TextView txtLike;
    private RadioGroup rgChoice;
    private RadioButton rdo1;
    private RadioButton rdo2;
    private RadioButton rdo3;
    private ImageView imgChange;
    private Button btnExit;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swStart = (Switch) findViewById(R.id.swStart);
        txtLike = (TextView) findViewById(R.id.txtLike);
        rgChoice = (RadioGroup) findViewById(R.id.rgChoice);
        rdo1 = (RadioButton) findViewById(R.id.rdo1);
        rdo2 = (RadioButton) findViewById(R.id.rdo2);
        rdo3 = (RadioButton) findViewById(R.id.rdo3);
        imgChange = (ImageView) findViewById(R.id.imgChange);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnReset = (Button) findViewById(R.id.btnReset);

        swStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLike.setVisibility(View.VISIBLE);
                rgChoice.setVisibility(View.VISIBLE);
                rdo1.setVisibility(View.VISIBLE);
                rdo2.setVisibility(View.VISIBLE);
                rdo3.setVisibility(View.VISIBLE);
                imgChange.setVisibility(View.VISIBLE);
                btnExit.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);
            }
        });

        rgChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (rgChoice.getCheckedRadioButtonId()){
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
                swStart.setChecked(false);
                txtLike.setVisibility(View.INVISIBLE);
                rgChoice.setVisibility(View.INVISIBLE);
                rdo1.setVisibility(View.INVISIBLE);
                rdo2.setVisibility(View.INVISIBLE);
                rdo3.setVisibility(View.INVISIBLE);
                imgChange.setVisibility(View.INVISIBLE);
                btnExit.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.INVISIBLE);
            }
        });

    }
}
