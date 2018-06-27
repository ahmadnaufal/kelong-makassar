package id.kelongmakassar.kelongmakassar.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import id.kelongmakassar.kelongmakassar.ui.games.KaraokeGameFragment;

public class KaraokeQuestion implements Question, Parcelable {

    private int resId;
    private String[] answerList;
    private int correctAnswerIdx;

    public KaraokeQuestion(int resId, String[] answerList, int correctAnswerIdx) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(resId);
        parcel.writeStringArray(answerList);
        parcel.writeInt(correctAnswerIdx);
    }

    public static final Parcelable.Creator<KaraokeQuestion> CREATOR = new Parcelable.Creator<KaraokeQuestion>() {
        @Override
        public KaraokeQuestion createFromParcel(Parcel parcel) {
            return new KaraokeQuestion(parcel);
        }

        @Override
        public KaraokeQuestion[] newArray(int size) {
            return new KaraokeQuestion[size];
        }
    };

    private KaraokeQuestion(Parcel in) {
        resId = in.readInt();
        in.readStringArray(answerList);
        correctAnswerIdx = in.readInt();
    }

    @Override
    public Fragment createQuestionFragment() {
        return KaraokeGameFragment.newInstance(this);
    }

    @Override
    public boolean isAnswerCorrect(int chosenIndex) {
        return correctAnswerIdx == chosenIndex;
    }
}
