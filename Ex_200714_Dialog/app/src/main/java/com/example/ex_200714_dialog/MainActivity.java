package com.example.ex_200714_dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

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

    private Animation translate_left;
    private Animation translate_right;

    private LinearLayout linearSlidingPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animation 로더한다
        translate_left = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translate_right = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        linearSlidingPage = findViewById(R.id.linearSlidingPage);
        linearSlidingPage.setVisibility(View.INVISIBLE);

        createTabHost();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        View sw = menu.findItem(R.id.swSlide).getActionView();

        Switch swSlide = (Switch) sw.findViewById(R.id.swSliding);

        swSlide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

               if (b) {
                   linearSlidingPage.startAnimation(translate_left);
               } else {
                   linearSlidingPage.startAnimation(translate_right);
               }
           }
       });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuTo2:
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void createTabHost() {
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);

        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.tab_one);

        imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.tab_two);

        imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.tab_three);

        tabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.tabRealContent);

        tabSpecOne = tabHost.newTabSpec("one").setIndicator(imageView);
        tabSpecTwo = tabHost.newTabSpec("two").setIndicator(imageView2);
        tabSpecThree = tabHost.newTabSpec("three").setIndicator(imageView3);

        tabHost.addTab(tabSpecOne, FragmentOne.class, null);
        tabHost.addTab(tabSpecTwo, FragmentTwo.class, null);
        tabHost.addTab(tabSpecThree, FragmentThree.class, null);

        changeFragment(FRAGMENT_1);
    }

    public void changeFragment(int fragmnetNo) {

        tabHost.setCurrentTab(fragmnetNo);
    }


}
