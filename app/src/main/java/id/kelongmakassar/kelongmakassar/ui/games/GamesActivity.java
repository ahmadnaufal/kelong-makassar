package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.factory.GamesFactory;
import id.kelongmakassar.kelongmakassar.data.model.Question;
import id.kelongmakassar.kelongmakassar.util.Utility;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GamesActivity extends AppCompatActivity implements QuestionFragment.OnAnswerListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener, AudioManager.OnAudioFocusChangeListener {

    @BindView(R.id.text_indicator_1) TextView indicatorTextView1;
    @BindView(R.id.text_indicator_2) TextView indicatorTextView2;
    @BindView(R.id.text_indicator_3) TextView indicatorTextView3;
    @BindView(R.id.text_indicator_4) TextView indicatorTextView4;

    private int mScore;
    private int currentIndex = -1;
    private List<Question> mQuestionList;

    private MediaPlayer mMediaPlayer;
    private int resumePosition;
    private int currentSongResId;
    private boolean isPaused;

    private List<TextView> questionIndicators = new ArrayList<>();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, GamesActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);

        mScore = 0;

        preparePlayer();

        questionIndicators.add(indicatorTextView1);
        questionIndicators.add(indicatorTextView2);
        questionIndicators.add(indicatorTextView3);
        questionIndicators.add(indicatorTextView4);

        // generate quiz by factory
        GamesFactory gamesFactory = new GamesFactory();
        mQuestionList = gamesFactory.generateRandomFourQuestions();
        progressToNextQuestion();
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

    @Override
    public void onBackPressed() {
        if (currentIndex > -1) {
            currentIndex--;
            setQuestionIndicator();
        }

        super.onBackPressed();
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        onBackPressed();
    }

    @Override
    public boolean onPlayButtonPressed(int resId) {
        if (resId == currentSongResId) {
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
            return !isPaused;
        } else {
            setUpMedia(resId);
            return true;
        }
    }

    private void progressToNextQuestion() {
        currentIndex++;
        stopMedia();

        if (currentIndex < mQuestionList.size()) {
            Fragment fragment = mQuestionList.get(currentIndex).createQuestionFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.layout_game_frame, fragment);
            if (currentIndex > 0) {
                fragmentTransaction.addToBackStack(null);
            }

            fragmentTransaction.commit();

            // change question indicator status
            setQuestionIndicator();
        } else {
            onFinish();
        }
    }

    private void setQuestionIndicator() {
        for (int i = 0; i < questionIndicators.size(); i++) {
            if (i == currentIndex) {
                changeIndicatorToActive(questionIndicators.get(i));
            } else {
                changeIndicatorToInactive(questionIndicators.get(i));
            }
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

    private void resumeMedia() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.seekTo(resumePosition);
            mMediaPlayer.start();
        }
    }

    private void changeIndicatorToActive(TextView indicatorView) {
        indicatorView.setBackgroundResource(R.drawable.indicator_active);
        indicatorView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    private void changeIndicatorToInactive(TextView indicatorView) {
        indicatorView.setBackgroundResource(R.drawable.indicator_inactive);
        indicatorView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
    }

    // called if user has finished the quiz
    private void onFinish() {
        Intent intent = GamesScoreActivity.getStartIntent(this, mScore);
        startActivity(intent);

        finish();
    }

    @Override
    public void onAnswer(int index) {
        Question currentQuestion = mQuestionList.get(currentIndex);
        if (currentQuestion.isAnswerCorrect(index)) {
            mScore += currentQuestion.getAnswerCorrectScore();
        }

        progressToNextQuestion();
    }

    private void setUpMedia(int resId) {
        if (mMediaPlayer != null) {
            stopMedia();

            mMediaPlayer.reset();
            try {
                // set the datasource to the mMediaPath attribute
                mMediaPlayer.setDataSource(this, Utility.resolveLocalMediaResourceUri(resId, this));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // reset properties
            currentSongResId = resId;
            resumePosition = 0;

            mMediaPlayer.prepareAsync();
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
        isPaused = false;
        playMedia();
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {

    }
}
