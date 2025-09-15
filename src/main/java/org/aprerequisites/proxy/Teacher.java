package org.aprerequisites.proxy;

import java.util.Date;

public class Teacher {

  public static void main(String[] args) {

    Attendance attendance = new Attendance(new Date(), false);

    /** Stage 1: Without Proxy */
    Student student = new Student(attendance);
    /** Direct call: Student is absent and session is denied for this student */
    student.attendSession();

    /**
     * Stage 2 client (Teacher) will not talk directly to main object it will talk to proxy to get
     * to the main object
     */
    Student student2 = new StudentProxy(attendance);
    student2.attendSession();

    /** Stage 3: Client (Teacher) will only talk to proxy through protection proxy */
    DailySession session = new StudentProxy(attendance);
    session.attendSession();
  }
}
