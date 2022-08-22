package org.aprerequisites.proxy;

// proxy
public class StudentProxy extends Student {

    public StudentProxy(Attendance attendance) {
        super (attendance);
    }

    @Override
    public void attendSession() {
        if (!getAttendance ().isPresent ()) {
            throw new RuntimeException ("Student is absent and session is denied for this student..");
        } else {
            super.attendSession ();
        }
    }


}
