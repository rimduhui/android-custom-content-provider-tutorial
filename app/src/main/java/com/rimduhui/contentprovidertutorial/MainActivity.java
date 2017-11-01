package com.rimduhui.contentprovidertutorial;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mInput;
    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInput = (EditText) findViewById(R.id.input);
        mResult = (TextView) findViewById(R.id.result);
    }

    public void insert(View v) {
        ContentValues values = new ContentValues();
        values.put(TestEntry.COLUMN_INT, Integer.valueOf(mInput.getText().toString()));
        Uri result = getContentResolver().insert(TestContract.BASE_PATH_URI, values);
        mResult.setText(result.toString());
    }

    public void query(View v) {
        Cursor cursor = getContentResolver().query(TestContract.BASE_PATH_URI, null, null, null, null);
        cursor.moveToFirst();
        mResult.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(TestEntry.COLUMN_INT))));
    }

    public void delete(View v) {

    }
}
