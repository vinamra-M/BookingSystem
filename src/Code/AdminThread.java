package Code;

public class AdminThread extends Thread {
    private final Admin admin = new Admin();
    public AdminThread(){
    }
    public void run() {
        RunMethods.AdminRun();
    }
}
