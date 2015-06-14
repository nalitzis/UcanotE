package ivano.android.com.ucanote.ivano.android.com.ucanote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ivano on 4/8/2015.
 */
public class Db {


    public static final String DATABASE_NAME = "db";

    public static final int    DATABASE_VERSION=1;


    private static final String DATABASE_CREATE_SQL=
            "CREATE TABLE " + UcanContract.Tasks.DATABASE_TABLE
            + " ("  + UcanContract.Tasks._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UcanContract.Tasks.COLUMN_TASKS + " TEXT NOT NULL, "
            + UcanContract.Tasks.COLUMN_DATE + " TEXT, "
            + UcanContract.Tasks.COLUMN_URGENCY +" INTEGER, "
            + UcanContract.Tasks.COLUMN_TAG +" TEXT " +
                    " );";


private final Context context;
    public DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

public Db (Context ctx) {
    this.context = ctx;
    myDBHelper=new DatabaseHelper(context);
    }

    public Db open() {
    db= myDBHelper.getWritableDatabase();
        return this;
    }


    public static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super (context,DATABASE_NAME,null,DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_SQL);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+ UcanContract.Tasks.DATABASE_TABLE);
            onCreate(db);
        }
    }
}
