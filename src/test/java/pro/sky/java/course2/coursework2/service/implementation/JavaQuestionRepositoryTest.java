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

class JavaQuestionRepositoryTest {

    private JavaQuestionRepository javaQuestionRepository;
    Question test = new Question("Foo", "Bar");
    Question test2 = new Question("Bar", "Foo");
    Question test3 = new Question("A", "B");
    String question = "Foo";
    String answer = "Bar";
    Collection<Question> questions = new TreeSet<Question>(Set.of(test, test2, test3));

    @BeforeEach
    void setUp() {
        javaQuestionRepository = new JavaQuestionRepository(new TreeSet<>());
    }

    @Test
    void add() {
        Assertions.assertEquals(test, javaQuestionRepository.add(test));
    }

    @Test
    void addByQA() {
        Assertions.assertEquals(test, javaQuestionRepository.add(question, answer));
    }

    @Test
    void removeReturnsQuestion() {
        Assertions.assertEquals(test, javaQuestionRepository.remove(test));
    }

    @Test
    void remove() {
        javaQuestionRepository.add(test);
        javaQuestionRepository.add(test3);
        javaQuestionRepository.add(test2);

        javaQuestionRepository.remove(test2);

        Collection<Question> expected = new HashSet<>(questions);
        expected.remove(test2);

        Assertions.assertIterableEquals(expected, javaQuestionRepository.getAll());
    }



    @Test
    void getAll() {
        javaQuestionRepository.add(test);
        javaQuestionRepository.add(test3);
        javaQuestionRepository.add(test2.getQuestion(), test2.getAnswer());
        Assertions.assertIterableEquals(questions, javaQuestionRepository.getAll());
    }

    @Test
    void addThrowsExceptionAtNullQA() {
        String testStr = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionRepository.add(testStr, "foo"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionRepository.add("foo", null));
    }

    @Test
    void addThrowsExceptionAtNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionRepository.add(null));
    }

    @Test
    void removeThrowsExceptionAtNull() {
        javaQuestionRepository.add(new Question("foo", "bar"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionRepository.remove(null));
    }

    @Test
    void getAllThrowsExceptionIfEmpty() {
        Assertions.assertThrows(BadRequestException.class, () -> javaQuestionRepository.getAll());
    }

}