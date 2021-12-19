package console;

import quiz.Question;
import quiz.QuizFactory;

import java.util.List;

public class ConsoleQuizFactory extends QuizFactory<ConsoleQuizQuestion, ConsoleQuiz> {
    @Override
    public ConsoleQuizQuestion getQuizQuestion(Question question) {
        return new ConsoleQuizQuestion(question);
    }

    @Override
    public ConsoleQuiz getQuiz(List<ConsoleQuizQuestion> quizQuestions) {
        return new ConsoleQuiz(quizQuestions);
    }
}
