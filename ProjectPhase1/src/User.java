import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Penny on 2017-07-15.
 */
public abstract class User {

  /**
   * four types: manager, cashier, reshelier, receiver
   */
  private String UserType;
  private PayList receipt = null;
  private boolean login = false;

  public void setPWD(User user, String Type, String password) throws OperationFailedException {
    if (user instanceof Mananger) {
      switch (Type) {
        case "mananger":
          Mananger.setPassword(password);
          break;
        case "cashier":
          Cashier.setPassword(password);
          break;
        case "Reshelver":
          Reshelver.setPassword(password);
          break;
        case "Receiver":
          Receiver.setPassword(password);
          break;
        default:
          System.out.print("Wrong UserName.");
          break;
      }
    } else {
      throw new OperationFailedException("You are not allowed to change password.");
    }
  }

  void setLogin() {
    login = true;
  }

}

