package console;

import quiz.Quiz;

import java.util.List;

public class ConsoleQuiz extends Quiz<ConsoleQuizQuestion> {
    private final boolean IS_BACK_TO_MAIN = true;
    private final java.util.Scanner scanner;
    private boolean isDone;

    public ConsoleQuiz(List<ConsoleQuizQuestion> questions) {
        super(questions);

        scanner = Scanner.getInstance();
        isDone = false;
    }

    @Override
    public void start() {
        fixGetLine();

        while (true) {
            if (!isDone) {
                promptQuestion();
            } else {
                if (promptSummary() == IS_BACK_TO_MAIN) {
                    break;
                }
            }
        }
    }

    private void fixGetLine() {
        scanner.nextLine();
    }

    private void promptQuestion() {
        printStatus();
        printQuestion();
        printNavigationHint();
        promptAnswer();
    }

    private void printStatus() {
        Console.clearScreen();
        System.out.printf("\nQuestion (%d/%d)\n", getCurrentQuestionIndex() + 1, getTotalQuestions());
        System.out.println("-------------------------");
    }

    private void printQuestion() {
        ConsoleQuizQuestion question = getCurrentQuestion();
        question.printQuestion();
    }

    private void printNavigationHint() {
        ConsoleQuizQuestion question = getCurrentQuestion();
        System.out.printf("\n(blank - next question, 0 - previous question, 1-%d - answer/update answer)\n", question.getTotalAnswers());
    }

    private void promptAnswer() {
        ConsoleQuizQuestion question = getCurrentQuestion();

        while (true) {
            System.out.print("Your answer is: ");
            String answer = scanner.nextLine();

            if (isAnswerDone(answer)) {
                nextQuestionOrFinish();
                break;
            }

            int choice = Integer.parseInt(answer);

            if (choice <= 0) {
                previousQuestion();
                break;
            }

            if (choice <= (question.getTotalAnswers())) {
                if (question.isAnswered()) {
                    question.setAnswered(false);
                }

                question.addUserAnswer(choice - 1);

                if (!question.isMultipleChoice()) {
                    question.setAnswered(true);
                    nextQuestionOrFinish();

                    break;
                }
            }
        }
    }

    private boolean isAnswerDone(String answer) {
        return answer.isBlank();
    }

    private void nextQuestionOrFinish() {
        if (nextQuestion() == IS_ALREADY_LAST) {
            isDone = true;
        }
    }

    private boolean promptSummary() {
        final int VIEW_SUMMARY = 1;

        printSummaryMessageAndScore();
        int action = promptSummaryMenu();

        if (action == VIEW_SUMMARY) {
            printSummary();
            return !IS_BACK_TO_MAIN;
        }

        return IS_BACK_TO_MAIN;
    }

    private void printSummaryMessageAndScore() {
        double score = getScore();
        String message;
        String color;

        if (Double.compare(score, 1) == 0) {
            message = "Perfect! Keep up the good work!";
            color = Console.ANSI_GREEN;
        } else if (score >= 0.8) {
            message = "You're good! Just one more step to be a ninja.";
            color = Console.ANSI_PURPLE;
        } else if (score >= 0.5) {
            message = "Not bad! Keep trying harder!";
            color = Console.ANSI_YELLOW;
        } else {
            message = "Hmm...You lost because you didn't win.";
            color = Console.ANSI_RED;
        }

        Console.clearScreen();
        System.out.println();
        System.out.println(Console.colorize(message, color));
        System.out.printf("Your score is: " + Console.colorize("%d%%", color) + "\n\n", Math.round(score * 100));
    }

    private int promptSummaryMenu() {
        System.out.println("What do you want to do?");
        System.out.println("  1) View Summary");
        System.out.println("  0) Exit to Main Menu");

        System.out.print("\nI want to: ");

        return scanner.nextInt();
    }

    private void printSummary() {
        printSummaryHeader();
        printSummaryDetails();
        waitForEnter();
    }

    private void printSummaryHeader() {
        Console.clearScreen();
        System.out.println("\nSummary");
        System.out.println("-------------------------");
    }

    private void printSummaryDetails() {
        int listCounter = 1;

        for (ConsoleQuizQuestion question: getQuestions()) {
            question.printSummary(listCounter++);
        }
    }

    private void waitForEnter() {
        fixGetLine();

        System.out.println("\nPress Enter to go back...");
        scanner.nextLine();
    }
}
