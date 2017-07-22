import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.temporal.ChronoUnit;


/**
 * One kind of the product in the store.
 */
public class Product implements Serializable {

  private LocalDate today = LocalDate.now();

  private HashMap<LocalDate, Double> PrizeHistory = new HashMap<>();
  private String name;
  private String section;
  private String subsection;
  private int aisle;
  private int threshold;
  private double prize;
  private int pendingNumber = 0;
  private Discount discounts = null;
  private String distributor;
  private ArrayList orderHistory = new ArrayList();
  private double cost;
  private String upc;
  private int itemNum;



  Product(double productCost,String productName, double productPrize, String productSection,
      String pSubsection, int aisle1, int r, String productDistributor) {
    upc = Integer.toString(allItems.getStoreRecord().getNextUPC());
    int zeroNum = 12 - upc.length();
    for(int i = zeroNum; i > 0; i--) {
      upc = "0" + upc;
    }
    cost = productCost;
    name = productName;
    prize = productPrize;
    section = productSection;
    subsection = pSubsection;
    aisle = aisle1;
    threshold = r;
    distributor = productDistributor;
    allItems.getStoreRecord().addProduct(this);
  }

  public HashMap<LocalDate, Double> getPrizeHistory() {
    return PrizeHistory;
  }

  public void setPrizeHistory(HashMap<LocalDate, Double> prizeHistory) {
    PrizeHistory = prizeHistory;
  }


  public String getName() {
    return name;
  }

  public String getSection() {
    return section;
  }

  public String getSubsection() {
    return subsection;
  }

  public int getAisle() {
    return aisle;
  }

  public void setAisle(int aisle) {
    this.aisle = aisle;
  }

  public int getThreshold() {
    return threshold;
  }

  public double getPrize() {
    return prize;
  }

  public void setPrize(double prize) {
    this.prize = prize;
  }

  public int getPendingNumber() {
    return pendingNumber;
  }

  public void setPendingNumber(int pendingNumber) {
    this.pendingNumber = pendingNumber;
  }


  public Discount getDiscounts() {
    return discounts;
  }



  public String getDistributor() {
    return distributor;
  }

  public void setDistributor(String distributor) {
    this.distributor = distributor;
  }

  public ArrayList getOrderHistory() {
    return orderHistory;
  }

  public void setOrderHistory(ArrayList orderHistory) {
    this.orderHistory = orderHistory;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public String getUpc() {
    return upc;
  }

  public void setUpc(String upc) {
    this.upc = upc;
  }

  public int getItemNum() {
    return itemNum;
  }

  public void setItemNum(int itemNum) {
    this.itemNum = itemNum;
  }

  /**
   * Add a new product into the store.
   */
  void addNew() {
    itemNum += threshold * 3;
    pendingNumber = 0;
  }

  /**
   * Return null, since the method just remove the item from the shopping cart.
   * @param num number of items the customer buy.
   * @return null
   */
  String removeItem(int num) {
    itemNum -= num;
    if (!(itemNum > threshold) && (pendingNumber==0)) {
      pendingNumber = threshold * 3;
      orderHistory.add(today);
      return null;
    }
    return null;
  }

  /**
   * Add a discount to a product between a period of time, after that, the price of the product will follow the discount
   * plan during that period of time.
   * @param d the discount
   * @param start the date the discount starts
   * @param end the date the discount ends
   */
  void addDiscount (Discount d, LocalDate start, LocalDate end){
    discounts = d;
    d.setStartDate(start);
    d.setEndDate(end);
    if (PrizeHistory.containsKey(start)){
      PrizeHistory.replace(start,this.checkout(1));
      PrizeHistory.put(end.plus(1,ChronoUnit.DAYS),prize );
    }else{
      PrizeHistory.put(start,this.checkout(1));
      PrizeHistory.put(end.plus(1,ChronoUnit.DAYS),prize );
    }
  }

  void deleteDiscount() throws OperationFailedException {
    if (!(discounts == null)) {
      LocalDate end = discounts.getEndDate();
      if (today.isBefore(end)) {
        PrizeHistory.replace(today, prize);
        PrizeHistory.remove(end.plus(1, ChronoUnit.DAYS));
      }
    } else{
      throw new OperationFailedException("Discount does not exit.");
    }
  }




//  void addDiscount(Discount d) {
//    discounts.add(d);
//    LocalDate today = LocalDate.now();
//    if (PrizeHistory.containsKey(today)) {
//      PrizeHistory.replace(today, this.checkout(1));
//    } else {
//      PrizeHistory.put(today, this.checkout(1));
//    }
//  }

//  void deleteDiscount(Discount d) {
//    if (discounts.contains(d)) {
//      discounts.remove(d);
//      LocalDate today = LocalDate.now();
//      if (PrizeHistory.containsKey(today)) {
//        PrizeHistory.replace(today, this.checkout(1));
//      } else {
//        PrizeHistory.put(today, this.checkout(1));
//      }
//    }
//  }

  /**
   * Return the price that the customer needs to pay at checkout.
   * @param num the number of items the customer buy
   * @return the price in total
   */
  double checkout(int num) {
    Double returnPrize = prize * num;
    if (today.isBefore(discounts.getEndDate())){
      returnPrize = discounts.getPrize(prize,num);
    }
    return returnPrize;
  }






}
