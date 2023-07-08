package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UploadRooms {
    static void upload() throws FileNotFoundException
    {
        Scanner fs = new Scanner(new File("RoomFile.csv"));
        while(fs.hasNextLine())
        {
            String[] details = fs.nextLine().split(",");
            String[] facility = new String[details.length-3];
            for(int i=0; i< facility.length; i++)
            {
                facility[i] = details[i+3];
            }
            Room room = new Room(details[1], Integer.parseInt(details[2]), facility);
            switch (details[0]) {
                case "NAB" -> NAB.NABRooms.add(room);
                case "LTC" -> LTC.LTCRooms.add(room);
                case "Library" -> Library.LibraryRooms.add(room);
            }
        }
    }
}
