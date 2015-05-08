package ivano.android.com.ucanote.ivano.android.com.ucanote.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;


public class UcanContentProvider extends ContentProvider {

    public Db.DatabaseHelper myDBHelper;
    public static final String AUTHORITY = "ivano.android.com.ucanote.db.UcanContentProvider";

    public static final String PATH_TASKS = "tasks";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

    private static final int TASK = 3;
    private static final int TASKDIR = 4;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        sUriMatcher.addURI(AUTHORITY, PATH_TASKS + "/#", TASK);
        sUriMatcher.addURI(AUTHORITY,PATH_TASKS,TASKDIR);
    }


    @Override
    public boolean onCreate() {

        //as inner class
        myDBHelper = new Db.DatabaseHelper(getContext());


        return false;
    }

    //
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(UcanContract.Tasks.DATABASE_TABLE);

        Cursor cursor = qb.query(myDBHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);


        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

          long id =myDBHelper.getWritableDatabase().insert(UcanContract.Tasks.DATABASE_TABLE,null , values);
      Uri returnUri= ContentUris.withAppendedId(BASE_CONTENT_URI,id);
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
                int match = sUriMatcher.match(uri);
        int count = 0;
        switch (match) {
            case TASK:
                String where = UcanContract.Tasks._ID + " = " + selection;
                count = myDBHelper.getWritableDatabase().delete(UcanContract.Tasks.DATABASE_TABLE,
                        where, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
break;
            case TASKDIR:
                count = myDBHelper.getWritableDatabase().delete(UcanContract.Tasks.DATABASE_TABLE,
                        null, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;


        }
        return count;
    }
        @Override
        public int update (Uri uri, ContentValues values, String selection, String[]selectionArgs){
            String selection2 = UcanContract.Tasks._ID + " = " + selection;
int updatedRecord=myDBHelper.getWritableDatabase().update(UcanContract.Tasks.DATABASE_TABLE,values,selection2,null);
            getContext().getContentResolver().notifyChange(uri, null);
            return updatedRecord;
        }

}

