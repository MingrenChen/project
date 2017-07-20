/**
 * Created by chenmi84 on 18/07/17.
 */
public class Receiver extends User{
    private static String password = "Receiver";

    Receiver(String pwd) throws OperationFailedException {
        if (!pwd.equals(password)) {
            throw new OperationFailedException("Wrong Password");
        } else {
            super.setLogin();
        }
    }

    public static void setPassword(String password) {
        Receiver.password = password;
    }
}
