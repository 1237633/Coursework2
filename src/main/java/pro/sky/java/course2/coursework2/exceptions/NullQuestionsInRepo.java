package pro.sky.java.course2.coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.LOOP_DETECTED)
public class NullQuestionsInRepo extends RuntimeException{
    public NullQuestionsInRepo() {
    }

    public NullQuestionsInRepo(String message) {
        super(message);
    }

    public NullQuestionsInRepo(String message, Throwable cause) {
        super(message, cause);
    }
}
