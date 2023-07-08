package Driver;
import Code.Admin;
import Code.DriverMethods;

import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        try{
            DriverMethods.Start();
        }catch (FileNotFoundException e){
            System.out.println("The required files not found.");
        }
    }
}
