package com.example.a200709_ex;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private Chronometer chroTime;
    private Button btnTimeStart;
    private RadioGroup rgSetting;
    private RadioButton rdoDate;
    private RadioButton rdoTime;

    private CalendarView calendarView;
    private TimePicker timePicker;

    private Button btnFinish;
    private TextView txtFinalTime;
    private Button btnToCal;
    private Button btnToFrag3;
    private LinearLayout layoutHide;

    private int year;
    private int month;
    private int day;
    private MainActivity mainActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // flagment_1 가져와서 객체화 한다.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.flagment_1, container, false);

        findViewByIdFunction(rootView);

        //예약 시작버튼 리스너
        btnTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnTimeStartListener(view);
            }
        });

        // 라디오 그룹 선택 리스너
        rgSetting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                handleRgChangeListener(i);
            }
        });

        // 캘린더 리스너 -> 날짜 저장
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Fragment1.this.year = i;
                Fragment1.this.month = i1 + 1;
                Fragment1.this.day = i2;
            }
        });

        // 예약 완료 버튼 리스너
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnFinishListener(view);
            }
        });

        // Fragment2 - 계산기로 전환
        btnToCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeFragment(mainActivity.FRGMENT_2);
            }
        });

        btnToFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeFragment(mainActivity.FRGMENT_3);
            }
        });

        return rootView;
    }

    // 예약 완료 버튼 리스너
    private void handleBtnFinishListener(View view) {
        chroTime.stop();
        int hour = timePicker.getHour();
        int min = timePicker.getMinute();

        String str = year +"년" + month +"월" + day + "일" + hour + "시" + min + "분 예약됨";
        txtFinalTime.setText(str);
    }

    // 라디오 그룹 선택 리스너
    private void handleRgChangeListener(int i) {
        switch (i) {

            case R.id.rdoDate:
                calendarView.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
                break;

            case R.id.rdoTime:
                calendarView.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
                break;
        }
    }



    //예약 시작버튼 리스너
    private void handleBtnTimeStartListener(View view) {
        layoutHide.setVisibility(View.VISIBLE);

        chroTime.setBase(SystemClock.elapsedRealtime());
        chroTime.start();
        chroTime.setTextColor(Color.RED);
    }

    private void findViewByIdFunction(ViewGroup rootView) {

        chroTime = (Chronometer) rootView.findViewById(R.id.chroTime);
        btnTimeStart = (Button) rootView.findViewById(R.id.btnTimeStart);
        rgSetting = (RadioGroup) rootView.findViewById(R.id.rgSetting);
        rdoDate = (RadioButton) rootView.findViewById(R.id.rdoDate);
        rdoTime = (RadioButton) rootView.findViewById(R.id.rdoTime);
        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        timePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
        btnFinish = (Button) rootView.findViewById(R.id.btnFinish);
        txtFinalTime = (TextView) rootView.findViewById(R.id.txtFinalTime);
        btnToCal = (Button) rootView.findViewById(R.id.btnToCal);
        btnToFrag3 = (Button) rootView.findViewById(R.id.btnToFrag3);
        layoutHide = (LinearLayout) rootView.findViewById(R.id.layoutHide);
    }

}
