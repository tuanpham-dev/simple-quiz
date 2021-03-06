package quiz;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {
    private final String title;
    private final List<Answer> answers;
    private final Set<Answer> correctAnswers;

    public Question(String title, List<Answer> answers) {
        this.title = title;
        this.answers = answers;

        correctAnswers = new HashSet<>();
        answers.stream().filter(Answer::isCorrect).forEach(correctAnswers::add);
    }

    public String getTitle() {
        return title;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Set<Answer> getCorrectAnswers() {
        return correctAnswers;
    }

    public boolean isMultipleChoice() {
        return correctAnswers.size() > 1;
    }

    public void shuffleAnswers() {
        Collections.shuffle(answers);
    }
}
