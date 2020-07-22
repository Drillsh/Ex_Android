package com.example.test_mydatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public MyDBHelper (Context context, String dbName){
        // 나의 데이터베이스를 생성. 기존 내용있으면 만들지 않음
        super(context, dbName, null, 1);
        this.context = context;
    }

    // 테이블을 생성한다.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table myTBL(name char(20) primary key, number integer);");
    }

    // 테이블을 삭제한다.
    // i는 old ver i1은 new ver
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists myTBL");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<PersonModel> selectMyTBL(){

        ArrayList<PersonModel> personArrayList = new ArrayList<>();

        sqLiteDatabase = this.getWritableDatabase();
        // 쿼리문 입력하고 커서 리턴받음
        Cursor cursor = sqLiteDatabase.rawQuery("select * from myTBL;", null);

        while (cursor.moveToNext()) {
            PersonModel personModel = new PersonModel(cursor.getString(0), cursor.getInt(1));
            personArrayList.add(personModel);
        }

        cursor.close();
        sqLiteDatabase.close();

        return personArrayList;
    }

    public boolean insertMyTBL (PersonModel personModel){

        boolean returnValue = false;

        try {
            // SQLiteDatabase 객체
            sqLiteDatabase = this.getWritableDatabase();

            String name = personModel.getName();
            int count = personModel.getCount();

            String queryStr = "insert into myTBL values('" + name + "'," + count + ");";

            // 쿼리문 작성해서 넘김
            // 예외발생시 SQLException
            sqLiteDatabase.execSQL(queryStr);

            returnValue = true;
        } catch (SQLException e) {
            Log.d("database" , e.getMessage());
            returnValue = false;

        }finally {
            sqLiteDatabase.close();
        }

        return returnValue;
    }

    public boolean editMyTBL(PersonModel personModel){
        boolean returnValue = false;

        try {
            // SQLiteDatabase 객체
            sqLiteDatabase = this.getWritableDatabase();

            String name = personModel.getName();
            int count = personModel.getCount();

            String queryStr = "update myTBL set number =" + count + " where name ='"+ name + "';";

            // 쿼리문 작성해서 넘김
            // 예외발생시 SQLException
            sqLiteDatabase.execSQL(queryStr);

            returnValue = true;

        } catch (SQLException e) {
            Log.d("database" , e.getMessage());
            returnValue = false;

        }finally {
            sqLiteDatabase.close();
        }

        return returnValue;
    }


    public boolean removeMyTBL(PersonModel personModel){

        boolean returnValue = false;

        try {
            // SQLiteDatabase 객체
            sqLiteDatabase = this.getWritableDatabase();

            String name = personModel.getName();

            String queryStr = "delete from myTBL where name ='"+ name +"';";

            // 쿼리문 작성해서 넘김
            // 예외발생시 SQLException
            sqLiteDatabase.execSQL(queryStr);

            returnValue = true;

        } catch (SQLException e) {
            Log.d("database" , e.getMessage());
            returnValue = false;

        }finally {
            sqLiteDatabase.close();
        }

        return returnValue;
    }
}
