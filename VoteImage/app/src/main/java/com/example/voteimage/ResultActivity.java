package com.example.voteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private RatingBar[] ratingBars = new RatingBar[9];
    private int[] starIdArray = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};
    private TextView[] txtViewArray = new TextView[9];
    private int[] txtIdArray = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
    private TextView txtTopVoted;
    private ImageView ivTopVoted;

    private Button btnReturn;

    private ImageInfo[] imageInfos;
    private ImageInfo topRatingImage;

    private float topRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnReturn = findViewById(R.id.btnReturn);
        txtTopVoted = findViewById(R.id.txtTopVoted);
        ivTopVoted = findViewById(R.id.ivTopVoted);

        Intent intent = getIntent();
        imageInfos = (ImageInfo[])intent.getSerializableExtra("voteResult");

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int i = 0; i < ratingBars.length; ++i){
            ratingBars[i] = findViewById(starIdArray[i]);
            ratingBars[i].setRating(imageInfos[i].getCount());
            txtViewArray[i] = findViewById(txtIdArray[i]);
            txtViewArray[i].setText(imageInfos[i].getName());


            if (ratingBars[i].getRating() >= topRating) {

                topRating = ratingBars[i].getRating();
                topRatingImage = imageInfos[i];
            }
        }

        txtTopVoted.setText(topRatingImage.getName());
        ivTopVoted.setImageResource(topRatingImage.getImage());

    }
}
