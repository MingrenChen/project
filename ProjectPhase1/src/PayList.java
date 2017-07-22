import com.sun.javafx.fxml.builder.ProxyBuilder;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the list
 */
public class PayList {

  /**
   * things need to be pay.
   */
  private Map<Product, Integer> payList;

  private static int nextID = 1;

  private static double totalRevenue = 0.0;

  /**
   * prize for this pay list.
   */
  private double totalPrize = 0;

  /**
   * Payment type of this pay list. -1 for not pay yet,
   * 0 for credit, 1 for debit, 2 for cash.
   */
  private int paymentType = -1;

  /**
   * card number of this payment if pay by card or -1
   * for not paid yet and 0 for cash.
   */
  private int cardNumber = -1;

  PayList() {
    payList =  new HashMap<Product, Integer>();
    int payListID = nextID;         //an unique code for a specific ID for a receipt.
    nextID ++;


  }

    /**
     * Add items to the paylist for the customers.
     * @param UPC the universal product code
     * @throws OperationFailedException
     */
  void addItem(String UPC) throws OperationFailedException {
    int num;
    Product item = allItems.getStoreRecord().getProduct(UPC);
    if (!payList.containsKey(item)) {
      payList.put(item, 1);
    } else {
      num = payList.get(item);
      payList.put(item, num + 1);
    }
    for (Product product : payList.keySet()) {
        totalPrize = 0;
      totalPrize += product.checkout(payList.get(product));
    }
  }

    /**
     * Cancel items in the paylist for a customer.
     * @param UPC the universal product code
     * @throws OperationFailedException
     */
  void cancelItem(String UPC) throws OperationFailedException {
      int num;
      Product item = allItems.getStoreRecord().getProduct(UPC);
      if (!payList.containsKey(item)) {
          throw new OperationFailedException("You didn't scan this item.");
      } else {
          num = payList.get(item);
          payList.put(item, num + 1);
      }
      for (Product product : payList.keySet()) {
          totalPrize = 0;
          totalPrize += product.checkout(payList.get(product));
      }
  }

    /**
     * Check the information of a certain purchase, it includes the cardnumber, items they bought, price of the items
     * , date and time.
     * @param type payment type
     * @param cNumber the cardnumber
     */
  void purchase(int type, int cNumber) {
      int prize = 0;
      paymentType = type;
    long checkoutTime = Instant.now().getEpochSecond()*1000;
    java.util.Date dateTime=new java.util.Date(checkoutTime);
    cardNumber = cNumber;
    for (Product product : payList.keySet()) {
        product.removeItem(payList.get(product));
    }
    for (Product product : payList.keySet()) {
        prize += product.getCost() * payList.get(product);
    }
    for (Product product : payList.keySet()){
        totalRevenue += product.getPrize()* payList.get(product);
    }
    allItems.getStoreRecord().addProfit(prize - totalPrize);
  }
}
