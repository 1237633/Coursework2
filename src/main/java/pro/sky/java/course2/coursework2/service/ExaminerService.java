package pro.sky.java.course2.coursework2.service;

import pro.sky.java.course2.coursework2.items.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
