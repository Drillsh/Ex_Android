package com.example.a200721_spinneradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> arrayList;

    public MyCustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // 화면객체 만든다.
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 컬링
        if (view == null){
            view = layoutInflater.inflate(R.layout.spinner_partition, null);
        }

        // 아이디통해 객체 찾기
        ImageView imageView = view.findViewById(R.id.imgView);
        TextView txtName = view.findViewById(R.id.txtName);

        // 위치 찾아 대입
        Movie movie = arrayList.get(position);
        imageView.setImageResource(movie.getImgId());
        txtName.setText(movie.getTitle());

        return view;
    }

    public ArrayList<Movie> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Movie> arrayList) {
        this.arrayList = arrayList;
    }
}
