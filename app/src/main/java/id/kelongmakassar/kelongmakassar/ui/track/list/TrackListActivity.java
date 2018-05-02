package id.kelongmakassar.kelongmakassar.ui.track.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Track;
import id.kelongmakassar.kelongmakassar.ui.track.detail.TrackDetailActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TrackListActivity extends AppCompatActivity implements TrackListMvpView {

    @BindView(R.id.recyclerview_track_list) RecyclerView mTrackListRecyclerView;

    private TrackAdapter mTrackAdapter;
    private TrackListPresenter mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TrackListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);
        ButterKnife.bind(this);

        mPresenter = new TrackListPresenter(this);

        initAdapters();
        mPresenter.loadTracks();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initAdapters() {
        mTrackAdapter = new TrackAdapter(this);
        mTrackListRecyclerView.setAdapter(mTrackAdapter);
        mTrackListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTrackListRecyclerView.setNestedScrollingEnabled(false);
        mTrackAdapter.setListener(new OnTrackSelectedListener() {
            @Override
            public void onTrackSelected(Track track) {
                // open the track detail activity
                Intent intent = TrackDetailActivity.getStartIntent(TrackListActivity.this, track);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        super.onBackPressed();
    }

    @Override
    public void onTrackListLoaded(List<Track> trackList) {
        mTrackAdapter.setTrackList(trackList);
        mTrackAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onLoading() {

    }
}
