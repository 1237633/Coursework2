package pro.sky.java.course2.coursework2.service.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.QuestionRepository;
import pro.sky.java.course2.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
@Service
@Qualifier("MQS")
public class MathQuestionService implements QuestionService {
    private Random random;
    private QuestionRepository questionRepository;

    public MathQuestionService(@Qualifier("MQR") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return questionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionRepository.getSize() < 1) {
            throw new BadRequestException("No questions yet!");
        }
        return questionRepository.getAll().stream().collect(Collectors.toList()).get(random.nextInt(questionRepository.getSize()));
    }

    @Override
    public Question get(String questionText) {
        if (questionText == null) {
            throw new IllegalArgumentException("Question can't be null");
        }
        return questionRepository.getAll().stream().filter(question -> question.getQuestion().equals(questionText)).findFirst().get();
    }

    @Override
    public int getSize() {
        return questionRepository.getSize();
    }
}
