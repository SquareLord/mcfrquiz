import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    runGame();
  }
  public static void runGame() {
    Scanner reader = new Scanner(System.in);
    System.out.println("Welcome to the quiz! Select the amount of questions you would like your test to be today.");
    int num = 0;
   try {
      num = reader.nextInt();
      reader.nextLine();
      Quiz q = new Quiz(num);
      q.initSetUp();
    } catch (Exception e) {
      System.out.println("Invalid input!");
      runGame();
    }
    reader.close();
  }
}