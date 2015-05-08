package ivano.android.com.ucanote.ivano.android.com.ucanote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ivano on 4/8/2015.
 */
public class Db {
    private static final String TAG="Db";
//TODO check if all the fields are used,
// expecially TAG, ALL KEYS and columns, and what is the function of DB info?

    //fields


    //DB info
    public static final String DATABASE_NAME = "db";

    //always remember to change this if I are going to update the DB!
    public static final int    DATABASE_VERSION=1;

    //create

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

    public void close(){
        myDBHelper.close();
    }
//
//    public long insertRow (String task,String date, String urgency, String tag) {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(UcanContract.Tasks.COLUMN_TASKS, task);
//        initialValues.put(UcanContract.Tasks.COLUMN_DATE, date);
//        initialValues.put(UcanContract.Tasks.COLUMN_URGENCY, urgency);
//        initialValues.put(UcanContract.Tasks.COLUMN_TAG, tag);
//
//    return db.insert(UcanContract.Tasks.DATABASE_TABLE,null,initialValues);
//    }
//        //delete a row from the db by _id
//        public boolean deleteRow(long rowId) {
//            String where = UcanContract.Tasks._ID + "=" + rowId;
//
//
//              //db.delete(DATABASE_TABLE, where, null) ;
//             return db.delete(UcanContract.Tasks.DATABASE_TABLE, where, null) != 0;
//        }
//
//
//
//
//    public void deleteAll(){
//        Cursor c=getallRows();
//        long rowId=c.getColumnIndex(UcanContract.Tasks._ID);
//        if (c.moveToFirst()) {
//            do {
//                deleteRow(c.getLong((int) rowId));
//
//            }
//            while (c.moveToNext());
//        }
//            c.close();
//
//    }
//    public Cursor getallRows(){
//        String where=null;
//        Cursor c=db.query(true, UcanContract.Tasks.DATABASE_TABLE, UcanContract.Tasks.ALL_ROWS,where,null,null,null,null,null);
//   if (c!= null){
//c.moveToFirst();
//    }
//      return c;
//
//
//    }
//public Cursor getRow(long rowId){
//    String where =UcanContract.Tasks._ID+"=" +rowId;
//    Cursor c=db.query(true, UcanContract.Tasks.DATABASE_TABLE, UcanContract.Tasks.ALL_ROWS,where,null,null,null,null,null);
//    if(c!= null){
//        c.moveToFirst();
//    }
//    return c;
//
//}
//    public boolean updateRow(long rowId,String task,String date, String urgency,String tag){
//        String where =UcanContract.Tasks._ID+"="+rowId;
//        ContentValues newValues =new ContentValues();
//        newValues.put(UcanContract.Tasks.COLUMN_TASKS,task);
//
//        newValues.put(UcanContract.Tasks.COLUMN_DATE, date);
//        newValues.put(UcanContract.Tasks.COLUMN_URGENCY, urgency);
//        newValues.put(UcanContract.Tasks.COLUMN_TAG, tag);
//        return db.update(UcanContract.Tasks.DATABASE_TABLE,newValues,where,null)!=0;
//
//    }

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
            Log.w(TAG,"Upgrading application's database from version"
                    +oldVersion+ " to "+newVersion+
                    " ,which will destroy all the old data!");
            db.execSQL("DROP TABLE IF EXISTS "+ UcanContract.Tasks.DATABASE_TABLE);
            onCreate(db);
        }
    }
}
