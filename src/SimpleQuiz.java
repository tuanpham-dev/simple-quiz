import console.ConsoleClient;
import database.DatabaseLoader;
import database.JsonDatabaseLoader;
import quiz.Client;
import quiz.Question;

import java.util.List;

public class SimpleQuiz {
    public static void main(String[] args) {
        DatabaseLoader databaseLoader = getDatabaseLoader();
        List<Question> questions = databaseLoader.loadData();
        Client client = getClient(questions);

        try {
            client.run();
        } catch (Exception ignored) {}
    }

    private static DatabaseLoader getDatabaseLoader() {
        DatabaseLoader databaseLoader;

        databaseLoader = new JsonDatabaseLoader();

        return databaseLoader;
    }

    private static Client getClient(List<Question> questions) {
        Client client;

        client = new ConsoleClient(questions);

        return client;
    }
}
