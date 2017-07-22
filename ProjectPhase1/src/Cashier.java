import java.util.ArrayList;

/**
 * Created by chenmi84 on 18/07/17.
 */

public class Cashier extends User {
    private static String password = "Cashier";

    public void setReceipt(PayList receipt) {
        this.receipt = receipt;
    }

    private PayList receipt = null;


    Cashier(String pwd) throws OperationFailedException {
        if (!pwd.equals(password)) {
            throw new OperationFailedException("Wrong Password");
        } else {
            super.setLogin();
        }
    }

    /**
     * Set the password for cashier to get access to certain information.
     * @param pwd the password
     */
    static void setPassword(String pwd) {
        Cashier.password = pwd;
    }

    void scan(String UPC) throws OperationFailedException {
        if (receipt != null) {
            receipt.addItem(UPC);
        } else {
            receipt = new PayList();
            receipt.addItem(UPC);
        }
    }
    void checkOut(int type, int cnum) {
        receipt.purchase(type,cnum);
        receipt = null;
    }

    void cancelOne(String UPC) throws OperationFailedException {
        if (receipt == null) {
            throw new OperationFailedException("Nothing to cancel!");
        } else {
            receipt.cancelItem(UPC);
        }
    }

    void viewDiscount(String UPC) throws OperationFailedException {
        Discount discount = allItems.getStoreRecord().getProduct(UPC).getDiscounts();
        System.out.print("Discount started at: " + discount.getStartDate() + "\n" +
        "Discount ended at: " + discount.getEndDate());
    }

    void cancelAll() {
        receipt = null;
    }

}
