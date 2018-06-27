package id.kelongmakassar.kelongmakassar.ui.games;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kelongmakassar.kelongmakassar.R;
import id.kelongmakassar.kelongmakassar.data.factory.GamesFactory;
import id.kelongmakassar.kelongmakassar.data.model.Question;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GamesActivity extends AppCompatActivity implements KaraokeGameFragment.OnKaraokeGameListener, QuestionFragment.OnAnswerListener {

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
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_game_frame, fragment).addToBackStack(null).commit();
        } else {
            onFinish();
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
            mScore += 25;   // TODO: implement this inside the question, so we can get the score with a method i.e. question.getScore()
        }

        progressToNextQuestion();
    }
}
