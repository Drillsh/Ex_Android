package com.example.chapter06test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Screen1Sub extends Fragment {

    private TextView txtFragment1;
    private ImageView ivPicture1;
    private MainActivity mainActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // MainAcitivy 객체를 가져온다.
        mainActivity = (MainActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    // MainAcitity에서 setContentView(R.layout.activity_main) 와 같다.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        // R.layout.subscreen1 이 객체가 되었다. (인플렉션)
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.subscreen1, container, false);

        findViewByIdFunction(rootView);

        ivPicture1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeScreen(2);
                Toast.makeText(mainActivity, "화면전환됨", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void findViewByIdFunction(ViewGroup rootView) {

        txtFragment1 = (TextView) rootView.findViewById(R.id.txtFragment1);
        ivPicture1 = (ImageView) rootView.findViewById(R.id.ivPicture1);
    }
}
