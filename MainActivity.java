package ivano.android.com.ucanote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity    implements FragmentAsList.Callback{
    //TODO FIRST1 reminder notification after spinner list
    //1 day, 3 days, 5 days, 1 week
    // or calendar  http://stackoverflow.com/questions/10977422/how-to-create-simple-calendar-on-android
//TODO FIRST1 http://stackoverflow.com/questions/12208008/how-to-create-a-reminder-notification
// http://stackoverflow.com/questions/21078609/start-app-and-load-data-with-viewpager-asynctask
//splashscreen and viewpager http://stackoverflow.com/questions/19225969/splash-screen-with-asynctask

//    Intent myIntent = new Intent(this , NotifyService.class);
//    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//    pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);
//
//    Calendar calendar = Calendar.getInstance();
//    calendar.set(Calendar.SECOND, 0);
//    calendar.set(Calendar.MINUTE, 0);
//    calendar.set(Calendar.HOUR, 0);
//    calendar.set(Calendar.AM_PM, Calendar.AM);
//    calendar.add(Calendar.DAY_OF_MONTH, 1);
//
//    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24 , pendingIntent);
//    This will trigger Alarm each day at midnight (12 am). You can change that if you want.
//
//    Now, create a Service NotifyService and put this code in its onCreate():This will trigger Alarm each day at midnight (12 am). You can change that if you want.
//
//    Now, create a Service NotifyService and put this code in its onCreate():
//
//
//    @Override
//    public void onCreate() {
//        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        Notification notification = new Notification(R.drawable.notification_icon, "Notify Alarm strart", System.currentTimeMillis());
//        Intent myIntent = new Intent(this , MyActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        notification.setLatestEventInfo(this, "Notify label", "Notify text", contentIntent);
//        mNM.notify(NOTIFICATION, notification);
//    }
//
//
//    here is a little YouTube Video Tutorial about daily notifications. You can find the source code in the description.
//
//    This video is not made by myself. But I think its a quick help. Although i recommend some changes because the Notification.Builder is deprecated:
//
//            1.
//
//            import android.support.v4.app.NotificationCompat;
//    2.
//
//    // Change: Notification mNotify = new Notification.Builder(this) to
//    Notification mNotify = new NotificationCompat.Builder(this)
//    Have Fun!
//    https://www.youtube.com/watch?v=tyVaPHv-RGo
//


    //TODO FIRST CONFRONTW ITH THE ONE THAT WORKS
//TODO terrible icon for ShareActionProvider, you want a red share!
//TODO FIRST start with animations http://www.androidhive.info/2013/06/android-working-with-xml-animations/ FOR asyNCTASK OR sHAREiNTENT
//TODO when I add a task if I am in the list with all the urgent when i go back should I see also the non urgent or I remain that?
//TODO  maybe some thema?
//TODO change color ActionBar http://stackoverflow.com/questions/8024706/how-do-i-change-the-background-color-of-the-actionbar-of-an-actionbaractivity-us
//TODO FIRST  CLEAN UP THE CODE USE ANT AND BE SAFE YOU HAVE A PUSHED VERSION

//TODO  uncomment TODO FIND snippets
//notification se 5 urgenti allora al boot time scrivi aho datte' na sveglia
    ///TODO Asynctask image password points
    //todo in the detail you have a textview but you need click have edit text and update back the listview
    //casual quotes?detail activity should be refined a bit
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
    //TODO  order by date, id( insertion), preferences, share, fragment, services, syncadapter, broadcast receiver,
    //save when ord ered, drag list view, transparentbackground, choice background, distantiate listview, personalize actionbar.
//search button, implement the other fields
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //We can remove it because is already declared in the layout above activity_main so there
        //is not need to add it dynamically again
//
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//           .add(R.id.framebig, new FragmentAsList()).commit();
//
//        }



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
        new notificationStart();
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

        FragmentAsList fas = (FragmentAsList) getFragmentManager()
                .findFragmentById(R.id.fragment_as_list);


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
            Intent intent = new Intent(this,Detail.class)
                    .setData(contentUri);
            startActivity(intent);
        }
    }


   }

//    @Override
//    protected void onDestroy() {
//        this.unregisterReceiver(receiver);
//        super.onDestroy();
//    }



