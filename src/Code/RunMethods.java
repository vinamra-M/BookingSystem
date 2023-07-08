package Code;

import java.util.Scanner;

public class RunMethods {
     public synchronized static void AdminRun(){
         String passcode;
             Scanner sc = new Scanner(System.in);
             System.out.print("Enter Password: ");
             passcode = sc.nextLine();
        if(passcode.equals(AdminUser.getPassword())){
            int next;
            do {
                System.out.println("_________________________________________________");
                System.out.println("Give input in next line according to given key:");
                System.out.println("To View Next Application\t\t\t\t\t\t\t\t\t\t\t Enter i\nTo Approve Next Application\t\t\t\t\t\t\t\t\t\t\t Enter ii\nTo Reject Next Application\t\t\t\t\t\t\t\t\t\t\t Enter iii\nTo Ask More Details in Next Application\t\t\t\t\t\t\t\t Enter iv\nTo View Submitted Applications for a room in a given time period\t Enter v");
                System.out.println("_________________________________________________");
                String method = sc.nextLine();
                switch (method){
                    case "i" -> AdminUser.nextApplication();
                    case "ii" -> AdminUser.approve();
                    case "iii" -> AdminUser.reject();
                    case "iv" -> {
                        System.out.println("Enter details required in next line:");
                        String feedback = sc.nextLine();
                        AdminUser.askDetails(feedback);
                    }
                    case "v" -> {
                        System.out.print("Enter the room number: ");
                        String roomNumber = sc.nextLine();
                        System.out.print("Enter the start date and time with single space in between: ");
                        String[] d_t1 = sc.nextLine().split(" ");
                        String sDate = d_t1[0];
                        String sTime = d_t1[1];
                        System.out.print("Enter the start end date and time with single space in between: ");
                        String[] d_t2 = sc.nextLine().split(" ");
                        String eDate = d_t2[0];
                        String eTime = d_t2[1];
                        AdminUser.printSubmittedApplications(roomNumber, sDate, sTime, eDate, eTime);
                    }
                    default -> System.out.println("Invalid Input.");
                }
                String check;
                do {
                    System.out.println("****** To continue enter 1. To exit enter 0. All other inputs are invalid ******");
                    check = sc.nextLine();
                }while(!(check.equals("1")||check.equals("0")));
                next = Integer.parseInt(check);
            } while (next==1);
            System.out.println(".......................You have exited the Admin Mode of Room Booking System.......................");
        }
        else{
            System.out.println("The entered password is incorrect!");
        }
    }
    public synchronized static void UserRun(Application application){
         Scanner sc = new Scanner(System.in);
         int next;
         do{
             System.out.println("_________________________________________________");
             System.out.println("Give input in next line according to given key:");
             System.out.println("To Submit\t\t\t\t\t\tEnter i\nTo Cancel\t\t\t\t\t\tEnter ii\nTo Update Reason\t\t\t\tEnter iii\nTo Update Location\t\t\t\tEnter iv\nTo Update Start Time\t\t\tEnter v\nTo Update End Time\t\t\t\tEnter vi\nTo Update Required Capacity\t\tEnter vii\nTo Update Room Number\t\t\tEnter viii\nTo Update Organisation\t\t\tEnter ix\nTo Show Application Status\t\tEnter x\nTo Check Availability of Room\tEnter xi\nTo Show Booked Slots\t\t\tEnter xii\nTo Show Available Rooms\t\t\tEnter xiii\nTo View Application\t\t\t\tEnter xiv");
             System.out.println("_________________________________________________");
             String method = sc.nextLine();
             switch (method) {
                 case "i" -> application.submit();
                 case "ii" -> application.cancel();
                 case "iii" -> {
                     System.out.print("Enter the updated value: ");
                     String reason = sc.nextLine();
                     application.updateReason(reason);
                 }
                 case "iv" -> {
                     System.out.print("Enter the updated location and room number with single space in between: ");
                     String[] d_t = sc.nextLine().split(" ");
                     String location = d_t[0];
                     String roomNumber = d_t[1];
                     application.updateLocation(location, roomNumber);
                 }
                 case "v" -> {
                     System.out.print("Enter the updated date and time with single space in between: ");
                     String[] d_t = sc.nextLine().split(" ");
                     String date = d_t[0];
                     String time = d_t[1];
                     application.updateStartTime(date, time);
                 }
                 case "vi" -> {
                     System.out.print("Enter the updated date and time with single space in between: ");
                     String[] d_t = sc.nextLine().split(" ");
                     String date = d_t[0];
                     String time = d_t[1];
                     application.updateEndTime(date, time);
                 }
                 case "vii" -> {
                     System.out.print("Enter the updated value: ");
                     int reqCapacity = sc.nextInt();
                     application.updateReqCapacity(reqCapacity);
                 }
                 case "viii" -> {
                     System.out.print("Enter the updated value: ");
                     String newRoomNumber = sc.nextLine();
                     application.updateRoomNumber(newRoomNumber);
                 }
                 case "ix" -> {
                     System.out.print("Enter the updated value: ");
                     String organisation = sc.nextLine();
                     application.updateOrganisation(organisation);
                 }
                 case "x" -> application.showStatus();
                 case "xi" -> {
                     System.out.print("Enter Location: ");
                     String location = sc.nextLine();
                     System.out.print("Enter Room Number: ");
                     String roomNumber = sc.nextLine();
                     System.out.print("Enter Start Date: ");
                     String stDate = sc.nextLine();
                     System.out.print("Enter Start Time: ");
                     String stTime = sc.nextLine();
                     System.out.print("Enter End Date: ");
                     String edDate = sc.nextLine();
                     System.out.print("Enter End Time: ");
                     String edTime = sc.nextLine();
                     Location.isAvailable(location, roomNumber, stDate, stTime, edDate, edTime);
                 }
                 case "xiii" -> {
                     System.out.print("Enter Location: ");
                     String location1 = sc.nextLine();
                     System.out.print("Enter Start Date: ");
                     String stDate1 = sc.nextLine();
                     System.out.print("Enter Start Time: ");
                     String stTime1 = sc.nextLine();
                     System.out.print("Enter End Date: ");
                     String edDate1 = sc.nextLine();
                     System.out.print("Enter End Time: ");
                     String edTime1 = sc.nextLine();
                     Location.showAvailableRooms(location1, stDate1, stTime1, edDate1, edTime1);
                 }
                 case "xii" -> {
                     System.out.print("Enter Room Number: ");
                     String roomNumber1 = sc.nextLine();
                     Location.showBookedSlots(roomNumber1);
                 }
                 case "xiv" -> application.printApplication();
                 default -> {
                     System.out.print("Method entered is not valid. Valid methods are: ");
                     System.out.println("Submit/Cancel/Update Reason/Update Location/Update Start Time/Update Endx Time/Update Required Capacity/Update Room Number/Update Organisation/Show Application Status/Check Availability of Room/Show Booked Slots/Show Available Rooms/View Application");
                 }
             }
             String check;
             do {
                 System.out.println("****** To continue enter 1. To exit enter 0. All other inputs are invalid ******");
                 check = sc.nextLine();
             }while(!(check.equals("1")||check.equals("0")));
             next = Integer.parseInt(check);
         }while (next == 1);
         System.out.println(".......................You have exited the User Mode of Room Booking System.......................");
    }
}
