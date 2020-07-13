package com.example.slidepageproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Sub01_Screen extends Fragment {
    private Chronometer chroTime;
    private Button btnTimeStart;
    private RadioGroup rgSetting;
    private RadioButton rdoDate;
    private RadioButton rdoTime;

    private CalendarView calendarView;
    private TimePicker timePicker;

    private Button btnFinish;
    private TextView txtFinalTime;
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

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.screen_sub01, container, false);

        findViewByIdFunction(rootView);
        return rootView;
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
    }
}
