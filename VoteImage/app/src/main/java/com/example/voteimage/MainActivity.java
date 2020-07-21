package com.example.voteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private static final int REQUEST_CODE = 101;
//    private static final int RESULT_CODE = 103;

    private ImageView[] ivList = new ImageView[9];
    private Integer[] idList = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};
    private int[] count = new int[9];
    private Integer[] imageFileId = {R.drawable.chaewon, R.drawable.yeana, R.drawable.hyewon, R.drawable.eunbi,
            R.drawable.yujin, R.drawable.yuri, R.drawable.sakura, R.drawable.wonyoung, R.drawable.minju};

    private Button btnResult;

    private ImageInfo[] imageInfos = new ImageInfo[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResult = findViewById(R.id.btnResult);
        btnResult.setOnClickListener(this);

        for (int i = 0; i < ivList.length; ++i) {
            ivList[i] = findViewById(idList[i]);
            ivList[i].setOnClickListener(this);

            imageInfos[i] = new ImageInfo();
            imageInfos[i].setImage(imageFileId[i]);
        }
        initializeImageName();
    }

    private void initializeImageName() {

        imageInfos[0].setName("김채원");
        imageInfos[1].setName("최예나");
        imageInfos[2].setName("강혜원");
        imageInfos[3].setName("강은비");
        imageInfos[4].setName("안유진");
        imageInfos[5].setName("조유리");
        imageInfos[6].setName("사쿠라");
        imageInfos[7].setName("장원영");
        imageInfos[8].setName("김민주");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnResult) {

            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("voteResult", imageInfos);
            startActivity(intent);

        } else {
            for (int i = 0; i < ivList.length; ++i) {
                if (idList[i] == view.getId()) {
                    count[i]++;
                    Toast.makeText(getApplicationContext(), imageInfos[i].getName() + " " + count[i] + "표", Toast.LENGTH_SHORT).show();
                    imageInfos[i].setCount(count[i]);
                    break;
                }
            }
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE) {
//            if (resultCode == RESULT_CODE) {
//
//            }
//        }
//    }

}


