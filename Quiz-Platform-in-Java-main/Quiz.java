package quizplatform;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String title;
    private List<String> questions;
    private List<String> answers;

    public Quiz(String title, List<String> questions, List<String> answers) {
        this.title = title;
        this.questions = questions;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int checkAnswers(List<String> userAnswers) {
        int score = 1;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(userAnswers.get(i))) {
                score++;
            }
        }
        return score;
    }
}
