package id.kelongmakassar.kelongmakassar.ui.tutorial;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Tutorial;
import id.kelongmakassar.kelongmakassar.util.Utility;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TutorialActivity extends AppCompatActivity implements TutorialMvpView, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, AudioManager.OnAudioFocusChangeListener {

    @BindView(R.id.recyclerview_tutorial_list) RecyclerView tutorialListRecyclerView;
    @BindView(R.id.layout_tutorial_example) LinearLayout tutorialExampleLayout;

    private boolean isExampleShown = false;

    private TutorialAdapter mTutorialAdapter;
    private TutorialPresenter mPresenter;
    private MediaPlayer mMediaPlayer;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TutorialActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);

        initLayout();
        preparePlayer();

        mPresenter = new TutorialPresenter(this);
        mPresenter.loadTutorials();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            stopMedia();
            mMediaPlayer.release();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        super.onBackPressed();
    }

    @OnClick(R.id.button_examples)
    void onExampleClick() {
        isExampleShown = !isExampleShown;
        tutorialExampleLayout.setVisibility(isExampleShown ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.button_atiraja_no_vocal)
    void onAtirajaNoVocalClick() {
        Intent intent = TutorialExampleActivity.getStartIntent(this, false);
        startActivity(intent);
    }

    @OnClick(R.id.button_atiraja_vocal)
    void onAtirajaVocalClick() {
        Intent intent = TutorialExampleActivity.getStartIntent(this, true);
        startActivity(intent);
    }

    private void initLayout() {
        mTutorialAdapter = new TutorialAdapter(this);
        tutorialListRecyclerView.setAdapter(mTutorialAdapter);
        tutorialListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tutorialListRecyclerView.setNestedScrollingEnabled(false);
        mTutorialAdapter.setListener(new OnTutorialInteractionListener() {
            @Override
            public void onPlayButtonClicked(Tutorial tutorial) {
                if (mMediaPlayer != null) {
                    stopMedia();

                    mMediaPlayer.reset();
                    try {
                        // set the datasource to the mMediaPath attribute
                        mMediaPlayer.setDataSource(TutorialActivity.this, Utility.resolveLocalMediaResourceUri(tutorial.getResId(), TutorialActivity.this));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mMediaPlayer.prepareAsync();
                }
            }
        });

        // hide button layout
        tutorialExampleLayout.setVisibility(View.GONE);
    }

    private void playMedia() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }

    private void stopMedia() {
        if (mMediaPlayer == null) return;
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    private void preparePlayer() {
        mMediaPlayer = new MediaPlayer();

        // set up media event listeners
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);
        mMediaPlayer.setOnInfoListener(this);

        // reset the media player so it wont point to other media source
        mMediaPlayer.reset();

        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void onTutorialListLoaded(List<Tutorial> tutorialList) {
        mTutorialAdapter.setTutorialList(tutorialList);
        mTutorialAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onAudioFocusChange(int focusState) {
        //Invoked when the audio focus of the system is updated.
        switch (focusState) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // we gain audio focus, so resume the playback
                if (mMediaPlayer == null) {
                    preparePlayer();
                } else if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                }
                mMediaPlayer.setVolume(1.0f, 1.0f);
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                // we lost the focus for an unbounded amount of time: stop playback
                // and release media player
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }

                mMediaPlayer.release();
                mMediaPlayer = null;
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // lost focus for a short time, but we have to stop playback.
                // Do not release the media player
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // lost focus for a short time, keep playing for a lower level
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.setVolume(0.1f, 0.1f);
                }
                break;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopMedia();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        //Invoked when there has been an error during an asynchronous operation
        switch (i) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Log.d("MediaPlayer Error", "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + i1);
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.d("MediaPlayer Error", "MEDIA ERROR SERVER DIED " + i1);
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.d("MediaPlayer Error", "MEDIA ERROR UNKNOWN " + i1);
                break;
        }
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        playMedia();
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {

    }
}
