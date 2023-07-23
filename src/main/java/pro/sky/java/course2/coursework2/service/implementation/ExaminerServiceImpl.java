package pro.sky.java.course2.coursework2.service.implementation;

import org.apache.el.stream.Stream;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.exceptions.NullQuestionsInRepo;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.ExaminerService;
import pro.sky.java.course2.coursework2.service.QuestionRepository;
import pro.sky.java.course2.coursework2.service.QuestionService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    @Autowired
    private List<QuestionService> questionServices;
    private Random random;
    int iterator;

    public ExaminerServiceImpl() {
        this.random = new Random();
        this.iterator = 0;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> result = new HashSet<Question>(amount);
        int type;

        while (result.size() < amount) {
            type = random.nextInt(questionServices.size());
            result.add(getQuestion(questionServices.get(type)));
        }
        return result;
    }

    private Question getQuestion(QuestionService questionService) {
        Question question = questionService.getRandomQuestion();
        if (question == null) {
            iterator++;
            if (iterator < 10) {
                System.err.println("Got null question. Trying again");
                return getQuestion(questionService);
            } else {
                throw new NullQuestionsInRepo("Too much null questions detected in " + questionService + " repository");
            }
        }
        iterator = 0;
        return question;
    }
}
