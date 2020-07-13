package com.example.chapter06test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnScreen1;
    private Button btnScreen2;
    private FrameLayout frameContent;

    //프래그먼트 객체를 저장할 변수 설정
    private Screen1Sub fragment1;
    private Screen2Sub fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //screen1sub 객체 생성
        fragment1 = new Screen1Sub();
        fragment2 = new Screen2Sub();

        findViewByIdFunction();
        changeScreen(1);

        //버튼1을 누르면 프래그먼트1 화면을 액티비티 - FrameLayout 보여준다
        btnScreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment1).commit();
            }
        });

        //버튼2을 누르면 프래그먼트1 화면을 액티비티 - FrameLayout 보여준다
        btnScreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment2).commit();
            }
        });
    }

    private void findViewByIdFunction() {
        btnScreen1 = (Button) findViewById(R.id.btnScreen1);
        btnScreen2= (Button) findViewById(R.id.btnScreen2);

        frameContent = (FrameLayout) findViewById(R.id.frameContent);
    }

    public void changeScreen(int no){
        switch (no){
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment1).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment2).commit();
                break;
        }
    }
}

