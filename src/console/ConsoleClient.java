package console;

import quiz.Client;
import quiz.Question;
import quiz.QuizFactory;

import java.util.List;

public class ConsoleClient extends Client {
    private final QuizFactory<ConsoleQuizQuestion, ConsoleQuiz> quizFactory;
    private final java.util.Scanner scanner;

    public ConsoleClient(List<Question> questions) {
        super(questions);

        quizFactory = new ConsoleQuizFactory();
        scanner = Scanner.getInstance();
    }

    @Override
    public void run() {
        final int TAKE_QUIZ = 1;
        final int QUIT = 0;

        int action;

        do {
            printWelcomeMessage();

            action = promptMenu();

            if (action == TAKE_QUIZ) {
                int maxQuestions = promptNumberOfQuestionsForQuiz();
                ConsoleQuiz quiz = generate(maxQuestions, quizFactory);

                quiz.start();
            }
        } while (action != QUIT);

        printGoodbyeMessage();
    }

    private void printWelcomeMessage() {
        System.out.println("\n-------------------------");
        System.out.println("Welcome to Simple Quiz!");
        System.out.println("-------------------------\n\n");
    }

    private int promptMenu() {
        System.out.println("What do you want to do?");
        System.out.println("  1) Take Quiz");
        System.out.println("  2) Quit");
        System.out.print("\nI want to: ");

        return scanner.nextInt();
    }

    private int promptNumberOfQuestionsForQuiz() {
        int maxQuestions = 5;

        do {
            System.out.println("\n" + (maxQuestions > 0 ? "Cool!" : "") + "How many questions do you want to take?");
            System.out.print("I want to take: ");

            maxQuestions = scanner.nextInt();

            if (maxQuestions <= 0) {
                System.out.println(Console.colorize("Oops! You need to take at least one question.", Console.ANSI_RED));
            }
        } while (maxQuestions <= 0);

        return maxQuestions;
    }

    private void printGoodbyeMessage() {
        System.out.println(Console.colorize("Bye bye! Have a nice day my friend!", Console.ANSI_GREEN));
    }
}
