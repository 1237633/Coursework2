package pro.sky.java.course2.coursework2.service.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.QuestionService;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Service
public class JavaQuestionService implements QuestionService {

    private TreeSet<Question> questions;
    private Random random;
    private int size;


    public JavaQuestionService(TreeSet<Question> questions) {
        this.questions = questions;
        this.random = new Random();
        this.size = questions.size();
    }

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null) {
            throw new IllegalArgumentException("Question or answer can't be null");
        }

        Question result = new Question(question, answer);
        questions.add(result);
        size = questions.size();
        return result;
    }

    @Override
    public Question add(Question question) {
        if (question != null) {
            questions.add(question);
            size = questions.size();
            return question;
        }
        throw new IllegalArgumentException("Question can't be null");
    }

    @Override
    public Question remove(Question question) {
        if (question != null) {
            questions.remove(question);
            size = questions.size();
            return question;
        }
            throw new IllegalArgumentException("Question can't be null");
    }

    @Override
    public Collection<Question> getAll() {
        if (size < 1){
            throw new BadRequestException("No questions yet!");
        }
        TreeSet<Question> result = new TreeSet<>(questions); //Set.copyOf(questions);
        return result;
    }

    @Override
    public Question getRandomQuestion() {
        if (size < 1){
            throw new BadRequestException("No questions yet!");
        }
        return questions.stream().collect(Collectors.toList()).get(random.nextInt(questions.size()));
    }

    @Override
    public Question get(String questionText) {
        if (questionText == null) {
            throw new IllegalArgumentException("Question can't be null");
        }
        return questions.stream().filter(question -> question.getQuestion().equals(questionText)).findFirst().get();
    }

    @Override
    public int getSize() {
        return size;
    }


}
