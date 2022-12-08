package com.fatihkilic.sqlliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Musicians Adlı Veri Tabanı Oluşturdu
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);

            //musicians tablosu oluşturdu
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER primary key ,name varchar,age int)");

            //musicians tablosuna veri eklendi
            database.execSQL("insert into musicians (name,age ) values ('Lars',50)");
            database.execSQL("insert into musicians (name,age ) values ('James',60)");
            database.execSQL("insert into musicians (name,age ) values ('Kirk',40)");

            //Veri Güncelleme
            database.execSQL("update musicians set age = 61 where name='James'"); // jamesin yaşını 61 olarak güncelledi

            //Veri Silme
            database.execSQL("delete from musicians where id = 2"); //id si 2 olanı siler

            //Veri okuma
            Cursor cursor = database.rawQuery("select * from musicians",null);

            //veri filtreleme name in sonu s ile biteni ekrana getirir
            Cursor cursor2 = database.rawQuery("select * from musicians where name like '½s'",null);

            //veri filtreleme id si 2 olanı ekrana getirir
            Cursor cursor3 = database.rawQuery("select * from musicians where id = 2",null);

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while(cursor.moveToNext()){
                System.out.println("Name: "+cursor.getString(nameIx));
                System.out.println("Age: "+cursor.getString(ageIx));
                System.out.println("id: "+cursor.getString(idIx));
            }
            cursor.close();

        }catch (Exception exception){
            exception.printStackTrace();
        }


    }
}