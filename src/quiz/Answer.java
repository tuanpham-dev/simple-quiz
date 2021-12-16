package quiz;

public class Answer {
    private final String title;
    private final boolean isCorrect;
    private final String explanation;

    public Answer(String title, boolean isCorrect, String explanation) {
        this.title = title;
        this.isCorrect = isCorrect;
        this.explanation = explanation;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getExplanation() {
        return explanation;
    }
}
