package com.example.ex_200715_actionbar_icon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DrawingPadActivity extends AppCompatActivity {
    public static final int LINE = 1;
    public static final int CIRCLE = 2;
    public static final int RECT = 3;
    // 기본값 선=1, 원=2;
    public int flag = 1;

    public int color = Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawingpad_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLine:
                flag = LINE;
                break;
            case R.id.menuCircle:
                flag = CIRCLE;
                break;
            case R.id.menuRect:
                flag = RECT;
                break;
            case R.id.menuRed:
                color = Color.RED;
                break;
            case R.id.menuBlue:
                color = Color.BLUE;
                break;
            case R.id.menuGreen:
                color = Color.GREEN;
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyGraphicView extends View {

        private float startX;
        private float startY;
        private float stopX;
        private float stopY;

        private float moveX, moveY;

        private ArrayList<Data> list;

        public MyGraphicView(Context context) {
            super(context);
            this.startX = -1;
            this.startY = -1;
            this.stopX = -1;
            this.stopY = -1;
            this.moveX = -1;
            this.moveY = -1;

            list = new ArrayList<>();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(color);

            switch (flag) {
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    float radius = (float) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - stopX, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECT:
                    canvas.drawRect(startX, startY, stopX, stopY, paint);
                    break;
            }
        }

        private class Data {
            private float x = -1;
            private float y = -1;

            private Data(float x, float y) {
                this.x = x;
                this.y = y;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    list.clear();
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveX = event.getX();
                    moveY = event.getY();
                    Data data = new Data(moveX, moveY);
                    list.add(data);
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = event.getX();
                    stopY = event.getY();

                    this.invalidate();
                    Log.d("MyGraphicView", "이동횟수=" + list.size());

                    break;
            }
            return true;
        }

    }
}
