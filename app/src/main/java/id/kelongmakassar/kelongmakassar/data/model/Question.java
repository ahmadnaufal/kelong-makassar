package id.kelongmakassar.kelongmakassar.data.model;

import android.support.v4.app.Fragment;

public interface Question {
    Fragment createQuestionFragment();
    boolean isAnswerCorrect(int chosenIndex);
    int getAnswerCorrectScore();
}
