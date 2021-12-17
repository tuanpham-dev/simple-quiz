package quiz;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuizGenerator {
    private final List<Question> questions;

    public QuizGenerator(List<Question> questions) {
        this.questions = questions;
    }

    public Quiz generate(int maxQuestions) {
        Collections.shuffle(questions);

        List<QuizQuestion> quizQuestions = questions.subList(0, Math.min(maxQuestions, questions.size()))
                .stream().map(QuizQuestion::new).collect(Collectors.toList());

        return new Quiz(quizQuestions);
    }
}
