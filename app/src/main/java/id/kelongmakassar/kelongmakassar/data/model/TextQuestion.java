package id.kelongmakassar.kelongmakassar.data.model;

public class TextQuestion implements Question {

    private String question;
    private String[] answerList;
    private int correctAnswerIdx;

    public TextQuestion(String question, String[] answerList, int correctAnswerIdx) {
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
