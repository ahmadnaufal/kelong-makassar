package id.kelongmakassar.kelongmakassar.ui.track.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Track;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackMeaningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackMeaningFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TRACK = "TrackMeaningFragment.TRACK";

    private Track mTrack;

    @BindView(R.id.text_track_meaning) TextView trackMeaningTextView;

    public TrackMeaningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param track The track item
     * @return A new instance of fragment TrackMeaningFragment.
     */
    public static TrackMeaningFragment newInstance(Track track) {
        TrackMeaningFragment fragment = new TrackMeaningFragment();
        Bundle args = new Bundle();
        args.putParcelable(TRACK, track);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTrack = getArguments().getParcelable(TRACK);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_track_meaning, container, false);
        ButterKnife.bind(this, rootView);

        initLayout();

        return rootView;
    }

    private void initLayout() {
        if (mTrack != null) {
            // start to read lyric file from asset folder
            BufferedReader reader = null;
            StringBuilder text = new StringBuilder();

            try {
                reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(mTrack.getMeaningPath()), "UTF-8"));
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    text.append(mLine);
                    text.append('\n');
                }
            } catch (IOException e) {
                Toast.makeText(getActivity(), "Error reading file!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            trackMeaningTextView.setText(text);
        }
    }
}
