package com.test.memotest2;


//DB部

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    //データベース名
    static final String DATABASE_NAME = "memo.db";

    //データベースバージョン，
    static final int DATABASE_VERSION = 2;

    //継承している親クラスにデータベース名をバージョンをセット
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //テーブルの作成
    @Override
    public  void onCreate(SQLiteDatabase db){
        //テーブル作成
        db.execSQL(
                "create table memo ("
                    + "_id integer primary key autoincrement not null,"
                    + "content text not null,"
                    + "created text not null"
                    + ");"
        );
    }

    //データベースのバージョンアップ次の処理（createdを新しく定義）
    @Override
    public  void onUpgrde(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion == 1 && newVersion == 2){
            db.execSQL(
                    "create table memo ("
                    + "_id integer primary key autoincrement not null,"
                    + "content text not null,"
                    + "created text not null"
                    + ");"
            );
        }
    }

    public  SQLiteDatabase open(){
        return super.getWritableDatabase();
    }
    public void close(){
        super.close();
    }

}

//DB部　ここまで