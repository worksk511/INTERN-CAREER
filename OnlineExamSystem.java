import java.util.*;

class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class Question {
    private String content;
    private List<String> options;
    private int correctOption;

    public Question(String content, List<String> options, int correctOption) {
        this.content = content;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getContent() {
        return content;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Exam {
    private String title;
    private int durationMinutes;
    private List<Question> questions;

    public Exam(String title, int durationMinutes, List<Question> questions) {
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

class ExamResult {
    private String examTitle;
    private int score;
    private String feedback;

    public ExamResult(String examTitle, int score, String feedback) {
        this.examTitle = examTitle;
        this.score = score;
        this.feedback = feedback;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }
}

public class OnlineExamSystem {
    private List<User> users;
    private List<Exam> exams;
    private Map<String, List<ExamResult>> userResults;

    public OnlineExamSystem() {
        this.users = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.userResults = new HashMap<>();
    }

    public void registerUser(String username, String password, String role) {
        User user = new User(username, password, role);
        users.add(user);
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Authentication failed
    }

    public void createExam(String title, int durationMinutes, List<Question> questions) {
        Exam exam = new Exam(title, durationMinutes, questions);
        exams.add(exam);
    }

    public ExamResult takeExam(User student, Exam exam, List<Integer> studentAnswers) {
        if ("student".equals(student.getRole()) && exams.contains(exam)) {
            int totalQuestions = exam.getQuestions().size();
            int correctAnswers = 0;

            for (int i = 0; i < totalQuestions; i++) {
                Question question = exam.getQuestions().get(i);
                int studentAnswer = studentAnswers.get(i);

                if (studentAnswer == question.getCorrectOption()) {
                    correctAnswers++;
                }
            }

            int score = (int) (((double) correctAnswers / totalQuestions) * 100);
            String feedback = (score >= 70) ? "Pass" : "Fail";

            ExamResult result = new ExamResult(exam.getTitle(), score, feedback);
            List<ExamResult> userExamResults = userResults.getOrDefault(student.getUsername(), new ArrayList<>());
            userExamResults.add(result);
            userResults.put(student.getUsername(), userExamResults);

            return result;
        }

        return null; // Exam taking failed
    }

    public List<ExamResult> viewExamHistory(User student) {
        return userResults.getOrDefault(student.getUsername(), new ArrayList<>());
    }

    public static void main(String[] args) {
        OnlineExamSystem examSystem = new OnlineExamSystem();

        // Register users
        examSystem.registerUser("teacher1", "teacher123", "teacher");
        examSystem.registerUser("student1", "student123", "student");

        // Authenticate users
        User teacher = examSystem.authenticateUser("teacher1", "teacher123");
        User student = examSystem.authenticateUser("student1", "student123");

        // Create exam questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", Arrays.asList("Berlin", "Paris", "Rome"), 1));
        questions.add(new Question("What is 2 + 2?", Arrays.asList("3", "4", "5"), 1));
        questions.add(new Question("Is the earth round?", Arrays.asList("True", "False"), 0));

        // Create an exam
        examSystem.createExam("General Knowledge", 30, questions);

        // Student takes the exam
        List<Integer> studentAnswers = Arrays.asList(1, 1, 0); // Assuming student selected the first option for each question
        ExamResult result = examSystem.takeExam(student, examSystem.exams.get(0), studentAnswers);

        // View exam history
        List<ExamResult> examHistory = examSystem.viewExamHistory(student);
        System.out.println("Exam History for Student 1:");
        for (ExamResult examResult : examHistory) {
            System.out.println("Exam: " + examResult.getExamTitle() + ", Score: " + examResult.getScore() +
                    ", Feedback: " + examResult.getFeedback());
        }
    }
}
