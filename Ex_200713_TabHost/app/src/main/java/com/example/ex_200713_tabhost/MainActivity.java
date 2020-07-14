package com.example.ex_200713_tabhost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    public final int FRAGMENT_1 = 0;
    public final int FRAGMENT_2 = 1;
    public final int FRAGMENT_3 = 2;

    private FragmentTabHost tabHost;

    private TabHost.TabSpec tabSpecOne;
    private TabHost.TabSpec tabSpecTwo;
    private TabHost.TabSpec tabSpecThree;

    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIdFunction();
    }

    private void findViewByIdFunction() {

        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        // 탭호스트에 프래그먼트 매니저를 다른 프레임아웃에 저장
        tabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.tabRealContent);

        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.tab_one);

        imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.tab_two);

        imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.tab_three);


        tabSpecOne = tabHost.newTabSpec("One").setIndicator(imageView);
        tabSpecTwo = tabHost.newTabSpec("Two").setIndicator(imageView2);
        tabSpecThree = tabHost.newTabSpec("Three").setIndicator(imageView3);

        tabHost.addTab(tabSpecOne, FragmentOne.class, null);
        tabHost.addTab(tabSpecTwo, FragmentTwo.class, null);
        tabHost.addTab(tabSpecThree, FragmentThree.class, null);

        tabHost.setCurrentTab(FRAGMENT_1);
    }

    public void changeFragment(int fragmentNo){
        tabHost.setCurrentTab(fragmentNo);
    }
}
