package com.example.ex_200715_actionbar_icon;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private EditText edtNum1;
    private EditText edtNum2;
    private Button btnResult;

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

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.one_fragment, container, false);

        edtNum1 = viewGroup.findViewById(R.id.edtNum1);
        edtNum2 = viewGroup.findViewById(R.id.edtNum2);
        btnResult = viewGroup.findViewById(R.id.btnResult);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("num1", edtNum1.getText().toString());
                bundle.putString("num2", edtNum2.getText().toString());

                mainActivity.fragment2.setArguments(bundle);
                Toast.makeText(mainActivity, "전달완료", Toast.LENGTH_SHORT).show();
            }
        });

        return viewGroup;
    }
}
