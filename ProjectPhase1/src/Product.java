import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.temporal.ChronoUnit;


/**
 * One kind of the product in the store.
 */
public class Product {

  private HashMap<LocalDate, Double> PrizeHistory = new HashMap<>();
  private String name;
  private mSection section;
  private mSection subsection;
  private int aisle;
  private int threshold;
  private double prize;
  private int pendingNumber = 0;
  private HashMap<ArrayList<LocalDate>,Discount> discounts = new HashMap<>();
  private String distributor;
  private ArrayList orderHistory;
  private double cost;
  private int upc;
  private int itemNum;


  Product(int productUpc, double productCost,String productName, double productPrize, mSection productSection,
          mSection pSubsection, int aisle1, int r, String productDistributor, ArrayList pOderHistory) {
    upc = productUpc;
    cost = productCost;
    name = productName;
    prize = productPrize;
    section = productSection;
    subsection = pSubsection;
    aisle = aisle1;
    threshold = r;
    distributor = productDistributor;
    orderHistory = pOderHistory;
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

  public mSection getSection() {
    return section;
  }

  public void setSection(mSection section) {
    this.section = section;
  }

  public mSection getSubsection() {
    return subsection;
  }

  public void setSubsection(mSection subsection) {
    this.subsection = subsection;
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

  public void setThreshold(int threshold) {
    this.threshold = threshold;
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


  public HashMap<ArrayList<LocalDate>, Discount> getDiscounts() {
    return discounts;
  }

  public void setDiscounts(HashMap<ArrayList<LocalDate>, Discount> discounts) {
    this.discounts = discounts;
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

  public long getUpc() {
    return upc;
  }

  public void setUpc(int upc) {
    this.upc = upc;
  }

  public int getItemNum() {
    return itemNum;
  }

  public void setItemNum(int itemNum) {
    this.itemNum = itemNum;
  }

  void addNew() {
    itemNum += threshold * 3;
    pendingNumber = 0;
  }

  String removeItem(int num) {
    itemNum -= num;
    if (!(itemNum > threshold) && (pendingNumber==0)) {
      pendingNumber = threshold * 3;
      LocalDate today = LocalDate.now();
      orderHistory.add(today);
      return null;
    }
    return null;
  }

  void addDiscount (Discount d, LocalDate start, LocalDate end){
    ArrayList<LocalDate> discountPeriod = new ArrayList<>();
    discountPeriod.add(start);
    discountPeriod.add(end);
    discounts.put(discountPeriod,d);
    for ( LocalDate date = start; date.isBefore(end); date.plus(1,ChronoUnit.DAYS)){
      if (PrizeHistory.containsKey(date)){
        PrizeHistory.replace(date,this.checkout(1));
      }else{
        PrizeHistory.put(date,this.checkout(1));
      }
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

  double checkout(int num) {
    int returnPrize = 0;
    for (Discount d : discounts.values()) {
      returnPrize += d.getPrize(prize, num);
    }
    return returnPrize;
  }






}
