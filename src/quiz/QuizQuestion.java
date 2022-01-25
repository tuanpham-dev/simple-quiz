package quiz;

import java.util.*;

public class QuizQuestion {
    private final Question question;
    private final Set<Answer> userAnswers;
    private final List<Answer> answers;
    private boolean isAnswered;

    public QuizQuestion(Question question) {
        this.question = question;
        userAnswers = new HashSet<>();
        isAnswered = false;
        answers = new ArrayList<>(question.getAnswers());

        Collections.shuffle(answers);
    }

    public String getTitle() {
        return question.getTitle();
    }

    public Iterable<Answer> getAnswers() {
        return answers;
    }

    public Iterable<Answer> getCorrectAnswers() {
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
        return answers.size();
    }

    public void addUserAnswer(int answerIndex) {
        Answer answer = answers.get(answerIndex);

        userAnswers.add(answer);
    }

    public Set<Answer> getUserAnswers() {
        return userAnswers;
    }

    public boolean isCorrect() {
        return userAnswers.equals(question.getCorrectAnswers());
    }
}
