/**
 * Created by chenmi84 on 18/07/17.
 */
public class Mananger extends User {
    private static String password = "Mananger";

    Mananger(String pwd) throws OperationFailedException {
        if (!pwd.equals(password)) {
            throw new OperationFailedException("Wrong Password");
        } else {
            super.setLogin();
        }
    }

    static void setPassword(String pwd) {
        Mananger.password = pwd;
    }
}
