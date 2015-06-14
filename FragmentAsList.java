
package ivano.android.com.ucanote;


import android.app.AlarmManager;
import android.app.Fragment;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

import ivano.android.com.ucanote.ivano.android.com.ucanote.db.Db;
import ivano.android.com.ucanote.ivano.android.com.ucanote.db.UcanContentProvider;
import ivano.android.com.ucanote.ivano.android.com.ucanote.db.UcanContract;

/**
 * Created by ivano on 3/25/2015.
 */

public class FragmentAsList extends Fragment implements  View.OnCreateContextMenuListener,LoaderManager.LoaderCallbacks<Cursor> {



     public interface Callback {
         /**
          * DetailFragmentCallback for when an item has been selected.
          */
         public void onItemSelected(Uri contentUri);
     }


static public long mId;
static public Integer numbersUrgent;
static public String description;
static public Integer id;




    ListView listView;
    CursorLoader cl;

    Db myDb;
    CustomViewAdapter cVA;
    Intent mShareIntent;

    AdapterView.AdapterContextMenuInfo info;

   private ShareActionProvider mShareActionProvider;

    public FragmentAsList() {
        super();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        SharedPreferences settings = getActivity().getSharedPreferences("prova", getActivity().MODE_PRIVATE);

        int numberUrg = settings.getInt("variable", -1);

      if(numbersUrgent==null) {
            numbersUrgent = numberUrg;

        }

//





        getLoaderManager().initLoader(4, null, (LoaderManager.LoaderCallbacks<Cursor>)this);






        myDb = new Db(getActivity());
        myDb.open();


    }





    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragmentaslist, menu);


        MenuItem item = menu.findItem(R.id.share);



     mShareActionProvider = new ShareActionProvider(getActivity());
        MenuItemCompat.setActionProvider(item, mShareActionProvider);
 }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.add:

                Intent intent_comunicate = new Intent(getActivity(), AddTask.class);
                intent_comunicate.putExtra(getActivity().getPackageName(), "CIao Cipollino");
                startActivityForResult(intent_comunicate, 123);

                break;

            case R.id.share:
                mShareIntent = new Intent();
                mShareIntent.setAction(Intent.ACTION_SEND);
                mShareIntent.setType("text/plain");

                mShareIntent.putExtra(Intent.EXTRA_TEXT, "Download this app from google play, it is pretty cool!");

                if(mShareActionProvider!=null){
mShareActionProvider.setShareIntent(mShareIntent);
                }
                break;

            case R.id.delete_all:
                registerForContextMenu(listView);
                getActivity().getContentResolver().delete(UcanContentProvider.CONTENT_URI,null,null);

                getLoaderManager().initLoader(2, null, (LoaderManager.LoaderCallbacks<Cursor>)this);
            case R.id.favorites:

getLoaderManager().restartLoader(1, null, (LoaderManager.LoaderCallbacks<Cursor>)this);


                break;


            case R.id.undo_reset:
                getLoaderManager().restartLoader(4, null, (LoaderManager.LoaderCallbacks<Cursor>)this);

                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            String string = data.getExtras().getString(getActivity().getPackageName());
            String timeCurrent1 =data.getExtras().getString("tempo");
            Integer IsCheckedMaybe = data.getExtras().getInt("YesOrNot");




            registerForContextMenu(listView);

            ContentValues values = new ContentValues();
            values.put(UcanContract.Tasks.COLUMN_TASKS, string);

            values.put(UcanContract.Tasks.COLUMN_DATE,timeCurrent1);



            values.put(UcanContract.Tasks.COLUMN_URGENCY, IsCheckedMaybe);

            getActivity().getContentResolver().insert(UcanContentProvider.BASE_CONTENT_URI, values);
            getLoaderManager().initLoader(4, null, (LoaderManager.LoaderCallbacks<Cursor>)this);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View hiddenList = inflater.inflate(R.layout.fragment_as_list_layout, container, false);

        listView = (ListView) hiddenList.findViewById(R.id.list_hidden);




        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO make a new activity where you have a causal motivational thing etc
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                Uri uri = Uri.parse(UcanContentProvider.CONTENT_URI + "/"
                        + id);

              mId=id;
                if (cursor != null) {


                    ((Callback) getActivity())
                            .onItemSelected(uri);


                }
            }
        });



        return hiddenList;

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mi = getActivity().getMenuInflater();


        mi.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        registerForContextMenu(listView);
         info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;


        Uri uri = Uri.parse(UcanContentProvider.CONTENT_URI + "/"
                + info.id);
        String where = String.valueOf(info.id);

        switch (item.getItemId()) {

            case R.id.delete:

                getActivity().getContentResolver().delete(uri, where, null);
                getLoaderManager().initLoader(2, null, (LoaderManager.LoaderCallbacks<Cursor>) this);
                break;




            case R.id.rememberIn5:

                //get the cursor..
                Cursor cursor = cVA.getCursor();
                cursor.moveToPosition(index);
                description= cursor.getString(1);
                AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent i = new Intent(getActivity(), IntentServiceClass.class);
                PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 0, i, 0);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 8);
                calendar.set(Calendar.MINUTE, 0);

                calendar.set(Calendar.SECOND, 0);
                mgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+24*60*60*1000, 24*60*60*1000 , pendingIntent);
                Toast.makeText(getActivity(), "SEE YOU TOMORROW!",Toast.LENGTH_LONG ).show();

                break;


        }

        return super.onContextItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if (id==4) {
            cl = new CursorLoader(getActivity(), UcanContentProvider.CONTENT_URI, null, null, null, null);


        }else if(id==1 || id==2){

            String selection= UcanContract.Tasks.COLUMN_URGENCY+"=0";
            cl = new CursorLoader(getActivity(), UcanContentProvider.CONTENT_URI, null, selection, null, null);


        }else if(id==3){







            String[] projection = {UcanContract.Tasks.COLUMN_TASKS};
            Uri uri = Uri.parse(UcanContentProvider.CONTENT_URI + "/"
                    + info.id);

            cl= new CursorLoader(getActivity(),uri,projection,null, null,null);




        }

     return cl;
 }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences settings = getActivity().getSharedPreferences("prova", getActivity().MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("variable", numbersUrgent);
        editor.commit();


//        try {
//            getActivity().unregisterReceiver(receiver);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == 1) {
            numbersUrgent = data.getCount();
            cVA = new CustomViewAdapter(getActivity(), data, 0);
            listView.setAdapter(cVA);
        } else if (loader.getId() == 2) {
            numbersUrgent = data.getCount();
            SharedPreferences settings = getActivity().getSharedPreferences("prova", getActivity().MODE_PRIVATE);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("variable", numbersUrgent);
            editor.commit();
            cVA = new CustomViewAdapter(getActivity(), data, 0);
            listView.setAdapter(cVA);
        } else if (loader.getId() == 3) {

           if (data != null && data.moveToFirst()) {
                description = data.getString(0);
                Toast.makeText(getActivity(), "description is:" + description, Toast.LENGTH_LONG).show();
                getLoaderManager().initLoader(4, null, (LoaderManager.LoaderCallbacks<Cursor>) this);

           }
        }else if(loader.getId()==4){
                numbersUrgent = data.getCount();
                cVA = new CustomViewAdapter(getActivity(), data, 0);
                listView.setAdapter(cVA);
            }




        }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
  cVA.swapCursor(null);
    }

}


