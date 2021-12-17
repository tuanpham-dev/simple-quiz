package quiz;

import console.Console;

import java.util.HashSet;
import java.util.Set;

public class QuizQuestion {
    private final Question question;
    private final Set<Answer> userAnswers;
    private boolean isAnswered;

    public QuizQuestion(Question question) {
        question.shuffleAnswers();
        this.question = question;
        isAnswered = false;
        userAnswers = new HashSet<>();
    }

    public boolean isMultipleChoice() {
        return question.isMultipleChoice();
    }

    public int getAnswersCount() {
        return question.getAnswers().size();
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;

        if (!answered) {
            userAnswers.clear();
        }
    }

    public void addUserAnswer(int answerIndex) {
        Answer answer = question.getAnswers().get(answerIndex);
        userAnswers.add(answer);
    }

    public void printQuestion() {
        System.out.println();
        System.out.println(Console.format(question.getTitle()));
        int index = 1;

        for (Answer answer : question.getAnswers()) {
            System.out.printf("  %d) %s\n", index++, Console.format(answer.getTitle()));
        }
    }

    public boolean isCorrect() {
        return userAnswers.equals(question.getCorrectAnswers());
    }

    public void printSummary(int counter) {
        boolean isCorrect = isCorrect();
        String color = isCorrect ? Console.ANSI_GREEN : Console.ANSI_RED;

        System.out.println();
        System.out.printf(Console.colorize("%d. %s\n", color), counter, Console.format(question.getTitle(), color));
        System.out.println("> Your answer is:");

        for (Answer answer : userAnswers) {
            System.out.printf("  - %s\n", Console.format(answer.getTitle()));

            if (!answer.getExplanation().isBlank()) {
                System.out.print("  | ");
                System.out.println(Console.colorize(Console.format(answer.getExplanation(), Console.ANSI_YELLOW), Console.ANSI_YELLOW));
            }
        }

        if (!isCorrect) {
            System.out.println("> Correct answer is:");
            for (Answer answer : question.getCorrectAnswers()) {
                System.out.printf("  - %s\n", answer.getTitle());

                if (!answer.getExplanation().isBlank()) {
                    System.out.print("  | ");
                    System.out.println(Console.colorize(Console.format(answer.getExplanation(), Console.ANSI_CYAN), Console.ANSI_CYAN));
                }
            }
        }
    }
}
