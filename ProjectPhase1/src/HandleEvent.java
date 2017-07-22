import com.sun.webkit.dom.RectImpl;
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
                    System.out.println("You login as a mananger, " +
                            "commands: View pending, view profit," +
                            " view revenue, profit history,.");
                    break;
                case "Cashier":
                    user = new Cashier(pwd);
                    System.out.println("You login as a cashier, " +
                            "commands: scan, pending orders, cancel " +
                            "item, purchase, cancel, view discount");
                    break;
                case "Reshelver":
                    user = new Reshelver(pwd);
                    System.out.println("You login as a reshelver, " +
                            "commands: view Location, change aisles," +
                            "View section, item number");
                    break;
                case "Receiver":
                    user = new Receiver(pwd);
                    System.out.println("You login as a receiver, commands: " +
                            "scan in product, add product, View Location," +
                            " Prize History, view prize, view cost, set prize. ");
                    break;
                default:
                    System.out.print("Wrong UserName.\n");
                    break;
            }
            login = true;
        } else if (message.equalsIgnoreCase("set Password")) {
            System.out.print("UserType: ");
            Scanner userTypeReader = new Scanner(System.in);
            String UserType = userTypeReader.nextLine();
            System.out.print("New Password: ");
            Scanner newPassword = new Scanner(System.in);
            String pwd = newPassword.nextLine();
            user.setPWD(user, UserType, pwd);
        } else if (message.equalsIgnoreCase("scan in product")) {
            if (!(user instanceof Receiver)) {
                throw new OperationFailedException(user);
            }
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            String upc = ProUPC.nextLine();
            ((Receiver) user).addNew(upc);
        }   else if (message.equalsIgnoreCase("Scan")) {
            if (!(user instanceof Cashier)) {
                throw new OperationFailedException(user);
            }
            String upc = "";
            while (!upc.equalsIgnoreCase("quit")) {
                System.out.println("Product's UPC: ");
                Scanner UPCReader = new Scanner(System.in);
                upc = UPCReader.nextLine();
                ((Cashier)user).scan(upc);
            }
        } else if (message.equalsIgnoreCase("Cancel item")) {
            if (!(user instanceof Cashier)) {
                throw new OperationFailedException(user);
            }
            String upc = "";
            Scanner UPCReader = new Scanner(System.in);
            upc = UPCReader.nextLine();
            ((Cashier) user).cancelOne(upc);

        } else if (message.equalsIgnoreCase("Purchase")) {
            if (!(user instanceof Cashier)) {
                throw new OperationFailedException(user);
            }
            String cardType = "";
            System.out.print("input payment type(debit, credit or cash): ");
            Scanner cardTypeReader = new Scanner(System.in);
            cardType = cardTypeReader.nextLine();
            if (cardType.equalsIgnoreCase("cash")) {
                ((Cashier) user).checkOut(2,0);
            } else if (cardType.equalsIgnoreCase("debit")) {
                Scanner cardNumReader = new Scanner(System.in);
                int cardNum = cardNumReader.nextInt();
                ((Cashier) user).checkOut(1,cardNum);
            } else if (cardType.equalsIgnoreCase("credit")) {
                Scanner cardNumReader = new Scanner(System.in);
                int cardNum = cardNumReader.nextInt();
                ((Cashier) user).checkOut(0,cardNum);
            }
        }   else if (message.equalsIgnoreCase("Cancel")) {
            if (!(user instanceof Cashier)) {
                throw new OperationFailedException(user);
            }
            ((Cashier) user).cancelAll();
        }   else if (message.equalsIgnoreCase("profit History")) {
            if(user instanceof Mananger) {
                ((Mananger)user).viewProfit();
            } else {
                throw new OperationFailedException(user);
            }
        }   else if (message.equalsIgnoreCase("view Prize")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            String upc = ProUPC.nextLine();
            if(user instanceof Receiver){
                ((Receiver)user).viewPrize(upc);
            }
        }   else if (message.equalsIgnoreCase("Prize Histroy")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            String upc = ProUPC.nextLine();
            if(user instanceof Receiver) {
                ((Receiver)user).viewPrizeHistory(upc);
            } else {
                throw new OperationFailedException(user);
            }
        }   else if (message.equalsIgnoreCase("View Location")) {
            if(user instanceof Receiver) {
                System.out.print("Product's UPC: ");
                Scanner ProUPC = new Scanner(System.in);
                String upc = ProUPC.nextLine();
                ((Receiver) user).viewLocation(upc);
            } else if (user instanceof Reshelver) {
                System.out.print("Product's UPC: ");
                Scanner ProUPC = new Scanner(System.in);
                String upc = ProUPC.nextLine();
                ((Reshelver) user).viewLocation(upc);
            } else {
                throw new OperationFailedException(user);
            }
        } else if (message.equalsIgnoreCase("change aisles")) {
            if (!(user instanceof Reshelver)) {
                throw new OperationFailedException(user);
            }
            System.out.println("Change aisle: ");
            Scanner a1 = new Scanner(System.in);
            int a = a1.nextInt();
            System.out.println("and aisle: ");
            Scanner a2 = new Scanner(System.in);
            int b = a2.nextInt();
            ((Reshelver) user).changeAisle(a, b);
        }
        else if (message.equalsIgnoreCase("set prize")) {
            if (!(user instanceof Mananger)) {
                throw new OperationFailedException(user);
            }
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            String upc = ProUPC.nextLine();
            System.out.print("New Prize: ");
            Scanner prize = new Scanner(System.in);
            int p = prize.nextInt();
            ((Mananger) user).setPrize(upc, p);
        } else if (message.equalsIgnoreCase("View pending")) {

            if(user instanceof Mananger) {
                System.out.print("Product's UPC: ");
                Scanner ProUPC = new Scanner(System.in);
                String upc = ProUPC.nextLine();
                ((Mananger) user).viewPending(upc);
            } else {
                throw new OperationFailedException(user);
            }
        } else if (message.equalsIgnoreCase("View Cost")) {

            if(user instanceof Receiver) {
                System.out.print("Product's UPC: ");
                Scanner ProUPC = new Scanner(System.in);
                String upc = ProUPC.nextLine();
                ((Receiver) user).viewCost(upc);
            } else {
                throw new OperationFailedException(user);
            }
        } else if (message.equalsIgnoreCase("View section")) {
            if(user instanceof Reshelver) {
                System.out.print("Product's UPC: ");
                Scanner ProUPC = new Scanner(System.in);
                String upc = ProUPC.nextLine();
                ((Reshelver) user).viewLocation(upc);
            } else {
                throw new OperationFailedException(user);
            }
        } else if (message.equalsIgnoreCase("order history")) {
            System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            String upc = ProUPC.nextLine();
            if (user instanceof Reshelver) {
                ((Reshelver) user).viewOrderHistory(upc);
            } else {
                throw new OperationFailedException(user);
            }
        }   else if (message.equalsIgnoreCase("item number")) {
            if (user instanceof Reshelver) {
                System.out.print("Product's UPC: ");
                Scanner ProUPC = new Scanner(System.in);
                String upc = ProUPC.nextLine();
                ((Reshelver) user).viewItemNum(upc);
            } else {
                throw new OperationFailedException(user);
            }
        }   else if (message.equalsIgnoreCase("pending orders")) {
            if (user instanceof Mananger) {
                ((Mananger) user).viewAllPendingOrder();
            } throw new OperationFailedException(user);

        } else if (message.equalsIgnoreCase("view discount")) {
            if (!(user instanceof Cashier)) {
                throw new OperationFailedException(user);
            } System.out.print("Product's UPC: ");
            Scanner ProUPC = new Scanner(System.in);
            String upc = ProUPC.nextLine();
            ((Cashier) user).viewDiscount(upc);
        } else if (message.equalsIgnoreCase("view revenue")) {
            if(user instanceof Mananger) {
                System.out.print("Product's UPC: ");
                Scanner ProUPC = new Scanner(System.in);
                String upc = ProUPC.nextLine();
                ((Mananger) user).viewRevenue();
            } else {
                throw new OperationFailedException(user);
            }
        } else if (message.equalsIgnoreCase("view num")){
            if(user instanceof Receiver) {
                ((Receiver) user).getProNum();
            }
        } else if (message.equalsIgnoreCase("add Product")) {
            if (!(user instanceof Receiver)) {
                throw new OperationFailedException(user);
            }
            System.out.println("Input a new product's name:");
            Scanner productNameReader = new Scanner(System.in);
            String proName = productNameReader.nextLine();
            System.out.println("Input a new product's cost:");
            Scanner productCost = new Scanner(System.in);
            double proCost = productNameReader.nextDouble();
            System.out.println("Input a new product's prize");
            Scanner productPirze = new Scanner(System.in);
            double proPrize = productNameReader.nextDouble();
            System.out.println("Input a new product's aisle:");
            Scanner productAisle = new Scanner(System.in);
            int proAisle = productAisle.nextInt();
            System.out.println("Input a new product's section:");
            Scanner productSecReader = new Scanner(System.in);
            String proSec = productSecReader.nextLine();
            System.out.println("Input a new product's subsection:");
            Scanner productSubSecReader = new Scanner(System.in);
            String proSubSec = productSubSecReader.nextLine();
            System.out.println("Input a new product's threshold:");
            Scanner productThreshold = new Scanner(System.in);
            int proThreshold = productThreshold.nextInt();
            System.out.println("Input a new product's Distributor:");
            Scanner productDistributor = new Scanner(System.in);
            String productDis = productDistributor.nextLine();
            ((Receiver) user)
                .addProduct(proCost, proName, proPrize, proSec, proSubSec, proAisle, proThreshold,
                    productDis);
        }else{
                System.out.println("Wrong Command.");
            }
        }
    }
