package ivano.android.com.ucanote;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ivano.android.com.ucanote.ivano.android.com.ucanote.db.UcanContract;


/**
 * Created by ivano on 5/6/2015.
 */
public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    TextView textView;
    Uri mUri;

    static final String DETAIL_URI = "URI";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        View detailView = inflater.inflate(R.layout.activity_detail2, container, false);


        if (arguments != null) {
            mUri = arguments.getParcelable(DetailFragment.DETAIL_URI);
            textView = (TextView) detailView.findViewById(R.id.detail_text);

            String mUriString = mUri.toString();

            textView.setText(mUriString);

        }

        return detailView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(4, null, this);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mUri!=null) {

             Long idLong= FragmentAsList.mId;
            String idString = Long.toString(idLong);
            String[] projection = {UcanContract.Tasks.COLUMN_TASKS};
            String where = "_id=" + idString;


            return new CursorLoader(getActivity(), mUri,projection, where, null, null);

        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            String description = data.getString(0);

            textView.setText(description);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}




