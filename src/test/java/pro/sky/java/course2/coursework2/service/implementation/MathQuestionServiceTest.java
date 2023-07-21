package pro.sky.java.course2.coursework2.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    @Mock
    MathQuestionRepository mathQuestionRepositoryMock;
    @InjectMocks
    MathQuestionService mathQuestionService;

    Question test = new Question("Foo", "Bar");
    Question test2 = new Question("Bar", "Foo");
    String question = "Foo";
    String answer = "Bar";
    Collection<Question> questions = new TreeSet<Question>(Set.of(test, test2, new Question("A", "BE")));


    @Test
    void add() {
        when(mathQuestionRepositoryMock.add(question, answer)).thenReturn(test);
        Assertions.assertEquals(test, mathQuestionService.add(question, answer));
    }

    @Test
    void addQ() {
        when(mathQuestionRepositoryMock.add(test)).thenReturn(test);
        Assertions.assertEquals(test, mathQuestionService.add(test));
    }

    @Test
    void remove() {
        when(mathQuestionRepositoryMock.remove(test)).thenReturn(test);
        Assertions.assertEquals(test, mathQuestionService.remove(test));
    }

    @Test
    void getAll() {
        when(mathQuestionRepositoryMock.getAll()).thenReturn(new TreeSet<Question>(Set.of(test, test2)));
        Collection<Question> expected = new TreeSet<Question>(Set.of(test2, test));

        Assertions.assertIterableEquals(expected, mathQuestionService.getAll());


    }

    @Test
    void getRandomQuestion() {
        when(mathQuestionRepositoryMock.getSize()).thenReturn(2);
        when(mathQuestionRepositoryMock.getAll()).thenReturn(questions);

        Question actual = mathQuestionService.getRandomQuestion();

        Assertions.assertTrue(mathQuestionRepositoryMock.getAll().contains(actual));

    }

    @Test
    void get() {
        when(mathQuestionRepositoryMock.getAll()).thenReturn(questions);
        Assertions.assertEquals(test, mathQuestionService.get(question));

    }

    @Test
    void getThrowsExceptionAtNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathQuestionService.get(null));
    }

    @Test
    void getRandomThrowsExceptionIfEmpty() {
        Assertions.assertThrows(BadRequestException.class, () -> mathQuestionService.getRandomQuestion());
    }

}