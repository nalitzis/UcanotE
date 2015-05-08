
package ivano.android.com.ucanote;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ivano.android.com.ucanote.ivano.android.com.ucanote.db.Db;
import ivano.android.com.ucanote.ivano.android.com.ucanote.db.UcanContentProvider;
import ivano.android.com.ucanote.ivano.android.com.ucanote.db.UcanContract;

/**
 * Created by ivano on 3/25/2015.
 */

public class FragmentAsList extends Fragment implements  View.OnCreateContextMenuListener,LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter myCursorAdapter;
    //brought out from OnCreateView, have to be in all the class
    List<String> tasks = new ArrayList<String>();
    ListView listView;
    CursorLoader cl;
    Time today = new Time(Time.getCurrentTimezone());
    Db myDb;
    EditText etTasks;
    CustomViewAdapter cVA;

    public FragmentAsList() {
        super();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
// Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
        getLoaderManager().initLoader(0, null, (LoaderManager.LoaderCallbacks<Cursor>)this);




//open Database

        myDb = new Db(getActivity());
        myDb.open();


    }


    public void populateView() {

//TODO FIRST clean the class and see if you have to get rid of the query in populate view()




//        String[] fromFieldNames = new String[]{UcanContract.Tasks._ID, UcanContract.Tasks.COLUMN_TASKS};
//        int[] toViewId = new int[]{R.id.text_v1, R.id.text_v2};
//        myCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.textview_pretty_cool2_layout, null, fromFieldNames, toViewId, 0);
//        listView.setAdapter(myCursorAdapter);

        //before to implement the loader
//        String[] projection ={UcanContract.Tasks._ID,UcanContract.Tasks.COLUMN_TASKS};
//        Cursor cursor=getActivity().getContentResolver().query(UcanContentProvider.CONTENT_URI,projection,null,null,null);
//



//TODO first  before to implement this see if you can put inside loader

        String[] fromFieldNames = new String[]{UcanContract.Tasks.COLUMN_URGENCY,UcanContract.Tasks.COLUMN_TASKS};
    int[] toViewId = new int[]{R.id.urgent_finger,R.id.text_v1};

   // myCursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.row_rating,null,fromFieldNames,toViewId,0);

//   myCursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.textview_pretty_cool2_layout,null,fromFieldNames,toViewId,0);
//listView.setAdapter(myCursorAdapter);


//        CustomViewAdapter cVA = new CustomViewAdapter(getActivity(),cursore,0);
//        listView.setAdapter(cVA);

    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragmentaslist, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.add:

                // http://developer.android.com/guide/topics/providers/content-provider-creating.html
                // and pass data between activities, then if there is time check also
//
                Intent intent_comunicate = new Intent(getActivity(), DetailNote.class);
                //should i get rid of CIpilollino? probably you need to put an Uri there
                intent_comunicate.putExtra(getActivity().getPackageName(), "CIao Cipollino");
                startActivityForResult(intent_comunicate, 123);

                break;

            case R.id.delete_all:
//TODO do we need these lines:
                registerForContextMenu(listView);
                getActivity().getContentResolver().delete(UcanContentProvider.CONTENT_URI,null,null);

                //database query it is not redundant considering everything has been cancelled ?
                populateView();
//            Cursor cursor = myDb.getallRows();
//            String[] fromFieldNames = new String[]{UcanContract.Tasks.COLUMN_TASKS};
//            int[] toViewId = new int[]{R.id.text_view};
//
//
//            SimpleCursorAdapter myCursorAdapter;
//            myCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.textview_pretty_cool, cursor, fromFieldNames, toViewId, 0);
//            listView.setAdapter(myCursorAdapter);
            case R.id.favorites:

//TODO ask is this efficient?
                //TODO first http://stackoverflow.com/questions/21253332/will-loadermanager-restartloader-always-result-in-a-call-to-oncreateloader

 getLoaderManager().restartLoader(1, null, (LoaderManager.LoaderCallbacks<Cursor>)this);
              //  getActivity().getContentResolver().query(UcanContentProvider.CONTENT_URI,null,where,null,null);

//

                break;


            case R.id.undo_reset:
                getLoaderManager().restartLoader(0, null, (LoaderManager.LoaderCallbacks<Cursor>)this);
                populateView();

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



            Log.d("FragmentAsList", "stringa dall altra activity: " +string);
            Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
            ///database insert
            //today.setToNow();
            //String timeCurrent = today.format("%Y-%m-%d %H:%M:%S");
            Toast.makeText(getActivity(), timeCurrent1, Toast.LENGTH_LONG).show();



            registerForContextMenu(listView);
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//            int index = info.position;
//            Uri uri = Uri.parse(UcanContentProvider.CONTENT_URI + "/"
//                    + info.id);
//            String where =String.valueOf(info.id);


            ContentValues values = new ContentValues();
            values.put(UcanContract.Tasks.COLUMN_TASKS, string);

            values.put(UcanContract.Tasks.COLUMN_DATE,timeCurrent1);



            values.put(UcanContract.Tasks.COLUMN_URGENCY, IsCheckedMaybe);


            getActivity().getContentResolver().insert(UcanContentProvider.BASE_CONTENT_URI, values);


            // myDb.insertRow(string, timeCurrent, null, null);
            //database query
            populateView();


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View hiddenList = inflater.inflate(R.layout.fragment_as_list_layout, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        listView = (ListView) hiddenList.findViewById(R.id.list_hidden);

        //NOTA ora funziona, devi fare registerForContext mettendo la View di cui hai bisogno e poi chiamare il menu al tocco!



        //We are inside a fragment, we have to speak with the compiler !
        registerForContextMenu(listView);

populateView();
//
 //    getLoaderManager().initLoader(0, null, this);
//
//        String[] fromFieldNames = new String[]{UcanContract.Tasks._ID, UcanContract.Tasks.COLUMN_TASKS};
//        int[] toViewId = new int[]{R.id.text_v1, R.id.text_v2};
//        myCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.textview_pretty_cool2_layout, null, fromFieldNames, toViewId, 0);

        //listView.setAdapter(myCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ivano.android.com.ucanote.FragmentAsList", "onItemClick (line 115): tocca posizione: " + position);
                //TODO make a new activity where you have a causal motivational thing etc
                Intent intent = new Intent(getActivity(), DetailNote.class);
                startActivity(intent);
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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        Uri uri = Uri.parse(UcanContentProvider.CONTENT_URI + "/"
                + info.id);
        String where = String.valueOf(info.id);

        switch (item.getItemId()) {
            case R.id.delete:

                getActivity().getContentResolver().delete(uri, where, null);
                populateView();
                break;




            case R.id.edit:
//TODo update?

                break;
            case R.id.share:
                //TODO text?
                break;

        }

        return super.onContextItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Toast.makeText(getActivity(),"id is:"+id,Toast.LENGTH_LONG).show();

        if (id==0) {
//            String[] selection ={UcanContract.Tasks._ID,UcanContract.Tasks.COLUMN_TASKS};
//
//        CursorLoader cursorLoader = new CursorLoader(getActivity(), UcanContentProvider.CONTENT_URI,selection, null,null,null);

            cl = new CursorLoader(getActivity(), UcanContentProvider.CONTENT_URI, null, null, null, null);




        }else if(id==1){

            String selection= UcanContract.Tasks.COLUMN_URGENCY+"=0";
            cl = new CursorLoader(getActivity(), UcanContentProvider.CONTENT_URI, null, selection, null, null);

        }
     return cl;
 }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        cVA = new CustomViewAdapter(getActivity(),data,0);
        listView.setAdapter(cVA);
//myCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
myCursorAdapter.swapCursor(null);
  //cVA.swapCursor(null);
    }

}


