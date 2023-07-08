package Code;

public interface Location {
    static boolean allotRoom(Application a){return false;}
    static void showBookedSlots(String roomNumber){
        System.out.println("The following slots are booked for room number "+roomNumber+":");
        for(int i=0; i<ApplicationUpload.ApprovedApplicationList.size(); i++){
            if(ApplicationUpload.ApprovedApplicationList.get(i).getRoomNumber().equals(roomNumber)){
                System.out.println(ApplicationUpload.ApprovedApplicationList.get(i).getStartTime().getTime()+" to "+ApplicationUpload.ApprovedApplicationList.get(i).getEndTime().getTime());
            }
        }
    }
    static void isAvailable(String location, String roomNumber, String stDate, String stTime, String edDate, String edTime){
        switch(location){
            case "NAB":
                NAB.isAvailable(roomNumber, stDate, stTime, edDate, edTime);
                break;
            case "LTC":
                LTC.isAvailable(roomNumber, stDate, stTime, edDate, edTime);
                break;
            case "Library":
                Library.isAvailable(roomNumber, stDate, stTime, edDate, edTime);
                break;
        }
    }
    static void showAvailableRooms(String location, String stDate, String stTime, String edDate, String edTime){
        switch(location){
            case "NAB":
                NAB.showAvailableRooms(stDate, stTime, edDate, edTime);
                break;
            case "LTC":
                LTC.showAvailableRooms(stDate, stTime, edDate, edTime);
                break;
            case "Library":
                Library.showAvailableRooms(stDate, stTime, edDate, edTime);
                break;
        }
    }
}
