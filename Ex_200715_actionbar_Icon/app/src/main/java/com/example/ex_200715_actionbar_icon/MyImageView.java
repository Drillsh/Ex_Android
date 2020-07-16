package com.example.ex_200715_actionbar_icon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyImageView extends View {

    private String imagePath;

    // View 의 상속을 받는 UI 객체
    // 매개변수 1개있는 UI는 클래스내에서 객체로 만들어서 사용한다.
    public MyImageView(Context context) {
        super(context);
    }

    // 매개변수 2개가 있는 UI는 xml에서 드래그 앤 드롭으로
    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.imagePath = null;
    }

    // MyImageView 객체가 만들어지면 화면에 그려질때 자동으로 불리는 콜백함수
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(imagePath != null){
            //비트맵
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            //캔버스에 그린다
            canvas.scale(canvas.getWidth()/bitmap.getWidth(),canvas.getHeight()/bitmap.getHeight(),0,0);
            //캔버스에 비트맵을 그려넣는다
            canvas.drawBitmap(bitmap, 0, 0, null);

            bitmap.recycle();
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
