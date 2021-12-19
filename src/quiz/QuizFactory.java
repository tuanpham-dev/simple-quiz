package quiz;

import java.util.List;

public abstract class QuizFactory<S extends QuizQuestion, T extends Quiz<S>> {
    public abstract S getQuizQuestion(Question question);
    public abstract T getQuiz(List<S> quizQuestions);
}
