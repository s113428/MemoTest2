package com.test.memotest2;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

//DB部
public class MemoDao extends DatabaseHelper{

    protected Context mContext = null;
    protected SQLiteDatabase mDB = null;

    public MemoDao(Context context){
        super(context);
        mContext = context;
    }

    //データベースオープン
    public void connection(){
        DatabaseHelper helper = new DatabaseHelper(mContext);
        mDB = helper.open();
    }

    //データベースクローズ
    public void close(){
        mDB.close();
        mDB = null;
    }

    //データを投入
    public long addMemo(String title, String content){
//        String dateNow = String.valueOf(DateFormat.format(
//                "yyyy-MM-dd kk:mm:ss", Calendar.getInstance()));
        ContentValues val = new ContentValues();
        val.put("title", title);
        val.put("content", content);
        Log.d("memo", "タイトル：" + title);
//        val.put("created", dateNow);
        return mDB.insert("memo", null, val);
    }

    //データ読み込み
    public ArrayList<HashMap<String, Object>> searchAllByWord(String word){
        ArrayList<HashMap<String, Object>> memoLists = new ArrayList<HashMap<String, Object>>();
        String[] selectArgs = new String[]{word};

        Cursor cursor = null;

        try{
            cursor = mDB.rawQuery(
                    "select * from memo where content like '%' || ? || '%' order by created desc;"
                    , selectArgs);
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                //String created = cursor.getString(cursor.getColumnIndex(("created")));

                HashMap<String, Object> memoList = new HashMap<String, Object>();
                memoList.put("id", id);
                memoList.put("content", content);
                //memoList.put("created", created);
                memoLists.add(memoList);
            }
        }
        finally{
            if (cursor != null){
                cursor.close();
            }
        }
        return memoLists;
    }

    public LinkedList<String> getTitlesFromDatabase(){
        Cursor cursor = null;
        LinkedList<String> anw = new LinkedList<String>();

        cursor = mDB.rawQuery("select * from memo;", null);

        boolean mov = cursor.moveToFirst();
        while (mov) {
            String tmp = cursor.getString(1);
            anw.add(tmp);
            mov = cursor.moveToNext();
        }
        cursor.close();

        return anw;
    }

    //データ読み込み
//    public ArrayList<HashMap<String, Object>> searchAll(){
//        ArrayList<HashMap<String, Object>> memoLists = new ArrayList<HashMap<String, Object>>();
    public String searchAll(long id){
        Cursor cursor = null;
        String content = null;
        String title = null;
        try{
            cursor = mDB.rawQuery("select * from memo;", null);

            //while(cursor.moveToNext()) {
                int count = cursor.getCount();
                Log.d("memo", ""+count);
                cursor.move((int)id+1);
                //int id = cursor.getInt(0);
                title = cursor.getString(1);
                content = cursor.getString(2);
                //String created = cursor.getString(cursor.getColumnIndex("created"));
                Log.d("memo", "内容: " + content);
                Log.d("memo", "タイトル: " + title);
//                HashMap<String, Object> memoList = new HashMap<String, Object>();
//                memoList.put("id", id);
//                memoList.put("content", content);
                //memoList.put("created", created);
                //memoLists.add(memoList);
            //}
        }
        finally{
            if( cursor != null ){
                cursor.close();
            }
        }
        return content;
    }

    // メモIDで検索
    public HashMap<String, String> searchById( String memoId ){
        String[] selectArgs = new String[]{ memoId };

        Cursor cursor = null;
        HashMap<String, String> map = new HashMap<String, String>();
        try{
            cursor = mDB.rawQuery("select * from memo WHERE _id = ?", selectArgs);

            while(cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("_id"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                //String created = cursor.getString(cursor.getColumnIndex("created"));
                map.put("id", id);
                map.put("content", content);
                //map.put("created", created);
                Log.d("memo", "内容:"+content);
            }
        }
        finally{
            if( cursor != null ){
                cursor.close();
            }
        }
        return map;
    }

    // メモを更新
    public int updateContentById( String memoId, String content ){
        String[] selectArgs = new String[]{ memoId };
        ContentValues val = new ContentValues();
        val.put("content", content);

        Log.d("update", "ID："+memoId);
        Log.d("update", "内容："+content);

        return mDB.update( "memo",
                val,
                "_id = ?",
                selectArgs);
    }

    // メモの内容を条件に削除
    public int delById( String id ){
        String[] selectArgs = new String[]{ id };
        return mDB.delete( "memo",
                "_id = ?",
                selectArgs);
    }

    //全削除
    public int delAll(){
        return mDB.delete( "memo", null, null );
    }

}
//DB部　ここまで