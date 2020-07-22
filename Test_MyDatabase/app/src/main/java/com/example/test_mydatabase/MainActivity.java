package com.example.test_mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName;
    private EditText edtCount;
    private Button btnInit;
    private Button btnInsert;
    private Button btnEdit;
    private Button btnRemove;
    private Button btnSelect;
    private ListView listView;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    private MyCutomAdapter myCutomAdapter;

    private int position;
    private boolean returnValue;
    ArrayList<PersonModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰객체를 연결저장하고 이벤트등록
        findViewByIdFunc();

        btnInit.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnSelect.setOnClickListener(this);


        // SQLiteOpenHelper 객체를 만든다
        myDBHelper = new MyDBHelper(getApplicationContext(), "myDB");

        // 어댑터 생성
        myCutomAdapter = new MyCutomAdapter(getApplicationContext());

        // DB셀렉트하고 list에 띄움
        selectDB();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                edtName.setText(arrayList.get(i).getName());
                edtCount.setText(String.valueOf(arrayList.get(i).getCount()));
            }
        });
    }

    private void findViewByIdFunc() {
        edtName = findViewById(R.id.edtName);
        edtCount = findViewById(R.id.edtCount);
        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnEdit = findViewById(R.id.btnEdit);
        btnRemove = findViewById(R.id.btnRemove);
        listView = findViewById(R.id.listView);
        btnSelect = findViewById(R.id.btnSelect);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 테이블을 없애고 다시 만들것을 요청한다.
            case R.id.btnInit:
                //SQLiteDatabase 객체 가져오기
                sqLiteDatabase = myDBHelper.getWritableDatabase();

                myDBHelper.onUpgrade(sqLiteDatabase, 1, 2);

                selectDB();


                //다쓰고 반납
                sqLiteDatabase.close();
                break;

            // 테이블에 이름과 나이를 insert시킨다
            case R.id.btnInsert:

                String name = edtName.getText().toString();
                int count = Integer.parseInt(edtCount.getText().toString());
                PersonModel personModel = new PersonModel(name, count);


                returnValue = myDBHelper.insertMyTBL(personModel);

                selectDB();

                if(returnValue == true){

                   Toast.makeText(getApplicationContext(), "입력 성공", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "입력 실패", Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase.close();
                break;



            // 테이블 정보 수정
            case R.id.btnEdit:
                PersonModel personModel1 = arrayList.get(position);

                personModel1.setCount(Integer.parseInt(edtCount.getText().toString()));

                returnValue = myDBHelper.editMyTBL(personModel1);

                selectDB();

                if(returnValue == true){

                    Toast.makeText(getApplicationContext(), "수정 성공", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "수정 실패", Toast.LENGTH_SHORT).show();
                }

                sqLiteDatabase.close();
                break;


            // 테이블 정보 삭제
            case R.id.btnRemove:

                PersonModel personModel2 = arrayList.get(position);
                returnValue = myDBHelper.removeMyTBL(personModel2);

                selectDB();

                if(returnValue == true){

                    Toast.makeText(getApplicationContext(), "삭제 성공", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "삭제 실패", Toast.LENGTH_SHORT).show();
                }


                sqLiteDatabase.close();
                break;


            // 테이블에서 이름과 카운트를 select 해서 가져온다
            case R.id.btnSelect:

                // 테이블 정보 셀렉트 함수
                selectDB();

                sqLiteDatabase.close();
                break;

            default:
                break;
        }
    }

    // 테이블 정보 셀렉트
    private void selectDB() {

        if(arrayList != null){
            arrayList.clear();
        }

        arrayList = myDBHelper.selectMyTBL();

//        String strNames = "그룹이름" + "\r\n" + "--------" + "\r\n";
//        String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";
//
//        for(int i = 0; i < arrayList.size(); ++i){
//            PersonModel personModel1 = arrayList.get(i);
//            strNames += personModel1.getName() + "\n";
//            strNumbers += personModel1.getCount() + "\n";
//        }

        // 화면에 다시 띄움
        listViewUpdate(arrayList);

    }

    // 화면에 어댑터 띄움
    private void listViewUpdate(ArrayList<PersonModel> arrayList) {
        // 어댑터의 리스트에 객체리스트 세팅
        myCutomAdapter.setArrayList(arrayList);

        // 리스트뷰에 어댑터 세팅
        listView.setAdapter(myCutomAdapter);
    }

}
