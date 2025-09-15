package org.aprerequisites.proxy;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    private Date date;

    @Getter
    private boolean isPresent;

}
