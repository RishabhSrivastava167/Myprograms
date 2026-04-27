package quizplatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuizFrame extends JFrame {
    private List<String> questions;
    private List<String> answers;
    private List<String> userAnswers;
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JTextField answerField;
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining = 60; // 60 seconds timer

    public QuizFrame(String title) {
        setTitle(title);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Get quiz data
        String[] quizData = DatabaseManager.getQuiz(title);
        questions = List.of(quizData[0].split(";"));
        answers = List.of(quizData[1].split(";"));
        userAnswers = new ArrayList<>();

        questionLabel = new JLabel(questions.get(currentQuestionIndex));
        answerField = new JTextField();
        JButton nextButton = new JButton("Next");

        timerLabel = new JLabel("Time Remaining: " + timeRemaining + " seconds");
        add(timerLabel);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userAnswers.add(answerField.getText());
                answerField.setText("");
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    questionLabel.setText(questions.get(currentQuestionIndex));
                } else {
                    endQuiz();
                }
            }
        });

        add(questionLabel);
        add(answerField);
        add(nextButton);

        // Start the timer
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time Remaining: " + timeRemaining + " seconds");
                if (timeRemaining <= 0) {
                    endQuiz();
                }
            }
        });
        timer.start();

        setVisible(true);
    }

    private void endQuiz() {
        timer.stop();
        int score = new Quiz("Quiz", questions, answers).checkAnswers(userAnswers);
        JOptionPane.showMessageDialog(this, "Quiz Finished! Your Score: " + score + "/" + questions.size());
        System.exit(0);
    }
}
