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
import pro.sky.java.course2.coursework2.exceptions.BadRequestException;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.ExaminerService;

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

    @Mock
    private JavaQuestionService javaQuestionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        questions = new HashSet<Question>();
        testQuestion = new Question("foo", "bar");
        testQuestion2 = new Question("bar", "foo");
        questions.add(testQuestion);
    }

    @Test
    void getOneQuestion() {
        when(javaQuestionServiceMock.getSize()).thenReturn(1);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(testQuestion);
        Assertions.assertIterableEquals(questions, examinerService.getQuestions(1));

    }

    @Test
    void getQuestions() {
        questions.add(testQuestion2);
        when(javaQuestionServiceMock.getSize()).thenReturn(5);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(testQuestion).thenReturn(testQuestion2);
        Assertions.assertTrue(questions.containsAll(examinerService.getQuestions(2)));
    }

    @Test
    void throwsExpectionIfAmountIsTooHigh() {
        Assertions.assertThrows(BadRequestException.class, () -> examinerService.getQuestions(5));
    }

    @Test
    void nullQuestionBehaviorTest() {
        when(javaQuestionServiceMock.getSize()).thenReturn(1);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(1));
    }
}