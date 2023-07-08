package Code;

public class AdminUser extends Admin {
    private static AdminUser obj = new AdminUser();
    private final String password;

    private AdminUser() {
        password = "1234";
    }

    public static String getPassword() {
        return obj.password;
    }
}
