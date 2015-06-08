package ivano.android.com.ucanote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class AddTask extends ActionBarActivity  {
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note_layout);


        final EditText et=(EditText) findViewById(R.id. tasktest);
        checkbox = (CheckBox) findViewById(R.id.checkbox);




        et.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {



                Bundle bundle = getIntent().getExtras();


                Intent reply_back = new Intent();

                reply_back.putExtra(getPackageName(), et.getText().toString());


                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                String timeCurrent = today.format("%Y-%m-%d %H:%M:%S");
                reply_back.putExtra("tempo", timeCurrent);
                Integer checkUrgent=1;
                if (checkbox.isChecked()) {
                    checkUrgent=0;

                }
                reply_back.putExtra("YesOrNot", checkUrgent);


                setResult(123, reply_back);
                finish();

                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_note, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

