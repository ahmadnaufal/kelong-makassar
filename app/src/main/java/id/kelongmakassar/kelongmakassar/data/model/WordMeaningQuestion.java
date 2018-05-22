package id.kelongmakassar.kelongmakassar.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WordMeaningQuestion implements Question, Parcelable {

    private String question;
    private String[] answerList;
    private int correctAnswerIdx;

    public WordMeaningQuestion(String question, String[] answerList, int correctAnswerIdx) {
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

    public int getCorrectAnswerIdx() {
        return correctAnswerIdx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeStringArray(answerList);
        parcel.writeInt(correctAnswerIdx);
    }

    public static final Parcelable.Creator<WordMeaningQuestion> CREATOR = new Parcelable.Creator<WordMeaningQuestion>() {
        @Override
        public WordMeaningQuestion createFromParcel(Parcel parcel) {
            return new WordMeaningQuestion(parcel);
        }

        @Override
        public WordMeaningQuestion[] newArray(int size) {
            return new WordMeaningQuestion[size];
        }
    };

    private WordMeaningQuestion(Parcel in) {
        question = in.readString();
        in.readStringArray(answerList);
        correctAnswerIdx = in.readInt();
    }
}
