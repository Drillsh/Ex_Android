package com.example.mp3_project_lsh;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;

import java.util.ArrayList;

public class MusicDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "musicDB";
    private static final int VERSION = 1;

    private Context context;
    private SQLiteDatabase sqLiteDatabase;



    public MusicDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    // 테이블 생성
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "CREATE TABLE musicTBL(" +
                        "id VARCHAR(15) PRIMARY KEY," +
                        "artist VARCHAR(15)," +
                        "title VARCHAR(15)," +
                        "albumArt VARCHAR(15)," +
                        "duration VARCHAR(15)," +
                        "click INTEGER," +
                        "liked INTEGER );");
    }

    // 테이블을 삭제한다.
    // i는 old ver i1은 new ver. 버전이 바뀔때 호출된다.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists musicTBL");
        onCreate(sqLiteDatabase);
    }

    // DB Select
    public ArrayList<MusicData> selectMusicTbl() {

        ArrayList<MusicData> musicDBArrayList = new ArrayList<>();

        sqLiteDatabase = this.getWritableDatabase();

        // 쿼리문 입력하고 커서 리턴 받음
        Cursor cursor = sqLiteDatabase.rawQuery("select * from musicTBL;", null);

        while (cursor.moveToNext()) {
            MusicData musicData = new MusicData(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6));

            musicDBArrayList.add(musicData);
        }

        cursor.close();
        sqLiteDatabase.close();

        return musicDBArrayList;
    }

    // DB 삽입
//    public boolean insertMusicDataToDB() {
//
//        boolean returnValue = false;
///sqLiteDatabase = this.getWritableDatabase();
//
//        String query = "insert into musicTBL values("
//                + "'" + id + "',"
//                + "'" + artist + "',"
//                + "'" + title + "',"
//                + "'" + albumArt + "',"
//                + "'" + duration + "',"
//                + 0 + "," + 0 + ");";
//
//        // 쿼리문 작성해서 넘김
//        // 예외발생시 SQLException
//        sqLiteDatabase.execSQL(query);
//
//        return returnValue;
//    }
    // sdCard 안의 음악을 검색한다
    public ArrayList<MusicData> findMusic() {
        ArrayList<MusicData> sdCardList = new ArrayList<>();

        String[] data = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION};

        String selection = MediaStore.Audio.Media.DATA + " like ? ";

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                data, selection, new String[]{"%MusicList%"}, data[2] + " ASC");

        if (cursor != null) {
            while (cursor.moveToNext()) {

                // 음악 데이터 가져오기
                String id = cursor.getString(cursor.getColumnIndex(data[0]));
                String artist = cursor.getString(cursor.getColumnIndex(data[1]));
                String title = cursor.getString(cursor.getColumnIndex(data[2]));
                String albumArt = cursor.getString(cursor.getColumnIndex(data[3]));
                String duration = cursor.getString(cursor.getColumnIndex(data[4]));

                MusicData mData = new MusicData(id, artist, title, albumArt, duration, 0, 0);

                sdCardList.add(mData);
            }
        }

        return sdCardList;
    }

    // sdcard에서 검색한 음악과 DB를 비교해서 중복되지 않은 플레이리스트를 리턴
    public ArrayList<MusicData> compareArrayList(){
        ArrayList<MusicData> sdCardList = findMusic();
        ArrayList<MusicData> dbList = selectMusicTbl();

        // DB가 비었다면 sdcard리스트 리턴
        if(dbList.isEmpty()){
            return sdCardList;
        }

        // DB가 이미 sdcard 정보를 가지고 있다면 DB리스트를 리턴
        if(dbList.containsAll(sdCardList)){
            return dbList;
        }

        // 두 리스트를 비교후 중복되지 않은 값을 DB리스트에 추가후 리턴
        for(MusicData mData : sdCardList){
            for(MusicData mData2 : dbList){
                if(mData.getId() == mData2.getId()){
                    continue;
                }
                dbList.add(mData);
            }
        }
        return dbList;
    }
}