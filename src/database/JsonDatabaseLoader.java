package database;

import org.json.JSONArray;
import org.json.JSONObject;
import quiz.Answer;
import quiz.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class JsonDatabaseLoader implements DatabaseLoader {
    public String filePath = "data/java.json";

    @Override
    public List<Question> loadData() {
        List<Question> questions = new ArrayList<>();
        JSONArray data = loadFileContents();

        if (data == null) {
            return questions;
        }

        for (int i = 0; i < data.length(); i++) {
            JSONObject dataQuestion = data.getJSONObject(i);
            String title = dataQuestion.getString("title");
            List<Answer> answers = getAnswersFromQuestion(dataQuestion);

            questions.add(new Question(title, answers));
        }

        return questions;
    }

    private List<Answer> getAnswersFromQuestion(JSONObject dataQuestion) {
        JSONArray dataAnswers = dataQuestion.getJSONArray("answers");
        List<Answer> answers = new ArrayList<>();

        for (int i = 0; i < dataAnswers.length(); i++) {
            JSONObject dataAnswer = dataAnswers.getJSONObject(i);

            String title = dataAnswer.getString("title");
            boolean isCorrect = dataAnswer.getBoolean("isCorrect");
            String explanation = dataAnswer.getString("explanation");

            answers.add(new Answer(title, isCorrect, explanation));
        }

        return answers;
    }

    private JSONArray loadFileContents() {
        try {
            String contents = Files.readString(Path.of(filePath));

            return new JSONArray(contents);
        } catch (IOException ignored) {}

        return null;
    }
}
