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



    public static class ViewHolder{
      public final  ImageView iv;
      public final  TextView tv;

        public ViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.urgent_finger);
            tv = (TextView) view.findViewById(R.id.text_v1);


        }
    }




    public CustomViewAdapter(Context context,Cursor c, int flag){
            super( context, c, flag);



    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)   {


       View view= LayoutInflater.from(context).inflate(R.layout.textview_pretty_cool2_layout, parent, false);
 ViewHolder viewHolder = new ViewHolder(view);
     view.setTag(viewHolder);
       return view;



    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
ViewHolder viewHolder= (ViewHolder) view.getTag();

        String isCheckedMaybe = cursor.getString(cursor.getColumnIndexOrThrow(UcanContract.Tasks.COLUMN_URGENCY));
        Integer toString = Integer.parseInt(isCheckedMaybe);

        if(toString==0){

            viewHolder.iv.setVisibility(View.VISIBLE);
            String tasksText = cursor.getString(cursor.getColumnIndexOrThrow(UcanContract.Tasks.COLUMN_TASKS));
            viewHolder.tv.setText(tasksText);

        }else if (toString == 1){
            viewHolder.iv.setVisibility(View.GONE);
            String tasksText = cursor.getString(cursor.getColumnIndexOrThrow(UcanContract.Tasks.COLUMN_TASKS));
            viewHolder.tv.setText(tasksText);
        }

    }
}
