
package pro.sky.java.course2.coursework2.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.exceptions.NullQuestionsInRepo;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.ExaminerService;
import pro.sky.java.course2.coursework2.service.QuestionService;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private Question testQuestion;
    private Question testQuestion2;
    private Collection<Question> questions;
    private int amount = 2;


    @Mock
    private List<QuestionService> questionServiceListMock;
    @Mock
    private JavaQuestionService javaQuestionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl examinerService;


    @BeforeEach
    void setUp() {
        testQuestion = new Question("foo", "bar");
        testQuestion2 = new Question("bar", "foo");
        questions = new HashSet<Question>();
        questions.add(testQuestion);

        when(questionServiceListMock.size()).thenReturn(2);
        when(questionServiceListMock.get(anyInt())).thenReturn(javaQuestionServiceMock);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(testQuestion).thenReturn(testQuestion2);
    }

    @Test
    void getOneQuestion() {
        Assertions.assertIterableEquals(questions, examinerService.getQuestions(1));
    }

    @Test
    void getQuestionsSize() {

        questions.add(testQuestion2);
        Assertions.assertEquals(amount, examinerService.getQuestions(amount).size());
    }

    @Test
    void getQuestions() {

        questions.add(testQuestion2);
        Assertions.assertTrue(questions.containsAll(examinerService.getQuestions(amount)));
    }

    @Test
    void nullLoopBehaviorTest() {
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(null);
        Assertions.assertThrows(NullQuestionsInRepo.class, () -> examinerService.getQuestions(1));
    }

    @Test
    void nullQuestionTryAgainTest() {

        questions.add(testQuestion2);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(testQuestion).thenReturn(null).thenReturn(testQuestion2);
        Assertions.assertTrue(questions.containsAll(examinerService.getQuestions(amount)));
    }
}
