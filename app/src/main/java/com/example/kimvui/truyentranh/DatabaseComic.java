package com.example.kimvui.truyentranh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.List;

public class DatabaseComic extends SQLiteOpenHelper {
    public DatabaseComic(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "DBComics", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_COMIC ="CREATE TABLE tb_comics(id int primary key,name text,image text,catogery text, chapter text)";
        db.execSQL(CREATE_TABLE_COMIC);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_comics");
        onCreate(db);
    }
    public void insertContacts(Comic comics){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",comics.Name);
        values.put("image",comics.Image);
        values.put("catogery",comics.Catogery);
        values.put("chapter", String.valueOf(comics.Chapters));
        db.insertOrThrow("tb_contacts",null,values);
        db.close();
    }
    public List<Comic> getAllContacts(){
        List<Comic> contactsList=new ArrayList<>();
        String SELECTALL= "select * from tb_comics";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(SELECTALL,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(1);
                String image=cursor.getString(2);
                String catogery=cursor.getString(3);
                String chapter=cursor.getString(4);

                //contactsList.add(new Comic(name,image,catogery,chapter));
            }
            while (cursor.moveToNext());
        }
        return contactsList;
    }
//    public Comic getContactByID(int id){
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.query("tb_contacts",new String[]{"id","fullname","phonenumber"},"id=?",
//                new String[]{String.valueOf(id)},null,null,null,null);
//        if(cursor!=null){
//            cursor.moveToFirst();
//        }
//        String name=cursor.getString(1);
//        String phone=cursor.getString(2);
//        //Comic contacts=new Comic(name,phone);
//        return contacts;
//    }
}
