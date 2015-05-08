package ivano.android.com.ucanote;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
    //cursor adapter customize to click and change something int he list view
    //http://stackoverflow.com/questions/9690439/creating-custom-simple-cursor-adapter?rq=1
// put right allign with padding 20 from right etc so that is not urgent,if urgent leave like this

    //change layout to original
    //make cursorViewAdapter
    //make special row layout
    //see if you can make on click big row( maybe you can see emails)
    //make a database that you can order the things as outliners but ordered per database but this should be another app
    //follow tutorial
    //master detail view
    //list view bigger, double layout with rating and then spinner to order for preferences and then master detail
    //trnsparent grassetto, illumina bianco urgency e distacca le list views
    // create themes, ma anche la possibilita' di fare una foto
    //quotes

//begin with a Checkbox with ( a star or other) style="?android:attr/starStyle" star in detail that tag as urgency and then see if remain in memory, otherwse search on web, put a rating bar http://www.mkyong.com/android/android-rating-bar-example/

// todo, redifine layout detail, bring option urgency,
// set in the main if pressed urgency then query and fill urgent tasks only

//    if urgent then take casual quote
// broadcast receiver, action bar icons with dialog box sort , share
// then master detail view and viewpager(only first time have to appear then broadcast or syncadapter and customview, then transparence
// a picker to set in from 1 to 5, help, share, at place of ID if urgent give a symbol, maybe the index!
    //transparent enlarge quote

    //TODO FIRST  see difference between debug favorite and reset in menu

    // //spinner into menu on the left
//    http://www.hasnath.net/blog/actionbar-tab-spinnerlist-navigation-at-the-same-time
// sync, see smart pad, master detail,, casual quotes
//TODO Others, viewpager? ? ?insert screen help central and..
    // preferences
    // bassa priorita' vedi il nuovo menu context floating
    //TRANSPARENT list view , scegli sfondi, che si abbinano al carattere, separa bordi
    //http://stackoverflow.com/questions/1377336/how-to-make-a-listview-transparent-in-android
    //check also listview state against rotation of the device http://stackoverflow.com/questions/16692536/good-solution-to-retain-listview-items-when-user-rotate-phone-and-keep-all-data
    //NOTA I resolved the issue that inside the fragment was not working the contextmenu click, using a Log.d template and discovering that I had to implement the method
    //inside the fragment view
    //personalize ActionBar http://stackoverflow.com/questions/12897071/how-to-change-size-of-titles-text-on-action-bar
    //TODO first order by date, id( insertion), preferences, share, fragment, services, syncadapter, broadcast receiver,
    //save when ord ered, drag list view, transparentbackground, choice background, distantiate listview, personalize actionbar.
//search button, implement the other fields
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;
    private String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //We can remove it because is already declared in the layout above activity_main so there
        //is not need to add it dynamically again
//
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction().add(R.id.framebig, new FragmentAsList()).commit();
//
//        }




        if (findViewById(R.id.detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }



    }//end on Create

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
        FragmentAsList fas = (FragmentAsList) getFragmentManager().findFragmentById(R.id.fragment_as_list);


    }
}



