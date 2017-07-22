import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by chenmi84 on 18/07/17.
 */
public class Receiver extends User{
    private static String password = "Receiver";
    private PayList receipt;

    Receiver(String pwd) throws OperationFailedException {
        if (!pwd.equals(password)) {
            throw new OperationFailedException("Wrong Password");
        } else {
            super.setLogin();
        }
    }

    /**
     * Set a string of password for the receiver to get access to certain information.
     * @param password the password
     */
    public static void setPassword(String password) {
        Receiver.password = password;
    }

    void getProNum() {
        System.out.println(allItems.getStoreRecord().getNum());
    }

    void addNew(String UPC) throws OperationFailedException {
        receipt.addItem(UPC);
    }
    void viewLocation(String UPC) throws OperationFailedException {
        System.out.println(allItems.getStoreRecord().getProduct(UPC).getAisle());
    }
    void viewCost(String UPC) throws OperationFailedException {
        System.out.println(allItems.getStoreRecord().getProduct(UPC).getCost());
    }

    void viewPrizeHistory(String UPC) throws OperationFailedException {
        HashMap<LocalDate, Double> map =  allItems.getStoreRecord().getProduct(UPC).getPrizeHistory();
        for (LocalDate date: map.keySet()) {
            System.out.println(date + " :" + map.get(date));
        }
    }
    void viewPrize(String UPC) throws OperationFailedException {
        System.out.println(allItems.getStoreRecord().getProduct(UPC).getPrize());
    }
    void addProduct(double cost, String name, double prize, String section,
        String subsection, int aisle, int threshold, String distributor)
        throws OperationFailedException{
        Product p = new Product(cost, name, prize, section,
          subsection, aisle, threshold, distributor);
    }
}
