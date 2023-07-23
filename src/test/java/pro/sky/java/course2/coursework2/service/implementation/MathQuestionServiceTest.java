package pro.sky.java.course2.coursework2.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MethodNotAllowedException;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    MathQuestionService mathQuestionService;

    Question test = new Question("Foo", "Bar");
    Question test2 = new Question("Bar", "Foo");
    String question = "Foo";
    String answer = "Bar";
    Collection<Question> questions = new TreeSet<Question>(Set.of(test, test2, new Question("A", "BE")));


    public MathQuestionServiceTest() {
        this.mathQuestionService = new MathQuestionService();
    }

    @Test
    void add() {
        Assertions.assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add(test));
    }

    @Test
    void addQ() {
        Assertions.assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add(question, answer));
    }

    @Test
    void remove() {
        Assertions.assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.remove(test));
    }

    @Test
    void getAll() {
        Assertions.assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.getAll());
    }

    @Test
    void getRandomQuestion() {
        System.out.println(mathQuestionService.getRandomQuestion());
        Assertions.assertInstanceOf(Question.class, mathQuestionService.getRandomQuestion());
    }

    @Test
    void get() {
        Assertions.assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.get(question));;

    }

}