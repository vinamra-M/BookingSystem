package Code;

import java.util.ArrayList;
import java.util.Calendar;

public class Library implements Location
{
    static ArrayList<Room> LibraryRooms = new ArrayList<>();
    public static boolean allotRoom(Application a)
    {
        for(Room e : LibraryRooms)
        {
            if(e.getRoomNumber().equals(a.getRoomNumber()) && e.getCapacity()>=a.getReqCapacity())
            {
                boolean flag = true;
                for(Application aa: ApplicationUpload.ApprovedApplicationList)
                {
                    if(aa.getRoomNumber().equals(a.getRoomNumber()))
                    {
                        if((a.getStartTime().compareTo(aa.getEndTime()) > 0 && a.getEndTime().compareTo(aa.getEndTime())>0 || a.getStartTime().compareTo(aa.getStartTime())<0 && a.getEndTime().compareTo(aa.getStartTime())<0))
                        {
                            flag = true;
                        }
                        else{flag=false; break;}
                    }
                }
                return flag;
            }
            else continue;
        }
        return false;
    }
    public static void isAvailable(String roomNumber, String stDate, String stTime, String edDate, String edTime){
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        String[] sDate = stDate.split("/");
        String[] sTime = stTime.split(":");
        String[] eDate = edDate.split("/");
        String[] eTime = edTime.split(":");
        startTime.set(Integer.parseInt(sDate[2]), Integer.parseInt(sDate[1])-1, Integer.parseInt(sDate[0]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]), 0);
        endTime.set(Integer.parseInt(eDate[2]), Integer.parseInt(eDate[1])-1, Integer.parseInt(eDate[0]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]), 0);
        for(Room e: LibraryRooms){
            if(e.getRoomNumber().equals(roomNumber)){
                boolean flag = true;
                for(Application aa: ApplicationUpload.ApprovedApplicationList)
                {
                    if(aa.getRoomNumber().equals(roomNumber))
                    {
                        if((startTime.compareTo(aa.getEndTime()) > 0 && endTime.compareTo(aa.getEndTime())>0 || startTime.compareTo(aa.getStartTime())<0 && endTime.compareTo(aa.getStartTime())<0))
                        {
                            flag = true;
                        }
                        else{flag=false; break;}
                    }
                }
                if(flag){System.out.println("Room is Available");}
                else{System.out.println("Room is not Available");}
            }
            else
            {System.out.println("Room Number not Valid");}
        }
    }
    public static void showAvailableRooms(String stDate, String stTime, String edDate, String edTime){
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        String[] sDate = stDate.split("/");
        String[] sTime = stTime.split(":");
        String[] eDate = edDate.split("/");
        String[] eTime = edTime.split(":");
        startTime.set(Integer.parseInt(sDate[2]), Integer.parseInt(sDate[1])-1, Integer.parseInt(sDate[0]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]), 0);
        endTime.set(Integer.parseInt(eDate[2]), Integer.parseInt(eDate[1])-1, Integer.parseInt(eDate[0]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]), 0);
        for(Room e: LibraryRooms){
            boolean flag = false;
            System.out.println("The available rooms in Library for this time slot are:");
            for(Application aa: ApplicationUpload.ApprovedApplicationList)
            {
                if((startTime.compareTo(aa.getEndTime()) > 0 && endTime.compareTo(aa.getEndTime())>0 || startTime.compareTo(aa.getStartTime())<0 && endTime.compareTo(aa.getStartTime())<0))
                {
                    flag = true;
                }
            }
            if(!flag){
                System.out.println(e.getRoomNumber());
            }
        }
    }
}