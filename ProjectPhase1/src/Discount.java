import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Harry on 2017/7/9.
 * Discount is an ArrayList contains the discount
 * of a specific product.
 */
public class Discount implements Serializable {
  /**
   * The start date of the discount.
   */
  private LocalDate startDate;
  /**
   * The end date of the discount.
   */
  private LocalDate endDate;

  /**
   * The type of a discount.
   * There are three type of discount:
   * 0 for i% off,
   * 1 for buy x get next y i% off,
   * 2 for buy x get everything i% percent off.
   */
  private int discountType;


  /**
   * Discount of a product.
   */
  private double discount;

  /**
   * The condition that you buy x items.
   */
  private int buyX;

  /**
   * The condition that you can get next Y items with discount.
   */
  private int nextY;

  /**
   * Constructor for type 0 discount.
   * This kind of discount will apply to all products in the store.
   * @param off double the percent of the discount
   */

  Discount(double off, LocalDate start, LocalDate end) {
    startDate = start;
    endDate = end;
    discountType = 0;
    discount = off;
  }

  /**
   * Return the discount.
   * @return return the discount percent.
   */
  public double getDiscount() {
    return discount;
  }

  /**
   * Return the start date of the discount.
   * @return return the start date.
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * Set up new start date for discount.
   * @param startDate the new start date
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  /**
   * Return the end date of discount.
   * @return return the end date.
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * Set up new end date of discount.
   * @param endDate new end date
   */
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  /**
   * This is type 1 discount.
   * When you buy x products.
   * then you can get next y product with a discount.
   * @param off double the discount percent
   * @param x int when you buy x item
   * @param y int you get next y item with discount
   */
  Discount(double off, int x, int y) {
    discountType = 1;
    discount = off;
    buyX = x;
    nextY = y;
  }

  /**
   * This is type 2 discount,
   * Buy x items of a product,
   * then get a discount on everything.
   * @param off double
   * @param x int
   */
  Discount(double off, int x) {
    discountType = 2;
    discount = off;
    buyX = x;
  }

  /**
   * Return the final prize when applying this discount.
   * @param prize double original price for the product
   * @param number int amount of product
   * @return double the final price
   */
  double getPrize(double prize, int number) {
    if (discountType == 0) {
      return number * prize * (1 - discount);
    } else if (discountType == 1) {
      if (number >= buyX) {
        return
                buyX * prize + nextY * prize * (1 - discount) + this.getPrize(prize, number - buyX - nextY);
      } else {
        return number * prize;
      }
    } else {
      if (number >= buyX) {
        return number * prize * discount;
      } else {
        return number * prize;
      }
    }
  }
}
