package quiz;

import java.util.List;

public abstract class Client {
    QuizGenerator quizGenerator;

    public Client(List<Question> questions) {
        this.quizGenerator = new QuizGenerator(questions);
    }

    protected <S extends QuizQuestion,T extends Quiz<S>> T generate(int maxQuestions, QuizFactory<S, T> quizFactory) {
        return quizGenerator.generate(maxQuestions, quizFactory);
    }

    public abstract void run();
}
