package com.example.troyphattrinh.fitness_app.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "USERReg.db";

    private static final String TABLE_USER = "User";

    /*Create column*/
    private static final String COLUMN_USER_NAME = "User_name";
    private static final String COLUMN_USER_PASS = "User_pass";
    private static final String COLUMN_USER_DOB = "User_dob";
    private static final String COLUMN_USER_EMAIL = "User_email";

    /*Create Table Query*/
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_PASS + " TEXT,"
            + COLUMN_USER_DOB + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT" + ")";

    /*Drop always follows up with the Create Query*/
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public boolean addUser (String name, String pass, String dob, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_PASS, pass);
        values.put(COLUMN_USER_DOB, dob);
        values.put(COLUMN_USER_EMAIL, email);

        long success = db.insert(TABLE_USER, null, values);

        if(success == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
