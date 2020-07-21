package com.example.a200721_galleryadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Gallery gallery;
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

        gallery = findViewById(R.id.gallery);
        ivPoster = findViewById(R.id.ivPoster);

        for (int i = 0; i < 10; ++i){
            arrayList.add(new Movie(movieName[i], movieArray[i]));
        }

        // 어댑터 생성
        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(getApplicationContext());

        // 어댑터 리스트에 객체리스트 세팅
        myCustomAdapter.setArrayList(this.arrayList);

        // 갤러리에 어댑터 세팅
        gallery.setAdapter(myCustomAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ivPoster.setImageResource(arrayList.get(i).getImgId());
                Toast.makeText(getApplicationContext(), arrayList.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
