import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chenmi84 on 18/07/17.
 */
public class Reshelver extends User{
    private static String password = "Reshelver";

    Reshelver(String pwd) throws OperationFailedException {
        if (!pwd.equals(password)) {
            throw new OperationFailedException("Wrong Password");
        } else {
            super.setLogin();
        }
    }
    void viewLocation(String UPC) throws OperationFailedException {
         System.out.println(allItems.getStoreRecord().getProduct(UPC).getAisle());
    }
    void viewOrderHistory(String UPC) throws OperationFailedException {
        ArrayList<LocalDate> list = allItems.getStoreRecord().getProduct(UPC).getOrderHistory();
        for (LocalDate date : list) {
            System.out.print(date + "\n");
        }
    }
    void viewItemNum(String UPC) throws OperationFailedException {
        System.out.println(allItems.getStoreRecord().getProduct(UPC).getItemNum());
    }

    void changeAisle(int aisle1, int aisle2) {
        allItems.getStoreRecord().changeAisle(aisle1,aisle2);
    }

    static void setPassword(String pwd){
        Reshelver.password = pwd;
    }
}
