package id.kelongmakassar.kelongmakassar.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import id.kelongmakassar.kelongmakassar.ui.games.SongPartGameFragment;

public class SongPartQuestion implements Question, Parcelable {

    private int resId;
    private String question;
    private String[] answerList;
    private int correctAnswerIdx;

    public SongPartQuestion(int resId, String question, String[] answerList, int correctAnswerIdx) {
        this.resId = resId;
        this.question = question;
        this.answerList = answerList;
        this.correctAnswerIdx = correctAnswerIdx;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswerList() {
        return answerList;
    }

    public int getResId() {
        return resId;
    }

    public int getCorrectAnswerIdx() {
        return correctAnswerIdx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(resId);
        parcel.writeString(question);
        parcel.writeStringArray(answerList);
        parcel.writeInt(correctAnswerIdx);
    }

    public static final Parcelable.Creator<SongPartQuestion> CREATOR = new Parcelable.Creator<SongPartQuestion>() {
        @Override
        public SongPartQuestion createFromParcel(Parcel parcel) {
            return new SongPartQuestion(parcel);
        }

        @Override
        public SongPartQuestion[] newArray(int size) {
            return new SongPartQuestion[size];
        }
    };

    private SongPartQuestion(Parcel in) {
        resId = in.readInt();
        question = in.readString();
        in.readStringArray(answerList);
        correctAnswerIdx = in.readInt();
    }

    @Override
    public Fragment createQuestionFragment() {
        return SongPartGameFragment.newInstance(this);
    }

    @Override
    public boolean isAnswerCorrect(int chosenIndex) {
        return correctAnswerIdx == chosenIndex;
    }

    @Override
    public int getAnswerCorrectScore() {
        return 25;
    }
}
