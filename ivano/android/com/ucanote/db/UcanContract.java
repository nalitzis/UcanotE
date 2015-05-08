package ivano.android.com.ucanote.ivano.android.com.ucanote.db;

import android.provider.BaseColumns;

public  class UcanContract{





public static final class  Tasks implements BaseColumns{


    public static final String COLUMN_TASKS = "task";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_URGENCY = "urgency";
    public static final String COLUMN_TAG = "tag";




    public static final String[] ALL_ROWS=new String[]{_ID, COLUMN_TASKS, COLUMN_DATE, COLUMN_URGENCY, COLUMN_TAG};
 public static final String DATABASE_TABLE="table1";
}


}