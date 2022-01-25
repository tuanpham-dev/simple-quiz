package quiz;

import java.util.List;

public abstract class QuizFactory<S extends QuizQuestion, T extends Quiz<S>> {
    public abstract S newQuizQuestion(Question question);
    public abstract T newQuiz(List<S> quizQuestions);
}
