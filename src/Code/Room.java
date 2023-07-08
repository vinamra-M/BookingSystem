package Code;

import java.util.Arrays;

public class Room {
    private String roomNumber;
    private int capacity;
    private String[] facilities;
    Room(String roomNumber, int capacity, String[] facilities)
    {
        this.capacity = capacity;
        this.facilities = facilities;
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String[] getFacilities() {
        return facilities;
    }
    public String toString(){
        return ("Room Number: "+roomNumber+" Capacity: "+capacity+" Facilities: "+ Arrays.toString(facilities));
    }
}
