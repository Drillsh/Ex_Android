package com.example.test_mydatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCutomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PersonModel> arrayList;

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
            convertView = layoutInflater.inflate(R.layout.list_partition, null);
        }

        // 객체화된 화면에서 각 위젯의 아이디를 통해서 객체를 찾는다
        TextView txtViewName = convertView.findViewById(R.id.txtViewName);
        TextView txtViewCount = convertView.findViewById(R.id.txtViewCount);

        // 해당된 위젯에 보여져야할 위치(ArrayList)에서 찾아서 값을 대입한다.
        PersonModel personModel = arrayList.get(position);
        txtViewName.setText(arrayList.get(position).getName());
        txtViewCount.setText(String.valueOf(arrayList.get(position).getCount()));

        return convertView;
    }

    public ArrayList<PersonModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<PersonModel> arrayList) {
        this.arrayList = arrayList;
    }
}
