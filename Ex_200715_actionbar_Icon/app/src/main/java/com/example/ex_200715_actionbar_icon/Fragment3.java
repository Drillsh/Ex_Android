package com.example.ex_200715_actionbar_icon;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.File;

public class Fragment3 extends Fragment implements View.OnClickListener {

    private Button btnPrev;
    private Button btnNext;
    private MyImageView myImageView;
    private TextView txtIndex;

    private int index = 0;

    private MainActivity mainActivity;
    private File[] imageFileArray = new File[5];

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

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.three_fragment, container, false);

        btnPrev = viewGroup.findViewById(R.id.btnPrev);
        btnNext = viewGroup.findViewById(R.id.btnNext);
        myImageView = viewGroup.findViewById(R.id.myImageView);
        txtIndex = viewGroup.findViewById(R.id.txtIndex);

        ActivityCompat.requestPermissions(mainActivity, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        //외장하드의 이미지를 가져온다
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        imageFileArray = new File(path + "/image").listFiles();
        myImageView.setImagePath(imageFileArray[index].toString());

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPrev:
                if (index == 0) index = imageFileArray.length;
                index -= 1;
                txtIndex.setText((index+1) +" / 5");
                myImageView.setImagePath(imageFileArray[index].toString());
                myImageView.invalidate();
                break;
            case R.id.btnNext:
                if (index == imageFileArray.length - 1) index = -1;
                index += 1;
                txtIndex.setText((index+1) +" / 5");
                myImageView.setImagePath(imageFileArray[index].toString());
                myImageView.invalidate();
                break;
            default:
                break;
        }
    }
}
