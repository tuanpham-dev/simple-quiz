package console;

import quiz.Question;
import quiz.QuizFactory;

import java.util.List;

public class ConsoleQuizFactory extends QuizFactory<ConsoleQuizQuestion, ConsoleQuiz> {
    @Override
    public ConsoleQuizQuestion newQuizQuestion(Question question) {
        return new ConsoleQuizQuestion(question);
    }

    @Override
    public ConsoleQuiz newQuiz(List<ConsoleQuizQuestion> quizQuestions) {
        return new ConsoleQuiz(quizQuestions);
    }
}
