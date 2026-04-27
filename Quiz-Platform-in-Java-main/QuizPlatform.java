package quizplatform;

public class QuizPlatform {
    public static void main(String[] args) {
        DatabaseManager.createTables(); // Create the user and quiz tables
        DatabaseManager.addSampleQuizzes(); // Add sample quizzes
        new LoginFrame(); // Show the login frame (ensure this class exists)
    }
}
