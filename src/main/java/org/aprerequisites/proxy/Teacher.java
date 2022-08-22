package org.aprerequisites.proxy;

import java.util.Date;

public class Teacher {
    public static void main(String[] args) {
        /**  Stage 1 **/
//        Student student = new Student (new Attendance (new Date (), false));
//        student.attendSession ();
        /**  Stage 2 **/
        // client (Teacher) will not talk directly to main object it will talk to proxy
        // to get to the main object
//        Student student = new StudentProxy (new Attendance (new Date (), false));
//        student.attendSession ();
        /**  Stage 3 **/
        /** Concept of protection proxy */
        DailySession session = new StudentProxy(new Attendance (new Date (), false));
        session.attendSession ();
    }
}
