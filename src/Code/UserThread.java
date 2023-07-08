package Code;

public class UserThread extends Thread {
    private final Application application;
    public UserThread(int application_ID){
        Application application1 = ApplicationUpload.ApplicationList.get(application_ID);
        this.application = application1;
    }
    public void run(){
        RunMethods.UserRun(application);
    }
}
