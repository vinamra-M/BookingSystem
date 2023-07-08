package Code;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class DriverMethods{
    static String toStringCal(Calendar calendar){
        return calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR)+","+calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE);
    }
    public static void Start() throws FileNotFoundException {
        StudentLoginAndRegister.register();
        UploadRooms.upload();
        ApplicationUpload.uploadApplications();
        String next;
        String next0;
        do{
            do{
                Scanner in1 = new Scanner(System.in);
                System.out.println("_____________________________________________________________________________________");
                System.out.println("||         * If you are a New User enter '0'.                                      ||");
                System.out.println("||         * If you are an Existing User with New application enter '1'.           ||");
                System.out.println("||         * If you are an Existing User with Saved application enter '2'.         ||");
                System.out.println("||         * If you are Admin enter '3'.                                           ||");
                System.out.println("-------------------------------------------------------------------------------------");
                next0 = in1.nextLine();
            }while(!(next0.equals("1")||next0.equals("2")||next0.equals("0")||next0.equals("3")));
            switch (next0){
                case "0"-> {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter Student Name: ");
                    String name = in.nextLine();
                    System.out.print("Enter Student ID: ");
                    String student_ID = in.nextLine();
                    boolean search = false;
                    for(Student s: RegisteredStudent.userList){
                        if (s.getStudent_ID().equals(student_ID)) {
                            search = true;
                            break;
                        }
                    }
                    if(search){
                        System.out.println("Student already registered.");
                    } else {
                        Student newUser = new Student(name, student_ID);
                        RegisteredStudent.registerUser(newUser);
                        System.out.println("Student successfully registered.");
                    }
                    System.out.println("Enter Application Details.");
                    System.out.print("Enter Organisation: ");
                    String organisation = in.nextLine();
                    System.out.print("Enter Reason: ");
                    String reason = in.nextLine();
                    System.out.print("Enter Required Capacity (enter only an integer): ");
                    int reqCapacity = Integer.parseInt(in.nextLine());
                    System.out.print("Enter start date in dd/mm/yyyy format: ");
                    String sD = in.nextLine();
                    System.out.print("Enter start time in hh:mm format: ");
                    String sT = in.nextLine();
                    System.out.print("Enter end date in dd/mm/yyyy format: ");
                    String eD = in.nextLine();
                    System.out.print("Enter end time in hh:mm format: ");
                    String eT = in.nextLine();
                    System.out.print("Enter Location (NAB/Library/LTC): ");
                    String location = in.nextLine();
                    System.out.print("Enter Room Number: ");
                    String roomNumber = in.nextLine();
                    Calendar startTime = Calendar.getInstance();
                    Calendar endTime = Calendar.getInstance();
                    String[] sDate = sD.split("/");
                    String[] sTime = sT.split(":");
                    String[] eDate = eD.split("/");
                    String[] eTime = eT.split(":");
                    startTime.set(Integer.parseInt(sDate[2]), Integer.parseInt(sDate[1]) - 1, Integer.parseInt(sDate[0]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]), 0);
                    endTime.set(Integer.parseInt(eDate[2]), Integer.parseInt(eDate[1]) - 1, Integer.parseInt(eDate[0]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]), 0);
                    Application newApplication = new Application(student_ID, organisation, reason, reqCapacity, startTime, endTime, roomNumber, location, "Application not Submitted");
                    ApplicationUpload.upload(newApplication);
                    newApplication.setApplication_ID(ApplicationUpload.getID(newApplication));
                    System.out.println("Application saved with application ID "+ApplicationUpload.getID(newApplication));
                    (new UserThread(ApplicationUpload.getID(newApplication))).run();
                }
                case "1" -> {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter Student ID: ");
                    String student_ID = in.nextLine();
                    boolean search = false;
                    for(Student s: RegisteredStudent.userList){
                        if (s.getStudent_ID().equals(student_ID)) {
                            search = true;
                            break;
                        }
                    }
                    if(search){
                        System.out.println("Enter Application Details.");
                        System.out.print("Enter Organisation: ");
                        String organisation = in.nextLine();
                        System.out.print("Enter Reason: ");
                        String reason = in.nextLine();
                        System.out.print("Enter Required Capacity (enter only an integer): ");
                        int reqCapacity = Integer.parseInt(in.nextLine());
                        System.out.print("Enter start date in dd/mm/yyyy format: ");
                        String sD = in.nextLine();
                        System.out.print("Enter start time in hh:mm format: ");
                        String sT = in.nextLine();
                        System.out.print("Enter end date in dd/mm/yyyy format: ");
                        String eD = in.nextLine();
                        System.out.print("Enter end time in hh:mm format: ");
                        String eT = in.nextLine();
                        System.out.print("Enter Location (NAB/Library/LTC): ");
                        String location = in.nextLine();
                        System.out.print("Enter Room Number: ");
                        String roomNumber = in.nextLine();
                        Calendar startTime = Calendar.getInstance();
                        Calendar endTime = Calendar.getInstance();
                        String[] sDate = sD.split("/");
                        String[] sTime = sT.split(":");
                        String[] eDate = eD.split("/");
                        String[] eTime = eT.split(":");
                        startTime.set(Integer.parseInt(sDate[2]), Integer.parseInt(sDate[1]) - 1, Integer.parseInt(sDate[0]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]), 0);
                        endTime.set(Integer.parseInt(eDate[2]), Integer.parseInt(eDate[1]) - 1, Integer.parseInt(eDate[0]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]), 0);
                        Application newApplication = new Application(student_ID, organisation, reason, reqCapacity, startTime, endTime, roomNumber, location, "Application not Submitted");
                        ApplicationUpload.upload(newApplication);
                        newApplication.setApplication_ID(ApplicationUpload.getID(newApplication));
                        System.out.println("Application saved with application ID "+ApplicationUpload.getID(newApplication));
                        (new UserThread(ApplicationUpload.getID(newApplication))).run();
                    }
                    else {
                        System.out.println("Student ID not found in records. Please register.");
                    }
                }
                case "2" -> {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter ID of the Application you want to access: ");
                    String applicationID = in.nextLine();
                    int application_ID = Integer.parseInt(applicationID);
                    if(application_ID<ApplicationUpload.ApplicationList.size()) {
                        (new UserThread(application_ID)).run();
                    }
                    else{System.out.println("Application with this ID does not exist.");}
                }
                case "3"-> {
                    {(new AdminThread()).run();}
                }
            }
            do{
                Scanner in1 = new Scanner(System.in);
                System.out.println("****** If you want to continue using the application enter 1. If you want to exit the application enter 0 ******");
                next = in1.nextLine();
            }while(!(next.equals("1")||next.equals("0")));
        }while (next.equals("1"));
        File file = new File("ApplicationFile");
        File studentFile = new File("StudentFile");
        try{
            FileWriter fw0 = new FileWriter(studentFile);
            for(Student s: RegisteredStudent.userList) {
                fw0.write(s.getName()+","+s.getStudent_ID()+"\n");
            }
            fw0.flush();
            fw0.close();
            FileWriter fw = new FileWriter(file);
            for(Application a:ApplicationUpload.ApplicationList){
                if(a.getApplicationStatus().equals("Application not Submitted")){
                    fw.write(a.getStudent_ID()+","+a.getOrganisation()+","+a.getReason()+","+a.getReqCapacity()+","+toStringCal(a.getStartTime())+","+toStringCal(a.getEndTime())+","+a.getRoomNumber()+","+a.getLocation()+","+a.getApplicationStatus()+"\n");
                }
                else{
                    fw.write(a.getStudent_ID()+","+a.getOrganisation()+","+a.getReason()+","+a.getReqCapacity()+","+toStringCal(a.getStartTime())+","+toStringCal(a.getEndTime())+","+a.getRoomNumber()+","+a.getLocation()+","+a.getApplicationStatus()+","+toStringCal(a.getSubmissionTime())+"\n");
                }
            }
            fw.flush();
            fw.close();
        }
        catch (IOException e){
            System.out.println("Input Output Error!!");
        }
    }
}
