package quiz;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuizGenerator {
    private final List<Question> questions;

    public QuizGenerator(List<Question> questions) {
        this.questions = questions;
    }

    public <S extends QuizQuestion, T extends Quiz<S>> T generate(int maxQuestions, QuizFactory<S, T> quizFactory) {
        int numQuestionsToGet = Math.min(maxQuestions, questions.size());
        Collections.shuffle(questions);

        List<S> quizQuestions = questions.subList(0, numQuestionsToGet)
                .stream()
                .map(quizFactory::newQuizQuestion)
                .collect(Collectors.toList());

        return quizFactory.newQuiz(quizQuestions);
    }
}
