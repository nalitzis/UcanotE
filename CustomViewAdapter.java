package ivano.android.com.ucanote;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ivano.android.com.ucanote.ivano.android.com.ucanote.db.UcanContract;

/**
 * Created by ivano on 5/3/2015.
 */
public class CustomViewAdapter extends CursorAdapter {

public final int VIEW_TYPE_STARS=0;
public final int VIEWTYPE_NORMAL=1;

    public static class ViewHolder{
      public final  ImageView iv;
      public final  TextView tv;

        public ViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.urgent_finger);
            tv = (TextView) view.findViewById(R.id.text_v1);


        }
    }
    @Override
    public int getItemViewType(int position) {
        //TODO decide if you want one or two and cancel or not the //annotations in this class
return VIEWTYPE_NORMAL;
        //return(position==0)?VIEW_TYPE_STARS:VIEWTYPE_NORMAL;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }



    public CustomViewAdapter(Context context,Cursor c, int flag){
        super( context, c, flag);



    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)   {

      int viewType = getItemViewType(cursor.getPosition());

        int layoutId=-1;
          layoutId = R.layout.textview_pretty_cool2_layout;




//
//
//        if (viewType == VIEW_TYPE_STARS) {
//layoutId=R.layout.row_rating;
//        }else if (viewType==VIEWTYPE_NORMAL) {
//            layoutId = R.layout.textview_pretty_cool2_layout;
//        }
//
       View view= LayoutInflater.from(context).inflate(layoutId, parent, false);
 ViewHolder viewHolder = new ViewHolder(view);
     view.setTag(viewHolder);
       return view;



    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
ViewHolder viewHolder= (ViewHolder) view.getTag();

        String isCheckedMaybe = cursor.getString(cursor.getColumnIndexOrThrow(UcanContract.Tasks.COLUMN_URGENCY));
        Integer toString = Integer.parseInt(isCheckedMaybe);
//TODO you can maintain Integer isCheckedMaybe= cursor.getColumnIndexOr..etc just be sure the condition into the if below
        //is respected e.g. double == or single =
        if(toString==0){
           viewHolder.iv.setImageResource(R.drawable.reminder);
        }


        String tasksText = cursor.getString(cursor.getColumnIndexOrThrow(UcanContract.Tasks.COLUMN_TASKS));
viewHolder.tv.setText(tasksText);
//
//            // Find fields to populate in inflated template
//            TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
//            TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);
//            // Extract properties from cursor
//            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
//            int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));
//            // Populate fields with extracted properties
//            tvBody.setText(body);
//            tvPriority.setText(String.valueOf(priority));
    }
}
