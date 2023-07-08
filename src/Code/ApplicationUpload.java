package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ApplicationUpload {
    static ArrayList<Application> ApplicationList = new ArrayList<>();
    static PriorityQueue<Application> SubmittedApplicationList = new PriorityQueue<>(10000, new ApplicationComparator());
    static ArrayList<Application> CancelledApplicationList = new ArrayList<>();
    static ArrayList<Application> ApprovedApplicationList = new ArrayList<>();
    static ArrayList<Application> RejectedApplicationList = new ArrayList<>();
    static void upload(Application application)
    {
        ApplicationList.add(application);
    }
    static void uploadApplications() throws FileNotFoundException
    {
        Scanner fs = new Scanner(new File("ApplicationFile"));
        while(fs.hasNextLine())
        {
            String[] info = fs.nextLine().split(",");
            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            String[] sDate = info[4].split("/");
            String[] sTime = info[5].split(":");
            String[] eDate = info[6].split("/");
            String[] eTime = info[7].split(":");
            startTime.set(Integer.parseInt(sDate[2]), Integer.parseInt(sDate[1])-1, Integer.parseInt(sDate[0]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]), 0);
            endTime.set(Integer.parseInt(eDate[2]), Integer.parseInt(eDate[1])-1, Integer.parseInt(eDate[0]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]), 0);
            Application newApplication = new Application(info[0], info[1], info[2], Integer.parseInt(info[3]), startTime, endTime, info[8], info[9], info[10]);
            if(info[10].equals("Application not Submitted")) {
                upload(newApplication);
                newApplication.setApplication_ID(getID(newApplication));
            }
            else{
                String[] subDate = info[11].split("/");
                String[] subTime = info[12].split(":");
                Calendar submissionTime = Calendar.getInstance();
                submissionTime.set(Integer.parseInt(subDate[2]), Integer.parseInt(subDate[1])-1, Integer.parseInt(subDate[0]), Integer.parseInt(subTime[0]), Integer.parseInt(subTime[1]), 0);
                newApplication.setSubmissionTime(submissionTime);
                upload(newApplication);
                newApplication.setApplication_ID(getID(newApplication));
                switch (newApplication.getApplicationStatus()) {
                    case "Application Submitted for Approval" -> SubmittedApplicationList.add(newApplication);
                    case "Application cancelled." -> CancelledApplicationList.add(newApplication);
                    case "Application approved." -> ApprovedApplicationList.add(newApplication);
                    case "Application rejected." -> RejectedApplicationList.add(newApplication);
                }
            }
        }
    }
    static int getID(Application application){
        return ApplicationList.indexOf(application);
    }
    static void printApplicationList(){
        for(Application a: ApplicationList){System.out.println(a.toString());}
    }
    static void printCancelledApplicationList(){
        for(Application a: CancelledApplicationList){a.printApplication();}
    }
    static void printSubmittedApplicationList(){
        for(Application a: SubmittedApplicationList){a.printApplication();}
    }
    static void printApprovedApplicationList(){
        for(Application a: ApprovedApplicationList){a.printApplication();}
    }
}