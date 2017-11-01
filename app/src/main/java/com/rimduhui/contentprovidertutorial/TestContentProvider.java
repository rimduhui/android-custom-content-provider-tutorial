package com.rimduhui.contentprovidertutorial;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import static com.rimduhui.contentprovidertutorial.TestContract.buildUriMatcher;

public class TestContentProvider extends ContentProvider {

    TestDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new TestDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        UriMatcher matcher = buildUriMatcher();
        int match = matcher.match(uri);

        Cursor retCursor = null;

        switch (match) {
            case TestContract.TESTS :
                retCursor = db.query(TestEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // UriMatcher

        long id = db.insert(TestEntry.TABLE_NAME, null, values);
        Uri returnUri = null;
        if (id > 0) {
            returnUri = uri.withAppendedPath(TestContract.BASE_PATH_URI, String.valueOf(id));
        } else {
            Toast.makeText(getContext(), "ERROR insert", Toast.LENGTH_LONG).show();
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String deleteItemId = uri.getPath();
        String mSelection = "_id=?";
        String[] mSelectionArgs = new String[]{deleteItemId};

        int returnId = db.delete(TestEntry.TABLE_NAME, mSelection, mSelectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return returnId;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
