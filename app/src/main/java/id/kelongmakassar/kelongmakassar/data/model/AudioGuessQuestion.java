package id.kelongmakassar.kelongmakassar.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AudioGuessQuestion implements Question, Parcelable {

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

    public static final Parcelable.Creator<AudioGuessQuestion> CREATOR = new Parcelable.Creator<AudioGuessQuestion>() {
        @Override
        public AudioGuessQuestion createFromParcel(Parcel parcel) {
            return new AudioGuessQuestion(parcel);
        }

        @Override
        public AudioGuessQuestion[] newArray(int size) {
            return new AudioGuessQuestion[size];
        }
    };

    private AudioGuessQuestion(Parcel in) {
        resId = in.readInt();
        in.readStringArray(answerList);
        correctAnswerIdx = in.readInt();
    }
}
