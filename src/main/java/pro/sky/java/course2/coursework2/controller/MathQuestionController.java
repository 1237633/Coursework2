package pro.sky.java.course2.coursework2.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.coursework2.items.Question;
import pro.sky.java.course2.coursework2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math/")
public class MathQuestionController {

    QuestionService questionService;

    public MathQuestionController(@Qualifier("MQS") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("add")
    public Question addQuestion(@RequestParam String question, String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping
    public Collection<Question> getQuestions() {

        return questionService.getAll();
    }

    @GetMapping("delete")
    public Question removeQuestion(@RequestParam String questionText) {
        return questionService.remove(questionService.get(questionText));
    }
}
