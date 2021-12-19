package console;

import quiz.Answer;
import quiz.Question;
import quiz.QuizQuestion;

public class ConsoleQuizQuestion extends QuizQuestion {
    public ConsoleQuizQuestion(Question question) {
        super(question);
    }

    public void printQuestion() {
        System.out.println();
        System.out.println(Console.format(getTitle()));

        int listCounter = 1;

        for (Answer answer : getAnswers()) {
            System.out.printf("  %d) %s\n", listCounter++, Console.format(answer.getTitle()));
        }
    }

    public void printSummary(int listCounter) {
        boolean isCorrect = isCorrect();
        String color = isCorrect ? Console.ANSI_GREEN : Console.ANSI_RED;

        System.out.println();
        System.out.printf(Console.colorize("%d. %s\n", color), listCounter, Console.format(getTitle(), color));
        System.out.println("> Your answer is:");

        for (Answer answer : getUserAnswers()) {
            System.out.printf("  - %s\n", Console.format(answer.getTitle()));

            if (!answer.getExplanation().isBlank()) {
                System.out.print("  | ");
                System.out.println(Console.colorize(Console.format(answer.getExplanation(), Console.ANSI_YELLOW), Console.ANSI_YELLOW));
            }
        }

        if (!isCorrect) {
            System.out.println("> Correct answer is:");
            for (Answer answer : getCorrectAnswers()) {
                System.out.printf("  - %s\n", answer.getTitle());

                if (!answer.getExplanation().isBlank()) {
                    System.out.print("  | ");
                    System.out.println(Console.colorize(Console.format(answer.getExplanation(), Console.ANSI_CYAN), Console.ANSI_CYAN));
                }
            }
        }
    }
}
