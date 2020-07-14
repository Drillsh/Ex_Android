package com.example.slidepageproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int FRAGMENT_1 = 1;
    private final int FRAGMENT_2 = 2;
    private final int FRAGMENT_3 = 3;

    private Button btnOpenClose;
    private LinearLayout linearSlidingPage;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private LinearLayout linear3;
    private FrameLayout frameContent;
    private Button sbtnMenu;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private boolean btnFlag = false;
    private Animation translate_left;
    private Animation translate_right;
    private Sub01_Screen sub01_screen;
    private Sub02_Screen sub02_screen;
    private Sub03_Screen sub03_screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animation 로더한다
        translate_left = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translate_right = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        btnOpenClose = (Button) findViewById(R.id.btnOpenClose);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        sbtnMenu = (Button) findViewById(R.id.sbtnMenu);
        linearSlidingPage = (LinearLayout) findViewById(R.id.linearSlidingPage);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        frameContent = (FrameLayout) findViewById(R.id.frameContent);
        linearSlidingPage.setVisibility(View.INVISIBLE);

        sub01_screen = new Sub01_Screen();
        sub02_screen = new Sub02_Screen();
        sub03_screen = new Sub03_Screen();

        //changeView(1);

        btnOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnOpenCloseListener(view);
            }
        });

        sbtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFlag = true;
                handleBtnOpenCloseListener(view);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, sub01_screen).commit();
                changeView(FRAGMENT_1);
                btnFlag = true;
                handleBtnOpenCloseListener(view);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFlag = true;
                changeView(FRAGMENT_2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFlag = true;
                changeView(FRAGMENT_3);
            }
        });
    }



    private void changeView(int i) {
        switch (i) {
            case FRAGMENT_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, sub01_screen).commit();
                break;
            case FRAGMENT_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, sub02_screen).commit();
            break;
            case FRAGMENT_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, sub03_screen).commit();
                break;
        }
    }

    // 열기 버튼 클릭시 linearSlidingPage 나타나고 닫기클릭시 사라짐
    private void handleBtnOpenCloseListener(View view) {


        if (btnFlag == true) {
            linearSlidingPage.startAnimation(translate_right);
            btnOpenClose.setText("열기");
            btnFlag = false;
        } else {
            linearSlidingPage.startAnimation(translate_left);
            btnOpenClose.setText("닫기");
            btnFlag = true;
        }
    }
}
