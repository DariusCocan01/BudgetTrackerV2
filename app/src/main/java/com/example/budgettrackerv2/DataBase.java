package com.example.budgettrackerv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    public static final String TRACKING_TABLE = "TRACKING_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TRACKING_DAY = "TRACKING_DAY";
    public static final String COLUMN_TRACKING_MONTH = "TRACKING_MONTH";
    public static final String COLUMN_TRACKING_YEAR = "TRACKING_YEAR";
    public static final String COLUMN_TRACKING_EXPENSE = "TRACKING_EXPENSE";
    public static final String COLUMN_TRACKING_CATEGORY = "TRACKING_CATEGORY";

    public DataBase(@Nullable Context context) {
        super(context, "tracking.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + TRACKING_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TRACKING_DAY + " INT, " + COLUMN_TRACKING_MONTH + " INT, " + COLUMN_TRACKING_YEAR + " INT, " + COLUMN_TRACKING_EXPENSE + " INT, " + COLUMN_TRACKING_CATEGORY + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(MyData myData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TRACKING_DAY,myData.getDay());
        cv.put(COLUMN_TRACKING_MONTH, myData.getMonth());
        cv.put(COLUMN_TRACKING_YEAR, myData.getYear());
        cv.put(COLUMN_TRACKING_EXPENSE,myData.getExpense());
        cv.put(COLUMN_TRACKING_CATEGORY,myData.getCategory());

        long insert = db.insert(TRACKING_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public List<MyData> getAll(){
        List<MyData> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TRACKING_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do{
                int day = cursor.getInt(1);
                int month = cursor.getInt(2);
                int year = cursor.getInt(3);
                int expense = cursor.getInt(4);
                String category = cursor.getString(5);

                MyData myData = new MyData(day,month,year,expense,category);
                returnList.add(myData);

            }while(cursor.moveToNext());

        }else {

        }
        cursor.close();
        db.close();
        return returnList;
    }
    public List<MyData> getSpendingsFromMonth(int i){
        List<MyData> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TRACKING_TABLE + " WHERE " + COLUMN_TRACKING_MONTH + " = " + i ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do{
                int day = cursor.getInt(1);
                int month = cursor.getInt(2);
                int year = cursor.getInt(3);
                int expense = cursor.getInt(4);
                String category = cursor.getString(5);

                MyData myData = new MyData(day,month,year,expense,category);
                returnList.add(myData);

            }while(cursor.moveToNext());

        }else {

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
