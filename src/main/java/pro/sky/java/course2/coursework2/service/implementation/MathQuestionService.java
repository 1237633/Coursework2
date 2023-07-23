package pro.sky.java.course2.coursework2.service.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.QuestionRepository;
import pro.sky.java.course2.coursework2.service.QuestionService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Component
@Qualifier("MQS")
public class MathQuestionService implements QuestionService {
    private Random random;
    private final char[] symbols = {'+', '-', '*', '/'};

    public MathQuestionService() {
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        throw new MethodNotAllowedException("add", null);
    }

    @Override
    public Question add(Question question) {
        throw new MethodNotAllowedException("add", null);
    }

    @Override
    public Question remove(Question question) {
        throw new MethodNotAllowedException("remove", null);
    }

    @Override
    public Collection<Question> getAll() {
        throw new MethodNotAllowedException("getAll", null);
    }

    @Override
    public Question getRandomQuestion() {
        double[] operands = {generateNumber(), generateNumber()};
        int operation = random.nextInt(symbols.length);
        double result = 0;
        String question = operands[0] + " " + symbols[operation] + " " + operands[1];

        switch (operation) {
            case 0:
                result = operands[0] + operands[1];
                break;
            case 1:
                result = operands[0] - operands[1];
                break;
            case 2:
                result = operands[0] * operands[1];
                break;
            case 3:
                result = operands[0]/operands[1];
                break;
        }
        String answer = String.format("%.3f", result);
        return new Question(question, answer);
    }

    private int generateNumber() {
        return random.nextInt(1000); //only 3-digit
    }

    @Override
    public Question get(String questionText) {
        throw new MethodNotAllowedException("get", null);
    }

    @Override
    public int getSize() {
        throw new MethodNotAllowedException("getSize", null);
    }
}


