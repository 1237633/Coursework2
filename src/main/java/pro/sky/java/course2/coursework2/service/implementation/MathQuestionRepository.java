package pro.sky.java.course2.coursework2.service.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.QuestionRepository;

import java.util.Collection;
import java.util.TreeSet;

@Repository
@Qualifier("MQR")
public class MathQuestionRepository implements QuestionRepository {
    private int size;
    private Collection<Question> questions;

    public MathQuestionRepository(Collection<Question> questions) {
        this.questions = questions;
        this.size = questions.size();
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
        if (size < 1) {
            throw new BadRequestException("No questions yet!");
        }
        TreeSet<Question> result = new TreeSet<>(questions);
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }
}
