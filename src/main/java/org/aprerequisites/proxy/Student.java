package org.aprerequisites.proxy;

public class Student implements DailySession {

    private Attendance attendance;

    public Student(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public void attendSession() {
        // can add validation or let's talk about proxy from here
        System.out.println ("student attending the session..");
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
