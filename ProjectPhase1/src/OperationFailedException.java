
/**
 * Created by Harru on 2017/7/10.
 */
public class OperationFailedException extends Exception {
  String msg = "";
  OperationFailedException(String  FailedType) {
    System.out.println(FailedType);
    msg = FailedType;
  }
  OperationFailedException(User user) {
    System.out.println("Authority Failed.\n");
    if (user instanceof Receiver) {
      System.out.println("You login as a receiver, commands: scan in product, add product, View Location, Prize History, view prize, view cost, set prize. ");
    } else if (user instanceof Mananger) {
      System.out.println("You login as a mananger, commands: View pending, view profit, view revenue, profit history,.");
    } else if (user instanceof Cashier) {
      System.out.println("You login as a cashier, commands: scan, pending orders, cancel item, purchase, cancel, view discount");
    } else if (user instanceof Reshelver) {
      System.out.println("You login as a reshelver, commands: view Location, change aisles,View section, item number");
    }
  }

  String getMSG() {
    return msg;
  }
}