package com.example.troyphattrinh.fitness_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    /*Version of the current database. As we make changes to the database itself such as new column, the version is incremented*/
    private static final int DATABASE_VERSION = 1;

    /*Name of database of the whole project*/
    private static final String DATABASE_NAME = "FitnessApp.db";


    /*Create Names for each activities that uses database*/
    private static final String TABLE_USER = "User";
    private static final String TABLE_HEARTRATE = "HeartRate";
    private static final String TABLE_STEPS = "Steps";

    /*Create column for each table*/
    /*User Table*/
    private static final String COLUMN_USER_NAME = "User_name";
    private static final String COLUMN_USER_PASS = "User_pass";
    private static final String COLUMN_USER_DOB = "User_dob";
    private static final String COLUMN_USER_EMAIL = "User_email";
    /*Heartrate Table*/
    private static final String COLUMN_USER_HEARTRATE = "User_HeartRate";
    /*Steps Table*/
    private static final String COLUMN_USER_STEPS= "User_Steps";

    /*Drop function will completely delete a table*/
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_HR_TABLE = "DROP TABLE IF EXISTS " + TABLE_HEARTRATE;
    private String DROP_STEP_TABLE = "DROP TABLE IF EXISTS " + TABLE_STEPS;

    
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*When the databasehelper is called it will create 3 tables correspoding to each activities in the app*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String UTable = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_PASS + " TEXT,"
                + COLUMN_USER_DOB + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT" + ")";

        String HRTable = "CREATE TABLE " + TABLE_HEARTRATE + "("
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_HEARTRATE + " TEXT)";

        String StepsTable = "CREATE TABLE " + TABLE_STEPS + "("
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_STEPS + " TEXT" + ")";


        /*Execute each table after created*/
        db.execSQL(UTable);
        db.execSQL(HRTable);
        db.execSQL(StepsTable);
    }

    /*When a version is changed, the database will delete all data from previous version through DROP_TABLE.*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_HR_TABLE);
        db.execSQL(DROP_STEP_TABLE);
        onCreate(db);
    }


    /*Add users from registration to User table*/
    public boolean addUser (String name, String pass, String dob, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_PASS, pass);
        values.put(COLUMN_USER_DOB, dob);
        values.put(COLUMN_USER_EMAIL, email);

        long success = db.insert(TABLE_USER, null, values);
     /*Condition for the funtion to add data into the dtb*/
        if(success == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /*Cursor will take in the the sql command (query) to return the fields from table User */
    public Cursor getUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT User_name, User_dob, User_email FROM User WHERE User_email = " + "'" + email + "'"  , null);
        return data;
    }


/*Add heart rate record to HRTable with corresponding email address*/
    public boolean addHRate (int HRate, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_HEARTRATE, HRate);

        long success = db.insert(TABLE_HEARTRATE, null, values);
    /*Condition for the funtion to add data into the dtb*/
        if(success == -1){
            return false;
        }
        else{
            return true;
        }
    }

    /*Cursor will take in the the sql command (query) to return the fields from table HeartRate*/
    public Cursor getHRate (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT User_email, User_HeartRate FROM HeartRate WHERE Hr_email = " + "'" + email + "'"  , null);
        return data;
    }


/*Add Steps count to StepsTable with corresponding email address*/
    public boolean addSteps (int steps, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_STEPS, steps);

        long success = db.insert(TABLE_STEPS, null, values);
     /*Condition for the funtion to add data into the dtb*/
        if(success == -1){
            return false;
        }
        else{
            return true;
        }
    }
    /*Cursor will take in the the sql command (query) to return the fields from table Steps*/
    public Cursor getSteps (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT User_email, User_Steps FROM Steps WHERE User_email = " + "'" + email + "'"  , null);
        return data;
    }
}
