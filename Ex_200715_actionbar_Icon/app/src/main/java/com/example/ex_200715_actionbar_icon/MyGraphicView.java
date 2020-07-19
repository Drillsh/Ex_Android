package com.example.ex_200715_actionbar_icon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyGraphicView extends View {
    public float sx, sy, angle, color, satur, blur;

    public MyGraphicView(Context context) {
        super(context);
        this.sx = 0.4f;
        this.sy = 0.4f;
        this.angle = 0.0f;
        this.color = 1.0f;
        this.satur = 1.0f;
        this.blur = 0.1f;
    }

    public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        // 해당된 이미지를 가져와서 그린다
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.han);

        // 좌표
        int px = this.getWidth()/2;
        int py = this.getHeight()/2;

        // 비율
        canvas.scale(sx, sy, px, py);

        // 회전
        canvas.rotate(angle, px, py);

        // 색상 밝기를 조절
        float[] array = {color, 0, 0, 0, 0,
                0, color, 0, 0, 0,
                0, 0, color, 0, 0,
                0, 0, 0, 1, 0};
        ColorMatrix colorMatrix = new ColorMatrix(array);

        // 흑백과 칼라 선택
        if (this.satur == 0) {
            colorMatrix.setSaturation(satur);
        }

        // 엠보싱
//        EmbossMaskFilter embossMaskFilter;
//        embossMaskFilter = new EmbossMaskFilter(new float[]{3.0f,3.0f,3.0f}, 1.0f, 5.0f, 10.0f );
//
//        paint.setMaskFilter(embossMaskFilter);

        // 블러
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(blur, BlurMaskFilter.Blur.NORMAL);

        paint.setMaskFilter(blurMaskFilter);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        // 캔버스 중앙위치 좌표구하기
        int centerX = ( this.getWidth() - bitmap.getWidth() ) / 2;
        int centerY = ( this.getHeight() - bitmap.getHeight() ) / 2;

        // 화면을 그린다
        canvas.drawBitmap(bitmap, centerX, centerY, paint);

        // 비트맵을 메모리에서 삭제한다
        bitmap.recycle();
    }
}
