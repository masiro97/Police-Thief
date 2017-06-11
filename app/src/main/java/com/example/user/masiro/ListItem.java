package com.example.user.masiro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class ListItem extends SQLiteOpenHelper {

    public ListItem(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table if not exists ItemLog (" +
                "id integer primary key autoincrement," +
                "GeoPoint text not null," +
                "point integer not null);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "drop table if not exists ItemLog";
            onCreate(db);
    }


    public void insert(String GeoPoint, int point){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into ItemLog values(null,'" + GeoPoint + "'," + point +");");
        db.close();

    }

    public void delete(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from ItemLog where id =" + id + ";");
    }

    public String getResult(){

        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("select * from ItemLog",null);
        while(cursor.moveToNext()){
            result += cursor.getString(0) + ":" + cursor.getString(1) + "," +cursor.getString(2) + "\n";
        }

        return result;
    }


}
