import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Penny on 2017-07-15.
 */
public class User {

  /**
   * four types: manager, cashier, reshelier, receiver
   */
  private String UserType;
  private PayList recepit = null;
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

  /**
   * add number of products into inventory;
   * @param UPC: UPC number
   */
  void addNew(int UPC) throws OperationFailedException {
    allItems.getStoreRecord().getProduct(UPC).addNew();
  }

  double viewPrize(int UPC) throws OperationFailedException {
    return allItems.getStoreRecord().getProduct(UPC).getPrize();
  }

  HashMap viewPrizeHistory(int UPC) throws OperationFailedException {
      return allItems.getStoreRecord().getProduct(UPC).getPrizeHistory();
  }

  void scan(int UPC) throws OperationFailedException {
    recepit.addItem(UPC);
    }
  void cancel(int UPC) throws OperationFailedException {
      recepit.cancelItem(UPC);
}

  void checkOut(int type, int cnum){
    recepit.purchase(type,cnum);
  }

  void cancelAll(){
    recepit = null;
  }

  void setPrize(int p, int UPC) throws OperationFailedException {
    allItems.getStoreRecord().getProduct(UPC).setPrize(p);
  }

  int viewLocation(int UPC) throws OperationFailedException {
    return allItems.getStoreRecord().getProduct(UPC).getAisle();
  }

  mSection viewSection(int UPC) throws OperationFailedException {
    return allItems.getStoreRecord().getProduct(UPC).getSection();
  }

  int viewPending(int UPC) throws OperationFailedException {
      return allItems.getStoreRecord().getProduct(UPC).getPendingNumber();
    }

  ArrayList viewOrderHistory(int UPC) throws OperationFailedException {
      return allItems.getStoreRecord().getProduct(UPC).getOrderHistory();
    }

  void viewProfit(){
    allItems.getStoreRecord().getProfit();
  }

}
