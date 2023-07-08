package Code;

public class Student {
    private String name;
    private String student_ID;

    public String getStudent_ID() {
        return student_ID;
    }

    public String getName() {
        return name;
    }
    Student(String name, String student_ID)
    {
        this.name = name;
        this.student_ID = student_ID;
    }
    public String toString(){
        return ("Name: "+name+"\n"+"Student ID: "+student_ID);
    }
}
