import java.net.PasswordAuthentication;

/**
 * Created by Harru on 2017/7/21.
 */
public class Mananger extends User {
  static String password = "Mananger";

  Mananger(String pwd) throws OperationFailedException {
    if (!pwd.equals(password)) {
      throw new OperationFailedException("Wrong Password");
    } else {
      super.setLogin();
    }
  }
  void viewPending(String UPC) throws OperationFailedException {
    System.out.println(allItems.getStoreRecord().getProduct(UPC).getPendingNumber());
  }
  void viewProfit(){
    System.out.println(allItems.getStoreRecord().getProfit());
  }

  void viewAllPendingOrder(){
    System.out.println(allItems.getStoreRecord().getPendingList());;
  }
  void viewRevenue(){
    System.out.println(allItems.getStoreRecord().getRevenue());
  }

  void setPrize(String upc, double prize) throws OperationFailedException {
    allItems.getStoreRecord().getProduct(upc).setPrize(prize);
  }

  /**
   * Set a string of password for manager to log in and get access to certain types of information.
   * @param pwd the password
   */
  static void setPassword(String pwd) {
    Mananger.password = pwd;
  }
}
