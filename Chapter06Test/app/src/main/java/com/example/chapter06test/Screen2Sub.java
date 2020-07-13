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

public class Screen2Sub extends Fragment {

    private TextView txtFragment2;
    private ImageView ivPicture2;
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



    // MainAcitity에서 setContentView(R.layout.activity_main) 와 같다.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        // R.layout.subscreen1 이 객체가 되었다. (인플렉션)
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.subscreen2, container, false);

        findViewByIdFunction(rootView);

        ivPicture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeScreen(1);
                Toast.makeText(mainActivity,"1번화면변경함", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void findViewByIdFunction(ViewGroup rootView) {

        txtFragment2 = (TextView) rootView.findViewById(R.id.txtFragment2);
        ivPicture2 = (ImageView) rootView.findViewById(R.id.ivPicture2);
    }
}
