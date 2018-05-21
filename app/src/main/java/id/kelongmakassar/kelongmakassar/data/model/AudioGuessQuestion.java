package id.kelongmakassar.kelongmakassar.data.model;

import android.os.Parcelable;

public class AudioGuessQuestion {

    private int resId;
    private String[] answerList;
    private int correctAnswerIdx;

    public AudioGuessQuestion(int resId, String[] answerList, int correctAnswerIdx) {
        this.resId = resId;
        this.answerList = answerList;
        this.correctAnswerIdx = correctAnswerIdx;
    }

    public int getResId() {
        return resId;
    }

    public String[] getAnswerList() {
        return answerList;
    }

    public int getCorrectAnswerIdx() {
        return correctAnswerIdx;
    }

}
