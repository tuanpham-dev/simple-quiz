import console.Console;
import database.DatabaseLoader;
import database.JsonDatabaseLoader;
import quiz.Quiz;
import quiz.QuizGenerator;

import java.util.Scanner;

public class SimpleQuiz {
    public static void main(String[] args) {
        DatabaseLoader databaseLoader = new JsonDatabaseLoader();
        QuizGenerator quizGenerator = new QuizGenerator(databaseLoader.loadData());

        try {
            promptMenu(quizGenerator);
        } catch (Exception ignored) {}
    }

    public static void promptMenu(QuizGenerator quizGenerator) {
        Scanner scanner = console.Scanner.getInstance();
        int action;
        int maxQuestions = 5;

        do {
            Console.clearScreen();
            System.out.println("\n-------------------------");
            System.out.println("Welcome to Simple Quiz!");
            System.out.println("-------------------------\n\n");

            System.out.println("What do you want to do?");
            System.out.println("  1) Take Quiz");
            System.out.println("  0) Quit");
            System.out.print("\nI want to: ");

            action = scanner.nextInt();

            if (action == 1) {
                do {
                    System.out.println((maxQuestions > 0 ? "\nCool! " : "\n") + "How many questions do you want to take?");
                    System.out.print("I want to take: ");

                    maxQuestions = scanner.nextInt();

                    if (maxQuestions <= 0) {
                        System.out.println(Console.colorize("Oops! You need to take at least one question.", Console.ANSI_RED));
                    }
                } while (maxQuestions <= 0);

                Quiz quiz = quizGenerator.generate(maxQuestions);
                quiz.start();
            } else if (action == 0) {
                System.out.println(Console.colorize("\nBye bye! Have a nice day friend!", Console.ANSI_GREEN));
            }
        } while (action != 0);
    }
}
