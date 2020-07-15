package com.example.ex_200715_actionbar_icon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int FRAGMENT_1 = 0;
    private final int FRAGMENT_2 = 1;
    private final int FRAGMENT_3 = 2;

    public Fragment1 fragment1;
    public Fragment2 fragment2;
    public Fragment3 fragment3;

    private LinearLayout slidingPage;
    private Button btnToFrag1;
    private Button btnToFrag2;
    private Button btnToFrag3;

    private Animation sliding_close;
    private Animation sliding_open;

    private boolean btnFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트 객체화
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        //프래그먼트 전환
        changeFragment(FRAGMENT_1);


        //슬라이딩 메뉴
        slidingPage = findViewById(R.id.slidingPage);
        // Animation 로더
        sliding_close = AnimationUtils.loadAnimation(this, R.anim.sliding_close);
        sliding_open = AnimationUtils.loadAnimation(this, R.anim.sliding_open);

        // 슬라이딩 페이지 내 버튼
        btnToFrag1 = findViewById(R.id.btnToFrag1);
        btnToFrag2 = findViewById(R.id.btnToFrag2);
        btnToFrag3 = findViewById(R.id.btnToFrag3);

        slidingPage.setVisibility(View.INVISIBLE);

        btnToFrag1.setOnClickListener(this);
        btnToFrag2.setOnClickListener(this);
        btnToFrag3.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 옵션 메뉴 객체화
        getMenuInflater().inflate(R.menu.actionbar_icon, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fragment1:
                changeFragment(FRAGMENT_1);
                break;
            case R.id.fragment2:
                changeFragment(FRAGMENT_2);
                break;
            case R.id.fragment3:
                changeFragment(FRAGMENT_3);
                break;
            case R.id.iconMoveToActivity: // 액티비티 이동
                Intent intent = new Intent(getApplicationContext(), DiaryActivity.class);
                startActivity(intent);
                break;
            case R.id.iconSlide: //슬라이딩 메뉴
                slidingPageMoving();
        }
        return super.onOptionsItemSelected(item);
    }

    private void slidingPageMoving() {
        slidingPage.bringToFront();

        if(btnFlag){
            slidingPage.startAnimation(sliding_close);
            btnFlag = false;
        }else{
            slidingPage.startAnimation(sliding_open);
            btnFlag = true;
        }
    }

    public void changeFragment(int fragmentNum){
        switch (fragmentNum){
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnToFrag1:
                changeFragment(FRAGMENT_1);
                slidingPageMoving();
                break;
            case R.id.btnToFrag2:
                changeFragment(FRAGMENT_2);
                slidingPageMoving();
                break;
            case R.id.btnToFrag3:
                changeFragment(FRAGMENT_3);
                slidingPageMoving();
                break;
        }
    }
}
