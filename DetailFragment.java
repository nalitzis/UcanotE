package ivano.android.com.ucanote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

/**
 * Created by ivano on 5/6/2015.
 */
public class DetailFragment extends Fragment {

    CheckBox checkbox;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View EditAndCheckBox = inflater.inflate(R.layout.activity_detail_note_layout, container, false);
//
//        final EditText et = (EditText) EditAndCheckBox.findViewById(R.id.tasktest);
//        checkbox = (CheckBox) EditAndCheckBox.findViewById(R.id.checkbox);
//

//        et.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                // Toast.makeText(getApplicationContext(),et.getText(),Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            manageIntentAndBack();
//take data
//                //TODO if more time more elegant( more readable) would be create a reference from the inner getClass()
//                // to outside http://salesforce.stackexchange.com/questions/14061/how-to-access-outer-class-instance-variables-from-inner-class-in-controller
//
//
//                Bundle bundle = getActivity().getIntent().getExtras();
//
//                //send reply
//
//
//                Intent reply_back = new Intent();
//                //reply_back.putExtra(getPackageName(), et.getText());
//
//                reply_back.putExtra(getActivity().getPackageName(), et.getText().toString());
//
//
//                Time today = new Time(Time.getCurrentTimezone());
//                today.setToNow();
//                String timeCurrent = today.format("%Y-%m-%d %H:%M:%S");
//                reply_back.putExtra("tempo", timeCurrent);
//
//                //TODO ask is this efficient?
//                //notice that i tag 0 as checked contrarily as common sense would say if one indicate a boolean
//                Integer checkUrgent = 1;
//                if (checkbox.isChecked()) {
//                    checkUrgent = 0;
//
//                }
//                reply_back.putExtra("YesOrNot", checkUrgent);
//
////reply_back.putExtra("YesOrNot",)
//
//                //give back the K identifier 123
//                getActivity().setResult(123, reply_back);
//                getActivity().finish();
//
//                return false;
//            }
//
//
//
//        });
//        return EditAndCheckBox;
//    }
//
        return null;
    }
}