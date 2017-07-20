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
      System.out.println("You login as a receiver, you can use command: scan in product, add new product, View Location. ");
    }
  }

  String getMSG() {
    return msg;
  }
}
