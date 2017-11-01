package com.rimduhui.contentprovidertutorial;

import android.content.UriMatcher;
import android.net.Uri;

public class TestContract {
    public static final String AUTHORITY = "com.rimduhui.contentprovidertutorial";
    public static final String ID = "#";
    public static final String BASE_PATHS = "test";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri BASE_PATH_URI = BASE_URI.buildUpon().appendPath(BASE_PATHS).build();

    public static final int TESTS = 100;
    public static final int TEST_BY_ID = 101;
    public static final UriMatcher TestUriMatcher = buildUriMatcher();

    private static Uri buildIdPath() {
        return BASE_PATH_URI.buildUpon().appendPath(ID).build();
    }

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, BASE_PATHS, TESTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATHS + "/" + ID, TEST_BY_ID);
        return uriMatcher;
    }
}
