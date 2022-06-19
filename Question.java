import java.util.*;
public class Question {
  private String q, a, b, c, d, caString;
  private int correctNum, answeredNum; 
  private static Scanner reader = new Scanner(System.in);
  private char caChar;
  private static boolean isFRQ;
  
  public Question (String q, String a, String b, String c, String d, String caString) {
    this.q = q;
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    caString.trim();
    System.out.print(q + "\""+caString + "\"" );
    if((caString.length() == 1 && (caString.equalsIgnoreCase("A") || caString.equalsIgnoreCase("B") || caString.equalsIgnoreCase("C") || caString.equalsIgnoreCase("D")))) {
      caString = caString.toUpperCase();//MCQ
      this.caChar= caString.charAt(0);
      this.caString = "";
      isFRQ = false;
    } else {
      this.caChar= ' ';
      this.caString = caString;
      isFRQ = true;
    }
    System.out.println(isFRQ);
  }
  public boolean askQ() {
    isFRQ = !isFRQ;
    System.out.println(isFRQ);
    System.out.println(this.q);
    if (!isFRQ) {
      System.out.println("A: " + this.a + "\nB: " + this.b + "\nC: " + this.c + "\nD: " + this.d);
    }
    char j = ' ';
    String i = "";
    try {
      i = reader.nextLine();
    } catch(Exception e){
      askQ();
    }
    if (!isFRQ) {
      i = i.toUpperCase();
      j = i.charAt(0);
      i = "";
    }
    i.trim();
    System.out.println("\"" + j + "\"");
    System.out.println("\"" + this.caChar + "\"");
    System.out.println("\"" + i + "\"");
    System.out.println("\"" + this.caString + "\"");
    if (j == this.caChar && i.equalsIgnoreCase(this.caString)) {//fix error
      System.out.println(j + i + " is the correct answer!");
      return true;
    } else {
    System.out.println(j + i + " is the wrong answer!\n" + this.caChar+ this.caString + " is the right answer!");
    return false;
    }
  } 
  public void displayScore() {
    int num = (int)(((double)this.correctNum * 100)/(this.answeredNum));
    System.out.println("You got " + this.correctNum + " out of " + this.answeredNum + " questions right. " + num + "%!");
  }
  public double getCurrentScore() {
    return ((double)this.correctNum * 100)/(this.answeredNum);
  }
}
