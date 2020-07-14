package com.example.a200709_ex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public final int FRAGMENT_1 = 1;
    public final int FRAGMENT_2 = 2;
    public final int FRAGMENT_3 = 3;

    private FrameLayout frameContent;
    private LinearLayout linearSlidingPage;
    private Button btnOpenClose;
    private Button sbtnMenu;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private boolean btnFlag = false;
    private Animation translate_left;
    private Animation translate_right;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        findViewByIdFunction();

        changeFragment(FRAGMENT_1);


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
                changeFragment(FRAGMENT_1);
                btnFlag = true;
                handleBtnOpenCloseListener(view);
            }
        });
        btn1.setClickable(false);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFlag = true;
                changeFragment(FRAGMENT_2);
                handleBtnOpenCloseListener(view);
            }
        });
        btn2.setClickable(false);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFlag = true;
                changeFragment(FRAGMENT_3);
                handleBtnOpenCloseListener(view);
            }
        });
        btn3.setClickable(false);

    }

    private void findViewByIdFunction() {
        frameContent = (FrameLayout) findViewById(R.id.frameContent);
        linearSlidingPage = (LinearLayout) findViewById(R.id.linearSlidingPage);
        //Animation 로더한다
        translate_left = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translate_right = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        btnOpenClose = (Button) findViewById(R.id.btnOpenClose);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        sbtnMenu = (Button) findViewById(R.id.sbtnMenu);
        linearSlidingPage = (LinearLayout) findViewById(R.id.linearSlidingPage);

        frameContent = (FrameLayout) findViewById(R.id.frameContent);
        linearSlidingPage.setVisibility(View.INVISIBLE);
    }

    public void changeFragment(int fragmentNo){
        switch (fragmentNo){
            case FRAGMENT_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment1).commit();
                break;
            case FRAGMENT_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment2).commit();
                break;
            case FRAGMENT_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment3).commit();
                break;
        }
    }

    // 열기 버튼 클릭시 linearSlidingPage 나타나고 닫기클릭시 사라짐
    private void handleBtnOpenCloseListener(View view) {

        if (btnFlag == true) {
            linearSlidingPage.startAnimation(translate_right);
            btnOpenClose.setText("열기");
            btnFlag = false;
            btn1.setClickable(false);
            btn2.setClickable(false);
            btn3.setClickable(false);

        } else {
            linearSlidingPage.startAnimation(translate_left);
            btnOpenClose.setText("닫기");
            btnFlag = true;

            btn1.setClickable(true);
            btn2.setClickable(true);
            btn3.setClickable(true);
        }
    }
}
