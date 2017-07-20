import java.util.Scanner;

/**
 * Created by chenmi84 on 18/07/17.
 * handle the event of input.
 */
public class HandleEvent {
    private static HandleEvent ourInstance = new HandleEvent();

    private User user = null;

    private boolean login = false;

    public static HandleEvent getEvent() {
        return ourInstance;
    }

    private HandleEvent() {
    }

    boolean isLogin() {
        return login;
    }

    public void passMessage(String message) throws OperationFailedException {
        if (message.equalsIgnoreCase("Login")) {
            System.out.print("UserType: ");
            Scanner userTypeReader = new Scanner(System.in);
            String UserType = userTypeReader.nextLine();
            System.out.print("Password: ");
            Scanner Password = new Scanner(System.in);
            String pwd = Password.nextLine();
            switch (UserType) {
                case "Mananger":
                    user = new Mananger(pwd);
                    break;
                case "Cashier":
                    user = new Cashier(pwd);
                    break;
                case "Reshelver":
                    user = new Reshelver(pwd);
                    break;
                case "Receiver":
                    user = new Receiver(pwd);
                    break;
                default:
                    System.out.print("Wrong UserName.\n");
                    break;
            }
            login = true;
        }
        else if (message.equalsIgnoreCase("set Password")) {
            System.out.print("UserType: ");
            Scanner userTypeReader = new Scanner(System.in);
            String UserType = userTypeReader.nextLine();
            System.out.print("New Password: ");
            Scanner newPassword = new Scanner(System.in);
            String pwd = newPassword.nextLine();
            user.setPWD(user,UserType,pwd);
        } else if (message.equalsIgnoreCase("scan in product")){
            if (!(user instanceof Receiver)) {
                throw new OperationFailedException(user);
            }
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.addNew(upc);
        } else if (message.equalsIgnoreCase("Order")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.addNew(upc);
        } else if (message.equalsIgnoreCase("Scan")) {

            String upc = "";
            while (!upc.equalsIgnoreCase("quit")) {
                System.out.println("Product's UPC: ");
                Scanner UPCReader = new Scanner(System.in);
                upc = UPCReader.nextLine();
                try {
                    user.scan(Integer.valueOf(upc));
                } catch (Exception e) {
                    System.out.print("Wrong UPC!\n");
                }
            }
        } else if (message.equalsIgnoreCase("Cancel item")) {
            String upc = "";
            while (!upc.equalsIgnoreCase("quit")) {
                Scanner UPCReader = new Scanner(System.in);
                upc = UPCReader.nextLine();
                try {
                    user.cancel(Integer.valueOf(upc));
                } catch (Exception e) {
                    System.out.print("Wrong UPC!");
                }
            }
        } else if (message.equalsIgnoreCase("Purchase")) {
            String cardType = "";
            System.out.print("input payment type(debit, credit or cash): ");
            Scanner cardTypeReader = new Scanner(System.in);
            cardType = cardTypeReader.nextLine();
            if (cardType.equalsIgnoreCase("cash")) {
                user.checkOut(2, 0);
            } else if (cardType.equalsIgnoreCase("debit")) {
                Scanner cardNumReader = new Scanner(System.in);
                int cardNum = cardNumReader.nextInt();
                user.checkOut(1, cardNum);
            } else if (cardType.equalsIgnoreCase("credit")) {
                Scanner cardNumReader = new Scanner(System.in);
                int cardNum = cardNumReader.nextInt();
                user.checkOut(0, cardNum);
            }
        } else if (message.equalsIgnoreCase("Cancel")) {
            user.cancelAll();
        } else if (message.equalsIgnoreCase("profit History")) {
            user.viewProfit();
        } else if (message.equalsIgnoreCase("view Prize")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.viewPrize(upc);
        } else if (message.equalsIgnoreCase("Prize Histroy")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.viewPrizeHistory(upc);
        } else if (message.equalsIgnoreCase("View Location")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.viewLocation(upc);
        } else if (message.equalsIgnoreCase("set prize")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            System.out.print("New Prize: ");
            Scanner prize = new Scanner(System.in);
            int p = prize.nextInt();
            user.setPrize(p,upc);
        } else if (message.equalsIgnoreCase("View pending")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.viewPending(upc);
        } else if (message.equalsIgnoreCase("View section")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.viewSection(upc);
        } else if (message.equalsIgnoreCase("order history")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            int upc = ProUPC.nextInt();
            user.viewOrderHistory(upc);
        } else {
            System.out.println("Wrong Command.");
        }
    }
}
