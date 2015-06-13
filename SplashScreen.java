package ivano.android.com.ucanote;
//A ProgressDialog object

/** Called when the activity is first created. */


//To use the AsyncTask, it must be subclassed

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;




public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout_screen);
        new Screen().execute();
    }

    private class Screen extends AsyncTask {


        @Override
        protected Object doInBackground(Object[] params) {
            try{
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;


        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}