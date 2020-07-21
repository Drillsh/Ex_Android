package com.example.a200721_adapterview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Poketmon> arrayList = new ArrayList<>();
    private Integer[] pkArray = {
            R.drawable.pk1,
            R.drawable.pk2,
            R.drawable.pk3,
            R.drawable.pk4,
            R.drawable.pk5,
            R.drawable.pk6,
            R.drawable.pk7,
            R.drawable.pk8,
            R.drawable.pk9,
            R.drawable.pk10,
            R.drawable.pk11,
            R.drawable.pk12,
            R.drawable.pk13,
            R.drawable.pk14,
            R.drawable.pk15,
            R.drawable.pk16,
            R.drawable.pk17,
            R.drawable.pk18
    };

    private String[] pkName = {"이상해씨", "이상해풀", "이상해꽃", "꼬부기", "어니부기", "거북왕",
    "파이리", "리자드", "리자몽", "치코리타", "베이리프", "메가니움", "브케인", "마그케인", "블레이범",
    "리아코", "엘리게이", "장크로다일"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);

        for(int i = 0; i < 18; ++i){
            arrayList.add(new Poketmon(pkName[i],pkArray[i]));
        }

        // 어댑터 생성
        MyCutomAdapter myCutomAdapter = new MyCutomAdapter(getApplicationContext());

        // 어댑터의 리스트에 객체리스트 셋
        myCutomAdapter.setArrayList(this.arrayList);

        // 그리드뷰에 어댑터 세팅
        gridView.setAdapter(myCutomAdapter);

        // 그리드뷰 클릭 이벤트
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popDialog(i);
            }
        });
    }

    //그리드뷰 클릭 이벤트
    private void popDialog(int position) {
        final TextView txtPopName;
        final ImageView imgPopImg;

        //다이얼로그 화면 메모리에 올림
        View dialogView = View.inflate(getApplicationContext(), R.layout.popup_dialog, null);

        // UI 객체를 찾아서 매치
        txtPopName = dialogView.findViewById(R.id.txtPopName);
        imgPopImg = dialogView.findViewById(R.id.imgPopImg);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("포켓몬 정보");

        //입력할 화면
        dialog.setView(dialogView);
        txtPopName.setText(arrayList.get(position).getName());
        imgPopImg.setImageResource(arrayList.get(position).getImageId());
        Glide.with(getApplicationContext()).load(arrayList.get(position).getImageId()).into(imgPopImg);

        dialog.setNegativeButton("취 소", null);

        dialog.show();
    }
}
