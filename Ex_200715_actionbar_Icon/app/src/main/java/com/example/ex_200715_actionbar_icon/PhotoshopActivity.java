package com.example.ex_200715_actionbar_icon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PhotoshopActivity extends AppCompatActivity implements View.OnClickListener {


    public int flag = 1;

    private MyGraphicView myGraphicView;
    private LinearLayout pictureLayout;

    private ImageButton ibtnZoomIn;
    private ImageButton ibtnZoomOut;
    private ImageButton ibtnRotate;
    private ImageButton ibtnBright;
    private ImageButton ibtnDark;
    private ImageButton ibtnGray;
    private ImageButton ibtnBlur;
    private boolean bFlag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoshop);
        findViewByIdFunction();

        myGraphicView = new MyGraphicView(this);
// linearView에 그래픽뷰를 추가, 중앙 배치
        pictureLayout.addView(myGraphicView);
        pictureLayout.setGravity(Gravity.CENTER);

        ibtnZoomIn.setOnClickListener(this);
        ibtnZoomOut.setOnClickListener(this);
        ibtnRotate.setOnClickListener(this);
        ibtnBright.setOnClickListener(this);
        ibtnDark.setOnClickListener(this);
        ibtnGray.setOnClickListener(this);
        ibtnBlur.setOnClickListener(this);

    }

    private void findViewByIdFunction() {
        ibtnZoomIn = findViewById(R.id.iBtnZoomIn);
        ibtnZoomOut = findViewById(R.id.iBtnZoomOut);
        ibtnRotate = findViewById(R.id.iBtnRotate);
        ibtnBright = findViewById(R.id.iBtnBright);
        ibtnDark = findViewById(R.id.iBtnDark);
        ibtnGray = findViewById(R.id.iBtnGray);
        ibtnBlur = findViewById(R.id.iBtnBlur);
        pictureLayout = findViewById(R.id.pictureLayout);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iBtnZoomIn:
                myGraphicView.sx += 0.2f;
                myGraphicView.sy += 0.2f;
                break;
            case R.id.iBtnZoomOut:
                myGraphicView.sx -= 0.2f;
                myGraphicView.sy -= 0.2f;
                break;
            case R.id.iBtnRotate:
                myGraphicView.angle += 10.0f;
                break;
            case R.id.iBtnBright:
                myGraphicView.color += 0.2f;
                break;
            case R.id.iBtnDark:
                myGraphicView.color -= 0.2f;
                break;
            case R.id.iBtnGray:
                if (flag == 1) {
                    myGraphicView.satur = 0.0f;
                    flag = 0;
                } else {
                    myGraphicView.satur = 1.0f;
                    flag = 1;
                }
                break;
            case R.id.iBtnBlur:
                if (bFlag == true) {
                    myGraphicView.blur = 80.0f;
                    bFlag = false;
                } else {
                    myGraphicView.blur = 0.1f;
                    bFlag = true;
                }
                break;

            default:
                break;
        }
        // 바뀐 값으로 다시한번 그려준다
        myGraphicView.invalidate();
    }
}
