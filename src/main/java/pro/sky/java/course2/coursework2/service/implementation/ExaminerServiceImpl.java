package pro.sky.java.course2.coursework2.service.implementation;

import org.apache.el.stream.Stream;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.ExaminerService;
import pro.sky.java.course2.coursework2.service.QuestionService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if(amount > questionService.getSize()){
            throw new BadRequestException("Not enough questions!");
        }
        Collection<Question> result = new HashSet<Question>(amount);
        Question q;
        while (result.size() < amount){
            q = questionService.getRandomQuestion();
            if (q == null) {
                throw new IllegalArgumentException("Question can't be null!");
            }
            result.add(q);
        }
        return result;
    }
}
