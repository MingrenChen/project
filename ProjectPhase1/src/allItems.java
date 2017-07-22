import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mingren Chen on 2017/7/10.
 * allItems class contains all products in the store
 */
public class allItems implements Serializable {

  /**
   * This field indicates the store record of all items in store.
   */
  private static allItems StoreRecord = new allItems();

  /**
   * This method is used to get store record.
   * @return store record.
   */
  public static allItems getStoreRecord() {
    return StoreRecord;
  }

  /**
   * This field indicate total revenue.
   */
  private double totalRevenue = 0.0;

  /**
   * This field indicates the list of products.
   */
  private ArrayList<Product> productlist = new ArrayList<Product>();

  /**
   * This field indicates the total profit.
   */
  private double totalProfit = 0;

  private int nextUPC = 0;

  /**
   * Constructor.
   */
  private allItems() {}

  public static void replace(allItems oldStoreRecord){
    StoreRecord = oldStoreRecord;
  }
  /**
   * This is the list of sections.
   */
  private ArrayList<String> sectionList = new ArrayList<>();

  public int getNum() {
    return productlist.size();
  }

  public int getNextUPC() {
    nextUPC ++;
    return nextUPC;
  }

  /**
   * Return an array list of the name of all the
   * products on a certain aisle. The result is an
   * array list of all the product on an aisle.
   * @param a the number label on an aisle
   * @return the products on an specific aisle
   */
  public ArrayList<Product> getProductByAisle(int a) {
    ArrayList<Product> result = new ArrayList<>();
    for (Product product: productlist) {
      if (product.getAisle() ==  a) {
        result.add(product);
      }
    }
    return result;
  }

  /**
   * Switch the location of two products on the aisle, a and b are
   * the amount of two products that need to be switched.
   * This method can help us to change the location of the products.
   * @param a the amount of product A
   * @param b the amount of product B
   */
  public void changeAisle(int a, int b) {
    ArrayList lstA = getProductByAisle(a);
    ArrayList lstB = getProductByAisle(b);
    for (Object pa: lstA) {
      ((Product) pa).setAisle(b);
    }
    for (Object pb: lstB) {
      ((Product) pb).setAisle(a);
    }
  }

  /**
   * Return the string of all products and numbers that been request but not arrive.
   * @return String
   */
  public String getPendingList() {
    String result = "";
    for (Product product: productlist) {
      if (!(product.getPendingNumber() == 0)) {
        result += product.getName() + " : " + product.getPendingNumber() + "\n";
      }
    }
    return result;
  }

  /**
   * Return the total revenue.
   * @return the total amount of revenue
   */
  public double getRevenue() {
    return totalRevenue;
  }

  /**
   * Return an arraylist of the name of all products on a specific section in the store.
   * @param sec the name of an section
   * @return ArrayList
   */
  public ArrayList getProductBySection(String sec) {
    ArrayList<Product> result = new ArrayList<>();
    for (Product product: productlist) {
      if (product.getSection().equals(sec)) {
        result.add(product);
      }
    }
    return result;
  }

  /**
   * Add a product to the ProductList of the store.
   * @param p Product the product
   */
  public void addProduct(Product p) {
    productlist.add(p);
  }

  /**
   * This method is used to get product by UPC.
   * @param upc UPC for product
   * @return found product by UPC
   * @throws OperationFailedException when the UPC does not exist, throw exception.
   */
  public Product getProduct(String upc) throws OperationFailedException {
    for (Product product: productlist) {
      if (product.getUpc().equals(upc)) {
        return product;
      }
    }
    throw new OperationFailedException("No such Product, check your UPC!");
  }

  /**
   * Add a certain extra amount of profit to the current profit.
   * @param profit the extra amount of profit needed to be add
   */
  public void addProfit(double profit) {
    totalProfit += profit;
  }

  /**
   * Return the profit.
   * @return the total amount of profit.
   */
  public double getProfit() {
    return totalProfit;
  }

  /**
   * Add revenue to the current totalRevenue.
   * @param revenue The amount of revenue needed to be add
   */
  public void addRevenue(double revenue) {
    totalRevenue += revenue;
  }

  private Object readResolve(){
      return getStoreRecord();
  }

  private Object writeResolve(){
      return getStoreRecord();
  }
}
