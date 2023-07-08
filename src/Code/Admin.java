package Code;

import java.util.Calendar;

public class Admin {
    public Admin(){}
    static void approve(){
        synchronized (ApplicationUpload.class) {
            Application application = ApplicationUpload.SubmittedApplicationList.peek();
            if (application != null) {
                application.setApplicationStatus("Application approved.");
                ApplicationUpload.ApprovedApplicationList.add(application);
                ApplicationUpload.SubmittedApplicationList.poll();
                System.out.println("Application successfully approved.");
            } else {
                System.out.println("No application to be approved.");
            }
        }
    }
    static void reject(){
        synchronized (ApplicationUpload.class) {
            Application application = ApplicationUpload.SubmittedApplicationList.peek();
            if (application != null) {
                ApplicationUpload.RejectedApplicationList.add(application);
                ApplicationUpload.SubmittedApplicationList.poll();
                application.setApplicationStatus("Application rejected.");
                System.out.println("Application successfully rejected.");
            } else {
                System.out.println("No application to be rejected.");
            }
        }
    }
    static void askDetails(String feedback){
        synchronized (ApplicationUpload.class) {
            Application application = ApplicationUpload.SubmittedApplicationList.peek();
            if (application != null) {
                application.setApplicationStatus("Update after considering this feedback and resubmit: " + feedback);
                ApplicationUpload.SubmittedApplicationList.remove(application);
                System.out.println("Request to upload more details sent.");
            } else {
                System.out.println("No application to ask for more details.");
            }
        }
    }
    static void nextApplication(){
        synchronized (ApplicationUpload.class) {
            Application application = ApplicationUpload.SubmittedApplicationList.peek();
            if (application != null) {
                application.printApplication();
            } else {
                System.out.println("No application to view.");
            }
        }
    }
    static void printSubmittedApplications(String roomNumber, String stDate, String stTime, String edDate, String edTime){
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        String[] sDate = stDate.split("/");
        String[] sTime = stTime.split(":");
        String[] eDate = edDate.split("/");
        String[] eTime = edTime.split(":");
        startTime.set(Integer.parseInt(sDate[2]), Integer.parseInt(sDate[1])-1, Integer.parseInt(sDate[0]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]), 0);
        endTime.set(Integer.parseInt(eDate[2]), Integer.parseInt(eDate[1])-1, Integer.parseInt(eDate[0]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]), 0);
        boolean flag = false;
        synchronized (ApplicationUpload.class) {
            for (Application a : ApplicationUpload.SubmittedApplicationList) {
                if (a.getRoomNumber().equals(roomNumber) && a.getStartTime().compareTo(startTime) > 0 && a.getEndTime().compareTo(endTime) < 0) {
                    a.printApplication();
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("No submitted applications for this room in the given time period.");
            }
        }
    }
}
