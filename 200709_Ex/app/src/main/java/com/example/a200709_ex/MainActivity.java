package com.example.a200709_ex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    public final int FRGMENT_1 = 1;
    public final int FRGMENT_2 = 2;
    public final int FRGMENT_3 = 3;

    private FrameLayout frameContent;

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

        changeFragment(FRGMENT_1);


    }

    private void findViewByIdFunction() {
        frameContent = (FrameLayout) findViewById(R.id.frameContent);
    }

    public void changeFragment(int fragmentNo){
        switch (fragmentNo){
            case FRGMENT_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment1).commit();
                break;
            case FRGMENT_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment2).commit();
                break;
            case FRGMENT_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fragment3).commit();
                break;
        }
    }
}
