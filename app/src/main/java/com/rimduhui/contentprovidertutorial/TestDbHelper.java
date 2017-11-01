package com.rimduhui.contentprovidertutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDbHelper extends SQLiteOpenHelper {

    public TestDbHelper(Context context) {
        super(context, TestEntry.TABLE_NAME, null, TestEntry.TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STATEMENT = "create table " + TestEntry.TABLE_NAME + " (" +
                "_id" + " integer, " +
                TestEntry.COLUMN_INT + " integer" + ");";

        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
