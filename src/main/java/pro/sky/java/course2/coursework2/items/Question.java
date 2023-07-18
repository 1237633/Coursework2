package pro.sky.java.course2.coursework2.items;

import java.util.Objects;

public class Question implements Comparable<Question>{
    String question;
    String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return getQuestion().equals(question1.getQuestion()) && getAnswer().equals(question1.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getAnswer());
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public int compareTo(Question q) {
        if (hashCode() > q.hashCode()) {
            return 1;
        }
        else if (hashCode() < q.hashCode()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
