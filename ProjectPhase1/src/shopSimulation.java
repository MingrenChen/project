import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class shopSimulation {

  public static void main(String[] args) throws OperationFailedException, IOException, ClassNotFoundException {
    try{
      ObjectInputStream readInverntory = new ObjectInputStream(new FileInputStream("inventory.out"));
      allItems.replace((allItems) readInverntory.readObject());
      readInverntory.close();
    } catch(IOException E){
      ObjectOutputStream Inventory = new ObjectOutputStream(new FileOutputStream("inventory.out"));
      Inventory.close();
    }


    String command = "";
    while (!HandleEvent.getEvent().isLogin()) {
      HandleEvent.getEvent().passMessage("Login");
    }
    while (!command.equalsIgnoreCase("Quit")) {
      System.out.println("command: ");
      Scanner msg = new Scanner(System.in);
      command = msg.nextLine();
      try{
        ObjectOutputStream inventory = new ObjectOutputStream(new FileOutputStream("inventory.out"));
        HandleEvent.getEvent().passMessage(command);
        inventory.writeObject(allItems.getStoreRecord());
        System.out.println(1);
        inventory.close();
        System.out.println(2);
      } catch (OperationFailedException e) {
        System.out.print(e.getMSG());
      }

    }
  }
}