package quizplatform;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:quiz_platform.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }
    
    public static void createTables() {
        String userTable = "CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, password TEXT)";
        String quizTable = "CREATE TABLE IF NOT EXISTS quizzes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, questions TEXT, answers TEXT)";
        
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(quizTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    public static void addQuiz(String title, String questions, String answers) {
        String sql = "INSERT INTO quizzes (title, questions, answers) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, questions);
            stmt.setString(3, answers);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] getQuizzes() {
        String sql = "SELECT title FROM quizzes";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            StringBuilder titles = new StringBuilder();
            while (rs.next()) {
                titles.append(rs.getString("title")).append(",");
            }
            return titles.toString().split(",");
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static String[] getQuiz(String title) {
        String sql = "SELECT questions, answers FROM quizzes WHERE title = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[]{rs.getString("questions"), rs.getString("answers")};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addSampleQuizzes() {
        // Sample quizzes
        String[] quizTitles = {
            "Addition Quiz",
            "Subtraction Quiz",
            "Multiplication Quiz",
            "Division Quiz"
        };

        // Questions and answers for each quiz
        String[][] questions = {
            {
                "{\"question\":\"What is 2 + 2?\",\"answer1\":\"3\",\"answer2\":\"4\",\"correctAnswer\":\"4\"}",
                "{\"question\":\"What is 5 + 7?\",\"answer1\":\"12\",\"answer2\":\"13\",\"correctAnswer\":\"12\"}"
            },
            {
                "{\"question\":\"What is 5 - 3?\",\"answer1\":\"2\",\"answer2\":\"3\",\"correctAnswer\":\"2\"}",
                "{\"question\":\"What is 10 - 4?\",\"answer1\":\"5\",\"answer2\":\"6\",\"correctAnswer\":\"6\"}"
            },
            {
                "{\"question\":\"What is 3 x 4?\",\"answer1\":\"12\",\"answer2\":\"10\",\"correctAnswer\":\"12\"}",
                "{\"question\":\"What is 6 x 7?\",\"answer1\":\"42\",\"answer2\":\"40\",\"correctAnswer\":\"42\"}"
            },
            {
                "{\"question\":\"What is 8 ÷ 2?\",\"answer1\":\"4\",\"answer2\":\"5\",\"correctAnswer\":\"4\"}",
                "{\"question\":\"What is 15 ÷ 3?\",\"answer1\":\"3\",\"answer2\":\"5\",\"correctAnswer\":\"5\"}"
            }
        };

        // Insert quizzes into the database
        String sql = "INSERT INTO quizzes (title, questions, answers) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < quizTitles.length; i++) {
                pstmt.setString(1, quizTitles[i]);
                pstmt.setString(2, "[" + String.join(",", questions[i]) + "]"); // Join questions into JSON array format
                pstmt.setString(3, ""); // Placeholder for answers, modify as necessary
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
