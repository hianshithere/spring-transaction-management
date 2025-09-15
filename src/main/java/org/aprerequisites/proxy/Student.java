package org.aprerequisites.proxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Student implements DailySession {

  @Getter
  @Setter
  private Attendance attendance;

  @Override
  public void attendSession() {
    System.out.println("student is attending the session..");
  }
}
