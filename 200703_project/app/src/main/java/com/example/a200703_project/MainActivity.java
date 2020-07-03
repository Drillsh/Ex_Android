package com.example.a200703_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPhone;

    private Switch switchHide;

    private RadioGroup rgPhoto;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    private Button btnOK;

    private String photoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);

        switchHide = (Switch) findViewById(R.id.switchHide);

        rgPhoto = (RadioGroup) findViewById(R.id.rgPhoto);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);

        btnOK = (Button) findViewById(R.id.btnOK);

        rgPhoto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton:
                        photoName = "LION";
                        break;
                    case R.id.radioButton2:
                        photoName = "MUJI";
                        break;
                    case R.id.radioButton3:
                        photoName = "NEO";
                        break;
                    case R.id.radioButton4:
                        photoName = "PRODO";
                        break;
                }
            }
        });

        // 완료 버튼 클릭
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnOKListener(view);
            }
        });
        // 완료 버튼 드래그
        btnOK.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                finish();
                return true;
            }
        });
    }


    // 완료 버튼 클릭
    private void handleBtnOKListener(View view) {
        Intent intent = new Intent(getApplicationContext(), completeActivity.class);
        intent.putExtra("Name", etName.getText().toString());
        intent.putExtra("PhoneNo", etPhone.getText().toString());
        intent.putExtra("Photo", photoName);

        intent.putExtra("HideNum", switchHide.isChecked());

        startActivity(intent);
    }
}
