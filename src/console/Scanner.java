package console;

public class Scanner {
    private static java.util.Scanner instance;

    private Scanner() {}

    public static java.util.Scanner getInstance() {
        if (instance == null) {
            instance = new java.util.Scanner(System.in);
        }

        return instance;
    }
}
