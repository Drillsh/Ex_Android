package com.example.a200721_spinneradapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;
    private ImageView ivPoster;

    private ArrayList<Movie> arrayList = new ArrayList<>();
    private Integer[] movieArray = {
            R.drawable.mov01,
            R.drawable.mov02,
            R.drawable.mov03,
            R.drawable.mov04,
            R.drawable.mov05,
            R.drawable.mov06,
            R.drawable.mov07,
            R.drawable.mov08,
            R.drawable.mov09,
            R.drawable.mov10,
    };

    private String[] movieName = {"써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골",
            "헬보이", "백투더퓨처"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner1);
        ivPoster = findViewById(R.id.ivPoster);

        for (int i = 0; i < 10; ++i){
            arrayList.add(new Movie(movieName[i], movieArray[i]));
        }

        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(getApplicationContext());

        myCustomAdapter.setArrayList(this.arrayList);

        spinner1.setAdapter(myCustomAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ivPoster.setImageResource(arrayList.get(i).getImgId());
                Toast.makeText(getApplicationContext(), arrayList.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
