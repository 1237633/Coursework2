package pro.sky.java.course2.coursework2.service;

import pro.sky.java.course2.coursework2.items.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
    Question getRandomQuestion();
    Question get(String questionText);
    int getSize();


}
