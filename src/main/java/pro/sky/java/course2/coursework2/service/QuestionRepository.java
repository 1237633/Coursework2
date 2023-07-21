package pro.sky.java.course2.coursework2.service;

import pro.sky.java.course2.coursework2.items.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);

    Question add(String question, String answer);

    Question remove(Question question);

    Collection<Question> getAll();

    int getSize();

}
