package org.aprerequisites.proxy;

import java.util.Date;

public class Attendance {

    private Date date;
    private boolean isPresent;

    public Attendance(Date date, boolean isPresent) {
        this.date = date;
        this.isPresent = isPresent;
    }

    public boolean isPresent() {
        return isPresent;
    }
}
