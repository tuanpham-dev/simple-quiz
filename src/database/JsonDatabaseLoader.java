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
    // TODO: JSON file path should be dynamic
    public static final Path filePath = Path.of("data/java.json");

    @Override
    public List<Question> loadData() {
        List<Question> questions = new ArrayList<>();

        try {
            String contents = Files.readString(filePath);
            JSONArray data = new JSONArray(contents);

            for (int i = 0; i < data.length(); i++) {
                JSONObject dataQuestion = data.getJSONObject(i);
                String questionTitle = dataQuestion.getString("title");

                List<Answer> answers = new ArrayList<>();
                JSONArray dataAnswers = dataQuestion.getJSONArray("answers");

                for (int j = 0; j < dataAnswers.length(); j++) {
                    JSONObject dataAnswer = dataAnswers.getJSONObject(j);

                    String answerTitle = dataAnswer.getString("title");
                    boolean answerIsCorrect = dataAnswer.getBoolean("isCorrect");
                    String answerExplanation = dataAnswer.getString("explanation");

                    answers.add(new Answer(answerTitle, answerIsCorrect, answerExplanation));
                }

                questions.add(new Question(questionTitle, answers));
            }
        } catch (IOException e) {
            System.err.println("database.json file not found!");
            exit(1);
        }

        return questions;
    }
}
