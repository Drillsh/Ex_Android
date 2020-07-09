package com.example.a200709_ex;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    private Button btnToFrag1;
    private Button btnToFrag2;

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


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.flagment_3, container, false);

        findViewByIdFunction(rootView);

        btnToFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeFragment(mainActivity.FRGMENT_1);
            }
        });

        btnToFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeFragment(mainActivity.FRGMENT_2);
            }
        });

        return rootView;
    }

    private void findViewByIdFunction(ViewGroup rootView) {
        btnToFrag1 = (Button) rootView.findViewById(R.id.btnToFrag1);
        btnToFrag2 = (Button) rootView.findViewById(R.id.btnToFrag2);
    }
}
