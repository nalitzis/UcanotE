package ivano.android.com.ucanote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class DetailNote extends ActionBarActivity  {
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_detail);
        //TODO first please bare in mind that the original layout with edittext and checkbox is below
        setContentView(R.layout.activity_detail_note_layout);
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container,new DetailFragment())
                    .commit();
        }

        final EditText et=(EditText) findViewById(R.id. tasktest);
        checkbox = (CheckBox) findViewById(R.id.checkbox);




        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // Toast.makeText(getApplicationContext(),et.getText(),Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            manageIntentAndBack();
//take data
                //TODO if more time more elegant( more readable) would be create a reference from the inner getClass()
                // to outside http://salesforce.stackexchange.com/questions/14061/how-to-access-outer-class-instance-variables-from-inner-class-in-controller


                Bundle bundle = getIntent().getExtras();
                Log.i("FragmentasList", bundle.getString(getPackageName()));

                //send reply


                Intent reply_back = new Intent();
                //reply_back.putExtra(getPackageName(), et.getText());

                reply_back.putExtra(getPackageName(), et.getText().toString());


                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                String timeCurrent = today.format("%Y-%m-%d %H:%M:%S");
                reply_back.putExtra("tempo", timeCurrent);

                //TODO ask is this efficient?
                //notice that i tag 0 as checked contrarily as common sense would say if one indicate a boolean
                Integer checkUrgent=1;
                if (checkbox.isChecked()) {
                    checkUrgent=0;

                }
                reply_back.putExtra("YesOrNot", checkUrgent);

//reply_back.putExtra("YesOrNot",)

                //give back the K identifier 123
                setResult(123, reply_back);
                finish();

                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_note, menu);
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

}
