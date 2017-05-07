package dao;

import entities.Answer;
import entities.Question;
import entities.QuestionType;

import java.util.List;
import java.util.UUID;

public interface QuestionDAO {

    boolean add(Question question);
    boolean remove(UUID id);
    boolean update(Question question);
    List<Question> getAll();
    Question getById(UUID id);
    List<Answer> getAnswersForQuestion(Question question);
    boolean verifyQuestion(Question question);
    List<String> getAllTypes();

}
