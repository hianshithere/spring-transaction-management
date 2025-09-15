package org.aprerequisites.proxy;

/** Proxy class to control access to Student's attendSession method based on attendance status. */
public class StudentProxy extends Student {

  public StudentProxy(Attendance attendance) {
    super(attendance);
  }

  @Override
  public void attendSession() {
    validateAttendanceAndProceed();
  }

  /**
   * Validates the attendance status before allowing the student to attend the session. This
   * validation can be of anything but for now we check student's attendance.
   *
   * <p>Throws a RuntimeException if the student is absent.
   */
  private void validateAttendanceAndProceed() {
    if (!getAttendance().isPresent()) {
      throw new RuntimeException("Student is absent and session is denied for this student..");
    } else {
      super.attendSession();
    }
  }
}
