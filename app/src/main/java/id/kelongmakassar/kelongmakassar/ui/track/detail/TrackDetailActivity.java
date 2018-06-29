package id.kelongmakassar.kelongmakassar.ui.track.detail;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.model.Track;
import id.kelongmakassar.kelongmakassar.util.Utility;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TrackDetailActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, AudioManager.OnAudioFocusChangeListener {

    private static final String TAG = TrackDetailActivity.class.getSimpleName();
    private static final String TRACK = "id.kelongmakassar.kelongmakassar.ui.track.detail.TrackDetailActivity.TRACK";

    @BindView(R.id.seekbar_status_song) SeekBar statusSongSeekBar;
    @BindView(R.id.text_song_title) TextView songTitleTextView;
    @BindView(R.id.text_elapsed_time) TextView elapsedTimeTextView;
    @BindView(R.id.text_remaining_time) TextView remainingTimeTextView;
    @BindView(R.id.button_control) ImageView controlImageView;

    private Track mTrack;
    private MediaPlayer mMediaPlayer;
    private int resumePosition;

    private boolean isPaused;

    private Handler mHandler = new Handler();
    private Runnable updateSeekbarTask = new Runnable() {
        @Override
        public void run() {
            updateSeekbar();
        }
    };

    public static Intent getStartIntent(Context context, Track track) {
        Intent intent = new Intent(context, TrackDetailActivity.class);
        intent.putExtra(TRACK, track);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mTrack = savedInstanceState.getParcelable(TRACK);
        } else {
            mTrack = getIntent().getParcelableExtra(TRACK);
        }

        initFirstLayout();
        preparePlayer();

        statusSongSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int currentSeconds = progress / 1000;
                elapsedTimeTextView.setText(DateUtils.formatElapsedTime(currentSeconds));
                remainingTimeTextView.setText("- " + DateUtils.formatElapsedTime((statusSongSeekBar.getMax() / 1000) - currentSeconds));
                if (fromUser) {
                    seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateSeekbar() {
        if (mMediaPlayer != null) {
            int currentPosition = mMediaPlayer.getCurrentPosition();
            statusSongSeekBar.setProgress(currentPosition);
        }

        mHandler.postDelayed(updateSeekbarTask, 100);
    }

    private void initFirstLayout() {
        if (mTrack != null) {
            songTitleTextView.setText(mTrack.getTitle());
        }

        statusSongSeekBar.setEnabled(false);
        controlImageView.setEnabled(false);
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
        try {
            // set the datasource to the mMediaPath attribute
            mMediaPlayer.setDataSource(this, Utility.resolveLocalMediaResourceUri(mTrack.getResId(), this));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.prepareAsync();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.button_control)
    void onMediaControlClick() {
        if (isPaused) {
            if (resumePosition > 0) {
                resumeMedia();
            } else {
                playMedia();
            }
        } else {
            pauseMedia();
        }

        isPaused = !isPaused;
        controlImageView.setImageResource(isPaused ? R.drawable.ic_play : R.drawable.ic_pause);
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        super.onBackPressed();
    }

    @OnClick(R.id.button_lyrics)
    void onLyricsClick() {
        Fragment fragment = TrackLyricsFragment.newInstance(mTrack);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_buttons, fragment).addToBackStack(null).commit();
    }

    @OnClick(R.id.button_meaning)
    void onMeaningClick() {
        Fragment fragment = TrackMeaningFragment.newInstance(mTrack);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_buttons, fragment).addToBackStack(null).commit();
    }

    @OnClick(R.id.button_notation)
    void onNotationClick() {
        Fragment fragment = TrackNotationFragment.newInstance(mTrack);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_buttons, fragment).addToBackStack(null).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(TRACK, mTrack);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(updateSeekbarTask);
        if (mMediaPlayer != null) {
            stopMedia();
            mMediaPlayer.release();
        }
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
        Log.d(TAG, "MediaPlayer with the source is prepared! Preparing controller...");
        prepareController();
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        //Invoked indicating the completion of a seek operation.
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

    private void pauseMedia() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            resumePosition = mMediaPlayer.getCurrentPosition();
        }
    }

    private void seekTo(int duration) {
        if (mMediaPlayer == null) {
            return;
        }

        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.seekTo(duration);
        } else {
            resumePosition = duration;
        }
    }

    private void resumeMedia() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.seekTo(resumePosition);
            mMediaPlayer.start();
        }
    }

    private void prepareController() {
        int durationInMillis = mMediaPlayer.getDuration();

        isPaused = true;

        elapsedTimeTextView.setText(DateUtils.formatElapsedTime(0));
        remainingTimeTextView.setText("- " + DateUtils.formatElapsedTime(durationInMillis / 1000));

        statusSongSeekBar.setProgress(0);
        statusSongSeekBar.setMax(durationInMillis);
        statusSongSeekBar.setEnabled(true);
        controlImageView.setEnabled(true);

        runOnUiThread(updateSeekbarTask);
    }
}
