/**
 * Created by Harry on 2017/7/9.
 * Discount is an ArrayList contains the discount
 * of a specific product.
 */
public class Discount {

  /**
   * the type of a discount. 0 for i% off,
   * 1 for buy x get next y i% off, 2 for
   * buy x get everything i% percent off.
   */
  private int discountType;


  /**
   * discount of a product
   */
  private double discount;

  /**
   * if you buy x items.
   */
  private int buyX;

  /**
   * you can get next Y items with discount.
   */
  private int nextY;

  /**
   * type 0 discount. This kind of discount
   * will on every product.
   * @param off double how many discount on
   * this product.
   */
  Discount(double off) {
    discountType = 0;
    discount = off;
  }
  public double getDiscount() {
    return discount;
  }


  /**
   * type 1 discount. Buy x products then
   * you can get next y product with a
   * discount.
   * @param off double
   * @param x int
   * @param y int
   */
  Discount(double off, int x, int y) {
    discountType = 1;
    discount = off;
    buyX = x;
    nextY = y;
  }

  /**
   * type 2 discount, Buy x items of a product,
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
   * get final prize after this discount.
   * @param prize double
   * @param number int amount of product customers
   * purchase
   * @return double
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
