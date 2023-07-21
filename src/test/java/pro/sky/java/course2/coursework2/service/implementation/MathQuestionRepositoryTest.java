package pro.sky.java.course2.coursework2.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionRepositoryTest {

    private MathQuestionRepository mathQuestionRepository;
    Question test = new Question("Foo", "Bar");
    Question test2 = new Question("Bar", "Foo");
    Question test3 = new Question("A", "B");
    String question = "Foo";
    String answer = "Bar";
    Collection<Question> questions = new TreeSet<Question>(Set.of(test, test2, test3));

    @BeforeEach
    void setUp() {
        mathQuestionRepository = new MathQuestionRepository(new TreeSet<>());
    }

    @Test
    void add() {
        Assertions.assertEquals(test, mathQuestionRepository.add(test));
    }

    @Test
    void addByQA() {
        Assertions.assertEquals(test, mathQuestionRepository.add(question, answer));
    }

    @Test
    void removeReturnsQuestion() {
        Assertions.assertEquals(test, mathQuestionRepository.remove(test));
    }

    @Test
    void remove() {
        mathQuestionRepository.add(test);
        mathQuestionRepository.add(test3);
        mathQuestionRepository.add(test2);

        mathQuestionRepository.remove(test2);

        Collection<Question> expected = new HashSet<>(questions);
        expected.remove(test2);

        Assertions.assertIterableEquals(expected, mathQuestionRepository.getAll());
    }



    @Test
    void getAll() {
        mathQuestionRepository.add(test);
        mathQuestionRepository.add(test3);
        mathQuestionRepository.add(test2.getQuestion(), test2.getAnswer());
        Assertions.assertIterableEquals(questions, mathQuestionRepository.getAll());
    }

    @Test
    void addThrowsExceptionAtNullQA() {
        String testStr = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathQuestionRepository.add(testStr, "foo"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathQuestionRepository.add("foo", null));
    }

    @Test
    void addThrowsExceptionAtNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathQuestionRepository.add(null));
    }

    @Test
    void removeThrowsExceptionAtNull() {
        mathQuestionRepository.add(new Question("foo", "bar"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathQuestionRepository.remove(null));
    }

    @Test
    void getAllThrowsExceptionIfEmpty() {
        Assertions.assertThrows(BadRequestException.class, () -> mathQuestionRepository.getAll());
    }
}