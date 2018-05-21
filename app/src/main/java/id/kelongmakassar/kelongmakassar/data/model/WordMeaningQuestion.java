package id.kelongmakassar.kelongmakassar.data.model;

public class WordMeaningQuestion implements Question {

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
}
