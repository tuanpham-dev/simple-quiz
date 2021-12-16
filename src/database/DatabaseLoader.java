package database;

import quiz.Question;

import java.util.List;

public interface DatabaseLoader {
    List<Question> loadData();
}
