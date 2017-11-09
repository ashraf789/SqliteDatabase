package com.example.syed.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Syed on 15/6/2016.
*/

public class MySqlite extends SQLiteOpenHelper{

    private static final String databaseName = "mydb.db";
    private static final String tableName = "studentInformation";
    private static final String column1 = "id";
    private static final String column2 = "name";
    private static final String column3 = "email";

    public MySqlite(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//------------- Creating The Database Table --------------------
        String query;
        query = "CREATE TABLE "+tableName+" ( "+column1+" INTEGER PRIMARY KEY ,"+column2+" TEXT ,"+column3+" TEXT"+" ) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF EXIST "+tableName);
        onCreate(db);
    }// ----------------------------------------------------- Table Create Finished ------------------------

    public boolean addToTable(String id,String name,String email){//---- Insert The Value on column---------------
        ContentValues contentValues =  new ContentValues();
        contentValues.put(column1,id);
        contentValues.put(column2,name);
        contentValues.put(column3,email);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long checker = sqLiteDatabase.insert(tableName,null,contentValues);
        if (checker == -1) return false;
        else return true;
    }//------------------------------------------------------Data Insertation End ---------------------------------

    public Cursor display(){//-------------------------------For Display The Database Data ------------------------
        SQLiteDatabase readSqliteDatabase = getReadableDatabase();
        Cursor cr;

        cr = readSqliteDatabase.rawQuery("SELECT * FROM "+tableName,null);
        return cr;
    }//------------------------------------------------------End The Display Data Method -------------------------

    public boolean dataUpdate(String id,String name,String email){//-------------Update The Table Data -----------
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(column1,id);
        contentValues.put(column2,name);
        contentValues.put(column3,email);
        long check = sqLiteDatabase.update(tableName,contentValues,"id = ? ",new String[] { id });
        if (check == -1) return false;
        else
            return true;
    }//------------------------------------------------------ Update Method End ----------------------------------

    public boolean delete(String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete(tableName,"id = ? ",new String[] { id });
        if (result > 0) return true;
        else return false;
    }


}
