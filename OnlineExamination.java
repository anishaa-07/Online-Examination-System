import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    private String username;
    private String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}

public class OnlineExamination {

    static Scanner sc = new Scanner(System.in);
    static int score = 0;
    static boolean timeUp = false;

    public static void main(String[] args) {

        User user = new User("student", "1234");

        System.out.println("📝 Online Examination System");

        // LOGIN
        System.out.print("Enter Username: ");
        String uname = sc.next();

        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (!uname.equals(user.getUsername()) || !pass.equals(user.getPassword())) {
            System.out.println("❌ Invalid Login");
            return;
        }

        int choice;
        do {
            System.out.println("\n1. Start Exam");
            System.out.println("2. Update Password");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    startExam();
                    break;

                case 2:
                    updatePassword(user);
                    break;

                case 3:
                    System.out.println("👋 Logged out successfully");
                    break;

                default:
                    System.out.println("❌ Invalid choice");
            }
        } while (choice != 3);

        sc.close();
    }

    static void startExam() {

        score = 0;
        timeUp = false;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                timeUp = true;
                System.out.println("\n⏰ Time Up! Exam auto-submitted.");
                timer.cancel();
            }
        }, 30000); 

        System.out.println("\n📚 Exam Started (30 seconds)");

        askQuestion(
            "1. Java is?",
            "a) Platform dependent",
            "b) Platform independent",
            "c) Machine dependent",
            "d) None",
            'b'
        );

        askQuestion(
            "2. Which keyword is used for inheritance?",
            "a) this",
            "b) super",
            "c) extends",
            "d) implements",
            'c'
        );

        askQuestion(
            "3. Which collection allows duplicates?",
            "a) Set",
            "b) Map",
            "c) List",
            "d) None",
            'c'
        );

        timer.cancel();
        System.out.println("\n✅ Exam Finished");
        System.out.println("🎯 Your Score: " + score + "/3");
    }

    static void askQuestion(String q, String a, String b, String c, String d, char correct) {

        if (timeUp) return;

        System.out.println("\n" + q);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.print("Your answer: ");

        char ans = sc.next().toLowerCase().charAt(0);
        if (ans == correct) {
            score++;
        }
    }

    static void updatePassword(User user) {
        System.out.print("Enter new password: ");
        String newPass = sc.next();
        user.setPassword(newPass);
        System.out.println("✅ Password updated successfully");
    }
}

