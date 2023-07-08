package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class StudentLoginAndRegister
{
    static void register() throws FileNotFoundException
    {
        Scanner fs = new Scanner(new File("StudentFile"));
        while(fs.hasNextLine())
        {
            String[] credentials = fs.nextLine().split(",");
            Student newUser = new Student(credentials[0], credentials[1]);
            RegisteredStudent.registerUser(newUser);
        }
    }
}
