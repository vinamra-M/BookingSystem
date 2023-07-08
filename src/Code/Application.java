package Code;

import java.util.Calendar;

public class Application {
    private String organisation;
    private String student_ID;
    private String reason;
    private Calendar startTime;
    private Calendar endTime;
    private Calendar submissionTime;
    private int reqCapacity;
    private String roomNumber;
    private String location;
    private Student student;
    private String applicationStatus;
    private int application_ID;

    public int getApplication_ID() {
        return application_ID;
    }

    public void setApplication_ID(int application_ID) {
        this.application_ID = application_ID;
    }

    public int getReqCapacity() {
        return reqCapacity;
    }

    public String getReason() {
        return reason;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getRoomNumber(){return roomNumber;}
    public String getLocation(){return location;}
    public Student getStudent(){return student;}
    public String getApplicationStatus(){return applicationStatus;}

    public Calendar getSubmissionTime() {
        return submissionTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setReqCapacity(int reqCapacity) {
        this.reqCapacity = reqCapacity;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }
    public void setApplicationStatus(String status){this.applicationStatus = status;}
    public void setSubmissionTime(Calendar submissionTime){this.submissionTime=submissionTime;}
    Application(String student_ID, String organisation, String reason, int reqCapacity, Calendar startTime, Calendar endTime, String roomNumber, String location, String applicationStatus)
    {
        this.endTime = endTime;
        this.organisation = organisation;
        this.student_ID = student_ID;
        student = RegisteredStudent.getStudent(student_ID);
        this.reason = reason;
        this.reqCapacity = reqCapacity;
        this.startTime = startTime;
        this.roomNumber = roomNumber;
        this.location = location;
        this.applicationStatus = applicationStatus;
    }
    public String toString(){
        return ("ID: "+student_ID+"\n"+"Organisation: "+organisation+"\n"+"Reason: "+reason+"\n"+"Required Capacity: "+reqCapacity+"\n"+"Start Time: "+startTime.getTime()+"\n"+"End Time: "+endTime.getTime()+"\n"+"Location: "+location+"\n"+"Room Number: "+roomNumber);
    }
    void printApplication(){
        System.out.println(student);
        System.out.println("Organisation: "+organisation);
        System.out.println("Reason: "+reason);
        System.out.println("Requested Time Slot: "+startTime.getTime()+" to "+endTime.getTime());
        System.out.println("Requested Room: "+location+" "+roomNumber);
        System.out.println("Required Capacity: "+reqCapacity);
        System.out.println("_______________________________________________");
    }
    synchronized void submit()
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.SubmittedApplicationList.contains(this)) {
                System.out.println("Application already submitted!!");
            } else {
                switch (this.getLocation()) {
                    case "NAB":
                        if (NAB.allotRoom(this)) {
                            this.setSubmissionTime(Calendar.getInstance());
                            ApplicationUpload.SubmittedApplicationList.add(this);
                            this.setApplicationStatus("Application Submitted for Approval");
                            System.out.println("Application Submitted with Application ID: " + ApplicationUpload.getID(this));
                            System.out.println("You can cancel the application anytime before it is approved.");
                        } else {
                            System.out.println("Room not available");
                        }
                        break;
                    case "Library":
                        if (Library.allotRoom(this)) {
                            this.setSubmissionTime(Calendar.getInstance());
                            ApplicationUpload.SubmittedApplicationList.add(this);
                            this.setApplicationStatus("Application Submitted for Approval");
                            System.out.println("Application Submitted with Application ID: " + ApplicationUpload.getID(this));
                            System.out.println("You can cancel the application anytime before it is approved.");
                        } else {
                            System.out.println("Room not available");
                        }
                        break;
                    case "LTC":
                        if (LTC.allotRoom(this)) {
                            this.setSubmissionTime(Calendar.getInstance());
                            ApplicationUpload.SubmittedApplicationList.add(this);
                            this.setApplicationStatus("Application Submitted for Approval");
                            System.out.println("Application Submitted with Application ID: " + ApplicationUpload.getID(this));
                            System.out.println("You can cancel the application anytime before it is approved.");
                        } else {
                            System.out.println("Room not available");
                        }
                        break;
                    default:
                        System.out.println("Location specified does not exist.");
                }
            }
        }
    }
    synchronized void cancel(){
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                ApplicationUpload.ApprovedApplicationList.remove(this);
                ApplicationUpload.CancelledApplicationList.add(this);
                this.setApplicationStatus("Application cancelled.");
                System.out.println("Application successfully cancelled.");
            } else if (ApplicationUpload.CancelledApplicationList.contains(this)) {
                System.out.println("Application has been already cancelled!!");
            } else if (ApplicationUpload.SubmittedApplicationList.contains(this)) {
                ApplicationUpload.SubmittedApplicationList.remove(this);
                ApplicationUpload.CancelledApplicationList.add(this);
                this.setApplicationStatus("Application cancelled.");
                System.out.println("Application successfully cancelled.");
            } else {
                System.out.println("Application has not been submitted yet!!");
            }
        }
    }
    synchronized void updateRoomNumber(String newRoomNumber)
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                System.out.println("Application already approved. Cannot be Edited!!");
            } else {
                this.setRoomNumber(newRoomNumber);
            }
        }
    }
    synchronized void updateReason(String reason)
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                System.out.println("Application already approved. Cannot be Edited!!");
            } else {
                this.setReason(reason);
            }
        }
    }
    synchronized void updateOrganisation(String organisation)
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                System.out.println("Application already approved. Cannot be Edited!!");
            } else {
                this.setOrganisation(organisation);
            }
        }
    }
    synchronized void updateReqCapacity(int reqCapacity)
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                System.out.println("Application already approved. Cannot be Edited!!");
            } else {
                this.setReqCapacity(reqCapacity);
            }
        }
    }
    synchronized void updateStartTime(String date, String time)
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                System.out.println("Application already approved. Cannot be Edited!!");
            } else {
                String[] sDate = date.split("/");
                String[] sTime = time.split(":");
                Calendar startTime = Calendar.getInstance();
                startTime.set(Integer.parseInt(sDate[2]), Integer.parseInt(sDate[1]) - 1, Integer.parseInt(sDate[0]), Integer.parseInt(sTime[0]), Integer.parseInt(sTime[1]), 0);
                this.setStartTime(startTime);
            }
        }
    }
    synchronized void updateEndTime(String date, String time)
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                System.out.println("Application already approved. Cannot be Edited!!");
            } else {
                String[] eDate = date.split("/");
                String[] eTime = time.split(":");
                Calendar endTime = Calendar.getInstance();
                endTime.set(Integer.parseInt(eDate[2]), Integer.parseInt(eDate[1]) - 1, Integer.parseInt(eDate[0]), Integer.parseInt(eTime[0]), Integer.parseInt(eTime[1]), 0);
                this.setEndTime(endTime);
            }
        }
    }
    synchronized void updateLocation(String location, String roomNumber)
    {
        synchronized (ApplicationUpload.class) {
            if (ApplicationUpload.ApprovedApplicationList.contains(this)) {
                System.out.println("Application already approved. Cannot be Edited!!");
            } else {
                this.setLocation(location);
                this.setRoomNumber(roomNumber);
            }
        }
    }
    void showStatus(){
        System.out.println(this.applicationStatus);
    }
}
