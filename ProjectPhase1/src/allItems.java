import sun.swing.SwingUtilities2;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Mingren Chen on 2017/7/10.
 * allItems class contains all products in the store
 */
public class allItems {

  private static allItems StoreRecord = new allItems();

  public static allItems getStoreRecord() {
    return StoreRecord;
  }

  private ArrayList<Product> ProductList = new ArrayList<Product>();

  private double totalProfit = 0;

  private allItems() {
  }


  private ArrayList<mSection> sectionList = new ArrayList<>();


  public ArrayList<Product> getProductByAisle(int a){
    ArrayList<Product> result = new ArrayList<>();
    for (Product product: ProductList){
      if (product.getAisle() ==  a){
        result.add(product);
      }
    } return result;
  }

  public void changeAisle(int a, int b){
    ArrayList lstA = getProductByAisle(a);
    ArrayList lstB = getProductByAisle(b);
    for (Object pA: lstA){
      ((Product) pA).setAisle(b);
    }
    for (Object pB: lstB){
      ((Product) pB).setAisle(a);
    }
  }



  public ArrayList getProductBySection(mSection sec){
    ArrayList<Product> result = new ArrayList<>();
    for (Product product: ProductList) {
      if (product.getSection() == sec) {
        result.add(product);
      }
    }
    return result;
  }







  public void addProduct(Product p) {
    ProductList.add(p);
  }

  public Product getProduct(int UPC) throws OperationFailedException {
    for (Product product: ProductList) {
      if (product.getUpc() == UPC) {
        return product;
      }
    }
    throw new OperationFailedException("No such Product, check your UPC!");
  }

  public void addProfit(double profit){
    totalProfit += profit;
  }

  public double getProfit() {
    return totalProfit;
  }
}
