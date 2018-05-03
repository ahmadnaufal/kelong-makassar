package id.kelongmakassar.kelongmakassar.ui.track.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Track;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackNotationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackNotationFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TRACK = "TrackNotationFragment.TRACK";

    private Track mTrack;

    public TrackNotationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param track The track item
     * @return A new instance of fragment TrackNotationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackNotationFragment newInstance(Track track) {
        TrackNotationFragment fragment = new TrackNotationFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_track_notation, container, false);
        ButterKnife.bind(this, rootView);


        return rootView;
    }

}
