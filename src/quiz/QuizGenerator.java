package quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizGenerator {
    private final List<Question> questions;

    public QuizGenerator(List<Question> questions) {
        this.questions = questions;
    }

    public Quiz generate(int maxQuestions) {
        Collections.shuffle(questions);

        List<QuizQuestion> quizQuestions = new ArrayList<>();
        int i = 0;

        for (Question question : questions) {
            if (++i > questions.size()) {
                break;
            }

            quizQuestions.add(new QuizQuestion(question));
        }

        return new Quiz(quizQuestions);
    }
}
