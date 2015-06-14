package ivano.android.com.ucanote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity    implements FragmentAsList.Callback {

    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // BroadcastNotification.scheduleAlarms(this);
       // Toast.makeText(this,"See you in five days",Toast.LENGTH_LONG).show();





        notificationStart();
        if (findViewById(R.id.detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;

            //TODO  should stay or should go
            getSupportActionBar().setElevation(0f);
        }


    }//end on Create

    private void notificationStart() {
        //TODO insert if urgent>10 then start notification below, of course should interrogate the database
//insert async task call and implment the code solution 3 b here https://androidresearch.wordpress.com/2013/05/10/dealing-with-asynctask-and-screen-orientation/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO  see what happen if you delete these lines




    }

    @Override
    public void onItemSelected(Uri contentUri) {
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putParcelable(DetailFragment.DETAIL_URI, contentUri);
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, fragment, DETAILFRAGMENT_TAG).commit();
        } else {
            Intent intent = new Intent(this, Detail.class)
                    .setData(contentUri);
            startActivity(intent);
        }
    }

}

