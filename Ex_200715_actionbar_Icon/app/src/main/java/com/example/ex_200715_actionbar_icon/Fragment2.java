package com.example.ex_200715_actionbar_icon;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private TextView txtResult;
    private MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
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

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.two_fragment, container, false);

        txtResult = viewGroup.findViewById(R.id.txtResult);

        if(mainActivity.fragment2.getArguments() != null){
            int num1 = Integer.parseInt(mainActivity.fragment2.getArguments().getString("num1"));
            int num2 = Integer.parseInt(mainActivity.fragment2.getArguments().getString("num2"));
            int result = num1 + num2;
            txtResult.setText(result +"");
            mainActivity.fragment2.setArguments(null);
        }else {
            Toast.makeText(mainActivity, "받아온값 없음", Toast.LENGTH_SHORT).show();
        }

        return viewGroup;
    }
}
