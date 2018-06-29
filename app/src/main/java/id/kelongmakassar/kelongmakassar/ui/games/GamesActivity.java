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
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.factory.GamesFactory;
import id.kelongmakassar.kelongmakassar.data.model.Question;
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

        // generate quiz by factory
        GamesFactory gamesFactory = new GamesFactory();
        mQuestionList = gamesFactory.generateRandomFourQuestions();
        progressToNextQuestion();
    }

    private void progressToNextQuestion() {
        currentIndex++;

        if (currentIndex < mQuestionList.size()) {
            Fragment fragment = mQuestionList.get(currentIndex).createQuestionFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.layout_game_frame, fragment);
            if (currentIndex > 0) {
                fragmentTransaction.addToBackStack(null);
            }

            fragmentTransaction.commit();

            // change question indicator status
            switch (currentIndex) {
                case 0: {
                    changeIndicatorToActive(indicatorTextView1);
                    break;
                }
                case 1: {
                    changeIndicatorToActive(indicatorTextView2);
                    changeIndicatorToInactive(indicatorTextView1);
                    break;
                }
                case 2: {
                    changeIndicatorToActive(indicatorTextView3);
                    changeIndicatorToInactive(indicatorTextView2);
                    break;
                }
                case 3: {
                    changeIndicatorToActive(indicatorTextView4);
                    changeIndicatorToInactive(indicatorTextView3);
                    break;
                }
            }
        } else {
            onFinish();
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.image_back)
    void onBackClick() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (currentIndex > -1) {
            currentIndex--;
        }

        super.onBackPressed();
    }

    // called if user has finished the quiz
    private void onFinish() {
        Intent intent = GamesScoreActivity.getStartIntent(this, mScore);
        startActivity(intent);

        finish();
    }

    @Override
    public boolean onPlayButtonPressed(int resId) {
        return false;
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

    }

    @Override
    public void onAudioFocusChange(int i) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {

    }
}
