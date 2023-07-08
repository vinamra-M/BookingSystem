package Code;

import java.util.HashSet;

public class RegisteredStudent {
    static HashSet<Student> userList = new HashSet<>(); //can use arraylist and assign application id
    static void registerUser(Student std)
    {
        userList.add(std);
    }
    static Student getStudent(String student_ID){
        for(Student e : userList)
        {
            if(e.getStudent_ID().equals(student_ID))
            {
                return e;
            }
        }
        return new Student(null,null);
    }
    static void printUserList(){for(Student s: userList){System.out.println(s);}}
}
