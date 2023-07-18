package pro.sky.java.course2.coursework2.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService(new TreeSet<Question>());
    }

    @Test
    void add() {
        javaQuestionService.add("Kupi slona!", "Nyet!");
        Set<Question> expected = new TreeSet<Question>();
        expected.add(new Question("Kupi slona!", "Nyet!"));
        Assertions.assertIterableEquals(expected, javaQuestionService.getAll());
    }

    @Test
    void addQ() {
        Question test = new Question("a", "be");
        javaQuestionService.add(test);
        Set<Question> expected = new TreeSet<Question>();
        expected.add(test);
        Assertions.assertIterableEquals(expected, javaQuestionService.getAll());
    }

    @Test
    void remove() {
        Question test = new Question("foo", "foo");
        Question test2 = new Question("bar", "bar");

        javaQuestionService.add(test);
        javaQuestionService.add(test2);
        javaQuestionService.remove(test2);

        Set<Question> expected = new TreeSet<Question>();
        expected.add(test);
        Assertions.assertIterableEquals(expected, javaQuestionService.getAll());
    }

    @Test
    void getAll() {
        Question test = new Question("foo", "foo");
        Question test2 = new Question("bar", "bar");

        javaQuestionService.add(test);
        javaQuestionService.add(test2);

        Collection<Question> expected = new TreeSet<Question>();
        expected.add(test2);
        expected.add(test);

        Assertions.assertIterableEquals(expected, javaQuestionService.getAll());


    }

    @Test
    void getRandomQuestion() {
        Question test = new Question("foo", "foo");
        Question test2 = new Question("bar", "bar");

        javaQuestionService.add(test);
        javaQuestionService.add(test2);

        Question actual = javaQuestionService.getRandomQuestion();
        Question expected = javaQuestionService.get(actual.getQuestion());

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void get() {
        Question expected = new Question("foo", "bar");

        javaQuestionService.add(expected);

        Assertions.assertEquals(expected, javaQuestionService.get("foo"));

    }

    @Test
    void getSize() {
        Question test = new Question("foo", "foo");
        Question test2 = new Question("bar", "bar");

        javaQuestionService.add(test);
        javaQuestionService.add(test2);

        Assertions.assertEquals(2, javaQuestionService.getSize());
    }

    @Test
    void addThrowsExceptionAtNullQA() {
        String testStr = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.add(testStr, "foo"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.add("foo", null));
    }

    @Test
    void addThrowsExceptionAtNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.add(null));
    }

    @Test
    void removeThrowsExceptionAtNull() {
        javaQuestionService.add(new Question("foo", "bar"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.remove(null));
    }

    @Test
    void getAllThrowsExceptionIfEmpty() {
        Assertions.assertThrows(BadRequestException.class, () -> javaQuestionService.getAll());
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