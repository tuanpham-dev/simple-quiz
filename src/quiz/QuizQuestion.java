package quiz;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizQuestion {
    private final Question question;
    private final Set<Answer> userAnswers;
    private boolean isAnswered;

    public QuizQuestion(Question question) {
        this.question = question;
        userAnswers = new HashSet<>();
        isAnswered = false;

        question.shuffleAnswers();
    }

    public String getTitle() {
        return question.getTitle();
    }

    public List<Answer> getAnswers() {
        return question.getAnswers();
    }

    public Set<Answer> getCorrectAnswers() {
        return question.getCorrectAnswers();
    }

    public boolean isMultipleChoice() {
        return question.isMultipleChoice();
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;

        if (!isAnswered) {
            userAnswers.clear();
        }
    }

    public int getTotalAnswers() {
        return question.getAnswers().size();
    }

    public void addUserAnswer(int answerIndex) {
        Answer answer = question.getAnswers().get(answerIndex);

        userAnswers.add(answer);
    }

    public Set<Answer> getUserAnswers() {
        return userAnswers;
    }

    public boolean isCorrect() {
        return userAnswers.equals(question.getCorrectAnswers());
    }
}
