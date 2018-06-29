package id.kelongmakassar.kelongmakassar.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import id.kelongmakassar.kelongmakassar.ui.games.SongContinueGameFragment;

public class SongContinueQuestion implements Question, Parcelable {

    private int resId;
    private String question;
    private int[] answerSongList;
    private int correctAnswerIdx;

    public SongContinueQuestion(int resId, String question, int[] answerSongList, int correctAnswerIdx) {
        this.resId = resId;
        this.question = question;
        this.answerSongList = answerSongList;
        this.correctAnswerIdx = correctAnswerIdx;
    }

    public String getQuestion() {
        return question;
    }

    public int[] getAnswerSongList() {
        return answerSongList;
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
        parcel.writeIntArray(answerSongList);
        parcel.writeInt(correctAnswerIdx);
    }

    public static final Parcelable.Creator<SongContinueQuestion> CREATOR = new Parcelable.Creator<SongContinueQuestion>() {
        @Override
        public SongContinueQuestion createFromParcel(Parcel parcel) {
            return new SongContinueQuestion(parcel);
        }

        @Override
        public SongContinueQuestion[] newArray(int size) {
            return new SongContinueQuestion[size];
        }
    };

    private SongContinueQuestion(Parcel in) {
        resId = in.readInt();
        question = in.readString();
        in.readIntArray(answerSongList);
        correctAnswerIdx = in.readInt();
    }

    @Override
    public Fragment createQuestionFragment() {
        return SongContinueGameFragment.newInstance(this);
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
