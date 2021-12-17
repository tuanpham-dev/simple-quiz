package console;

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String colorize(String text, String color) {
        return color + text + ANSI_RESET;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String format(String text, String previousColor) {
        String[] lines = text.split("\\r?\\n");
        StringBuilder output = new StringBuilder();
        boolean isInsideCode = false;
        int lineNumber = 1;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (isInsideCode) {
                if (line.compareTo("[code]") == 0) {
                    return text;
                } else if (line.compareTo("[/code]") == 0) {
                    if (!previousColor.isEmpty()) {
                        output.append(previousColor);
                    }

                    isInsideCode = false;
                } else {
                    if (!(i == 1 && lines[0].compareTo("[code]") == 0)) {
                        output.append('\n');
                    }

                    output.append(String.format(Console.colorize("%02d |", Console.ANSI_YELLOW)
                            + Console.colorize("  %s", Console.ANSI_CYAN), lineNumber++, line));
                }
            } else {
                if (line.compareTo("[code]") == 0) {
                    isInsideCode = true;
                    lineNumber = 1;
                } else if (line.compareTo("[/code]") == 0) {
                    return text;
                } else {
                    if (i > 0) {
                        output.append('\n');
                    }

                    output.append(line);
                }
            }
        }

        return output.toString();
    }

    public static String format(String text) {
        return format(text, "");
    }
}
