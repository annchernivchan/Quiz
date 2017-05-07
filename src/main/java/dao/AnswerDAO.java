package dao;

import entities.Answer;
import entities.Question;

import java.util.List;
import java.util.UUID;

public interface AnswerDAO {

    boolean add(Question question, Answer answer);
    boolean addAll(Question question, List<Answer> answers);
    boolean remove(UUID id);
    void removeAll(List<Answer> answers);
    boolean update(Answer newAnswer);
    void updateAll(List<Answer> newAnswers);
    List<Answer> getAllByQuestion(Question question);
    Answer getById(UUID id);

}
