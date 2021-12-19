package quiz;

import java.util.List;

public abstract class Quiz<T extends QuizQuestion> {
    private final List<T> questions;
    private int currentQuestionIndex;
    public final boolean IS_ALREADY_LAST = false;

    public Quiz(List<T> questions) {
        this.questions = questions;
        currentQuestionIndex = 0;
    }

    public List<T> getQuestions() {
        return questions;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public T getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public boolean nextQuestion() {
        if (currentQuestionIndex >= questions.size() - 1) {
            return IS_ALREADY_LAST;
        }

        currentQuestionIndex++;

        return !IS_ALREADY_LAST;
    }

    public void previousQuestion() {
        if (currentQuestionIndex <= 0) {
            return;
        }

        currentQuestionIndex--;
    }

    public double getScore() {
        int correctAnswersCount = (int) questions.stream().filter(T::isCorrect).count();

        return (double) correctAnswersCount / questions.size();
    }

    public abstract void start();
}
