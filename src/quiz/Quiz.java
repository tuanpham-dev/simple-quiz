package quiz;

import console.Console;

import java.util.List;
import java.util.Scanner;

public class Quiz {
    private final List<QuizQuestion> questions;
    private int questionIndex;
    private boolean isDone;
    private final Scanner scanner;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        questionIndex = 0;
        isDone = false;
        scanner = console.Scanner.getInstance();
    }

    private QuizQuestion currentQuestion() {
        return questions.get(questionIndex);
    }

    private boolean nextQuestion() {
        if (questionIndex >= questions.size() - 1) {
            return false;
        }

        questionIndex++;

        return true;
    }

    private boolean previousQuestion() {
        if (questionIndex <= 0) {
            return false;
        }

        questionIndex--;

        return true;
    }

    private double getScore() {
        int correctAnswersCount = (int) questions.stream().filter(QuizQuestion::isCorrect).count();

        return (double) correctAnswersCount / questions.size();
    }

    private void printSummary() {
        scanner.nextLine();
        Console.clearScreen();
        System.out.println("\nSummary");
        System.out.println("-------------------------");
        int index = 0;
        for (QuizQuestion question : questions) {
            question.printSummary(++index);
        }

        System.out.print("\nPress ENTER to go back...");
        scanner.nextLine();
    }

    private boolean promptSummaryMenu() {
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
        System.out.println("What do you want to do?");
        System.out.println("  1) View Summary");
        System.out.println("  0) Exit to Main Menu");

        System.out.print("\nI want to: ");
        int action = scanner.nextInt();

        if (action == 1) {
            printSummary();
            return false;
        } else {
            return true;
        }
    }

    public void promptQuestion() {
        QuizQuestion currentQuestion = currentQuestion();

        Console.clearScreen();
        System.out.printf("\nQuestion (%d/%d)\n", questionIndex + 1, questions.size());
        System.out.println("-------------------------");
        currentQuestion.printQuestion();
        System.out.println("\n(blank - next question, 0 - previous question, 1-4 - answer/update answer)");

        while (true) {
            System.out.print("Your answer is: ");

            String answer = scanner.nextLine();

            if (answer.compareTo("") == 0) {
                if (!nextQuestion()) {
                    isDone = true;
                }

                break;
            } else {
                int choice = Integer.parseInt(answer);

                if (choice <= 0) {
                    previousQuestion();
                    break;
                } else {
                    if (choice <= currentQuestion.getAnswersCount()) {
                        if (currentQuestion.isAnswered()) {
                            currentQuestion.setAnswered(false);
                        }

                        currentQuestion.addUserAnswer(choice - 1);

                        if (!currentQuestion.isMultipleChoice()) {
                            currentQuestion.setAnswered(true);

                            if (!nextQuestion()) {
                                isDone = true;
                            }

                            break;
                        }
                    }
                }
            }
        }
    }

    public void start() {
        scanner.nextLine();

        while (true) {
            if (isDone) {
                if (promptSummaryMenu()) {
                    break;
                }
            } else {
                promptQuestion();
            }
        }
    }
}
