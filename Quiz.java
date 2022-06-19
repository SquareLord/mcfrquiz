import java.util.*;
import java.io.*;
class Quiz {
  private static ArrayList<Question> arr = new ArrayList<>(); 
  private static Scanner reader = new Scanner(System.in);  // reader for whole class
  private int correctNum, answeredNum;
  private static Scanner readerLine;
  private static int numberOfTimes;
  private static FileOutputStream f;
  private static int origNum;
  private static String decider;

  public Quiz (int num) {
    numberOfTimes = num;
    origNum = num;
  }
  public void initSetUp() {
    try {
      f = new FileOutputStream("questions.csv", true);
      readerLine = new Scanner(new File("questions.csv"));
    } catch(IOException e) {
      System.out.println("Something went wrong.");
    }
    copyArray();
    mainInterface();
  }
  public void mainInterface() {
    do {
      System.out.println("\nWelcome to Quiz Software, choose your mode.\n1 - Add questions\n2 - Play quiz\nE - exit.");
      decider = reader.nextLine();
      if (decider.equalsIgnoreCase("1"))
        addQuestion();
      else if (decider.equalsIgnoreCase("2"))
        gameInterface();
    } while (!decider.equalsIgnoreCase("E"));
  }
  public void gameInterface() {
    String hello = "";
    if (arr.size() == 0) {
      System.out.println("You must have a number of questions greater than 1.");
      decider = "E";
    } else {
      do {
        if (numberOfTimes == 0) {
          System.out.println("Your questions have been finished.");
          displayScore();
          numberOfTimes = origNum;
          return;
        }
        play();
          
        System.out.println("Check score? Y/N");
        hello = reader.nextLine();
        if(hello.equalsIgnoreCase("Y"))
          displayScore();
      } while(arr.size() != 0);
    }
  }
  public void addQuestion() {
    String[] a = new String[6];
    try {
      System.out.println("What is the question?");
        a[0] = reader.nextLine();
      System.out.println("What is the first answer? (do not input if FRQ)");
        a[1] = reader.nextLine();
      System.out.println("What is the second answer? (do not input if FRQ)");
        a[2] = reader.nextLine();
      System.out.println("What is the third answer? (do not input if FRQ)");
        a[3] = reader.nextLine();
      System.out.println("What is the fourth answer? (do not input if FRQ)");
        a[4] = reader.nextLine();
      System.out.println("What is the correct answer? Select A, B, C, D, or anything else if FRQ.");
        a[5] = reader.nextLine();
      f.write((a[0] + "," + a[1] + "," + a[2] + "," + a[3] + "," + a[4] + "," + a[5] + ",\n").getBytes());
      arr.add(new Question(a[0], a[1], a[2], a[3], a[4], a[5]));
      System.out.println("Successfully added!");
    } catch (Exception e) {
      System.out.println("An error occurred.");
    }
  }
  
  public void play() {
    int rand = (int)(Math.random() * arr.size());
    if (arr.get(rand).askQ()) {
      correctNum++;
    } else {
      System.out.println("Do you want to override this question? Y/N\n (ex. you got it right but without the correct wording)");
      String hea = reader.nextLine();
      if (hea.equalsIgnoreCase("Y"))
        correctNum++;
    }
    answeredNum++;
    arr.remove(rand);
    numberOfTimes--;
  }
  public void displayScore() {
    System.out.println("\nYou got " + this.correctNum + " out of " + this.answeredNum + " questions right. " + (((double)this.correctNum * 100)/(this.answeredNum)) + "%.");
  }
  
  public void copyArray() {
    while(readerLine.hasNext()) {
      String question = readerLine.nextLine();
      String[] h = question.split(",");
      arr.add(new Question(h[0], h[1], h[2], h[3], h[4], h[5]));
    }
  }
  
}