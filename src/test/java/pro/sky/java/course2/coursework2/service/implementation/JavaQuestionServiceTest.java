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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    JavaQuestionRepository javaQuestionRepositoryMock;
    @InjectMocks
    JavaQuestionService javaQuestionService;

    Question test = new Question("Foo", "Bar");
    Question test2 = new Question("Bar", "Foo");
    String question = "Foo";
    String answer = "Bar";
    Collection<Question> questions = new TreeSet<Question>(Set.of(test, test2, new Question("A", "BE")));

    @BeforeEach
    void setUp() {

    }

    @Test
    void add() {
        when(javaQuestionRepositoryMock.add(question, answer)).thenReturn(test);
        Assertions.assertEquals(test, javaQuestionService.add(question, answer));
    }

    @Test
    void addQ() {
        when(javaQuestionRepositoryMock.add(test)).thenReturn(test);
        Assertions.assertEquals(test, javaQuestionService.add(test));
    }

    @Test
    void remove() {
        when(javaQuestionRepositoryMock.remove(test)).thenReturn(test);
        Assertions.assertEquals(test, javaQuestionService.remove(test));
    }

    @Test
    void getAll() {
        when(javaQuestionRepositoryMock.getAll()).thenReturn(new TreeSet<Question>(Set.of(test, test2)));
        Collection<Question> expected = new TreeSet<Question>(Set.of(test2, test));

        Assertions.assertIterableEquals(expected, javaQuestionService.getAll());


    }

    @Test
    void getRandomQuestion() {
        when(javaQuestionRepositoryMock.getSize()).thenReturn(2);
        when(javaQuestionRepositoryMock.getAll()).thenReturn(questions);

        Question actual = javaQuestionService.getRandomQuestion();

        Assertions.assertTrue(javaQuestionRepositoryMock.getAll().contains(actual));

    }

    @Test
    void get() {
        when(javaQuestionRepositoryMock.getAll()).thenReturn(questions);
        Assertions.assertEquals(test, javaQuestionService.get(question));

    }

    @Test
    void getThrowsExceptionAtNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.get(null));
    }

    @Test
    void getRandomThrowsExceptionIfEmpty() {
        Assertions.assertThrows(BadRequestException.class, () -> javaQuestionService.getRandomQuestion());
    }


}