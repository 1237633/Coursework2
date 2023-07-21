package pro.sky.java.course2.coursework2.service.implementation;

import org.apache.el.stream.Stream;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.ExaminerService;
import pro.sky.java.course2.coursework2.service.QuestionRepository;
import pro.sky.java.course2.coursework2.service.QuestionService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private QuestionService java;
    private QuestionService math;
    private Random random;

    public ExaminerServiceImpl(@Qualifier("JQS") QuestionService java, @Qualifier("MQS") QuestionService math) {
        this.java = java;
        this.math = math;
        this.random = new Random();
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > (java.getSize() + math.getSize())) {
            throw new BadRequestException("Not enough questions!");
        }

        Collection<Question> result = new HashSet<Question>(amount);
        Question q;


        while (result.size() < amount) {

            switch (random.nextInt(2)) {
                case 0:
                    q = java.getRandomQuestion();
                    if (q == null) {
                        throw new IllegalArgumentException("Question can't be null!");
                    }
                    result.add(q);
                    break;

                case 1:
                    q = math.getRandomQuestion();
                    if (q == null) {
                        throw new IllegalArgumentException("Question can't be null!");
                    }
                    result.add(q);
                    break;
            }
        }

        return result;
    }
}
