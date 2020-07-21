package com.example.a200721_adapterview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyCutomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Poketmon> arrayList;

    public MyCutomAdapter(Context context) {
        this.context = context;
    }

    // 전체 개수
    @Override
    public int getCount() {
        return arrayList.size();
    }

    // 뿌려져야할 객체 데이터 정보
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    // 뿌려져야할 객체 위치정보 (ArrayList)
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        // 부품 화면으로 그려져야할 화면 객체를 만든다  = setContentView
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 다시 그려야할 레이아웃이 준비되어 있지 않으면 만들고, 있으면 기존의 것을 쓴다
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_partition, null);
        }

        // 객체화된 화면에서 각 위젯의 아이디를 통해서 객체를 찾는다
        ImageView imgView = convertView.findViewById(R.id.imgView);

        // 해당된 위젯에 보여져야할 위치(ArrayList)에서 찾아서 값을 대입한다.
        Poketmon poketmon = arrayList.get(position);
        imgView.setImageResource(poketmon.getImageId());
        Glide.with(context).load(poketmon.getImageId()).into(imgView);

        return convertView;
    }

    public ArrayList<Poketmon> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Poketmon> arrayList) {
        this.arrayList = arrayList;
    }
}
