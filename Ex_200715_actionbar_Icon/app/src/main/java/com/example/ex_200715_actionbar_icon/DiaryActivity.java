package com.example.ex_200715_actionbar_icon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiaryActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText editText;
    private Button button;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        calendarView = findViewById(R.id.calendarView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
               fileName = year+"_"+month+"_"+day+".txt";

                try {
                    // 파일 읽기
                    FileInputStream in = openFileInput(fileName);
                    byte[] content = new byte[2000];
                    in.read(content);
                    in.close();
                    editText.setText(new String(content));
                    button.setText("일기 수정하기");
                } catch (FileNotFoundException e) { //읽어올 내용이 없을 경우
                    // 로그 작성
                    Log.d("MainActivity", e.getMessage());

                    editText.setText("");
                    editText.setHint("기록이 없습니다.");
                    button.setText("일기 저장하기");
                }catch (IOException e1){ // 그 외의 예외

                    Log.d("MainActivity", e1.getMessage());

                    editText.setText("");
                    editText.setHint("기록 로드 에러. 점검요망");
                    button.setText("일기 저장하기");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream out = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String content = editText.getText().toString();
                    out.write(content.getBytes());
                    out.close();
                    Toast.makeText(getApplicationContext(), fileName+" 저장 완료", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.d("MainActivity", e.getMessage());
                    Toast.makeText(getApplicationContext(),"~저장~", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e1){
                    Toast.makeText(getApplicationContext(),"문제발생!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
