import java.time.LocalDate;
import java.util.Scanner;

public class shopSimulation {

  public static void main(String[] args) throws OperationFailedException {
    String command = "";
    while (!HandleEvent.getEvent().isLogin()) {
      try {
        HandleEvent.getEvent().passMessage("Login");
      } catch (OperationFailedException e) {

      }
    }
    while (!command.equalsIgnoreCase("Quit")) {
      System.out.println("command: ");
      Scanner msg = new Scanner(System.in);
      command = msg.nextLine();
      try{
        HandleEvent.getEvent().passMessage(command);
      } catch (OperationFailedException e) {
      }

    }
  }
}