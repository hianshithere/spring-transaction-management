package org.aprerequisites.proxy.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.aprerequisites.proxy.dynamic.dto.IEmployee;

public class EmployeeSalaryInvocationHandler implements InvocationHandler {

  private IEmployee employeeTarget;

  public EmployeeSalaryInvocationHandler(IEmployee employeeTarget) {
    this.employeeTarget = employeeTarget;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    preprocessing(method, args);
    return postprocessing(method, args);
  }

  private void preprocessing(Method method, Object[] args) {

    String methodName = method.getName().toLowerCase();

    if (methodName.contains("name")) {
      String name = (String) args[0];
      System.out.println("printing from invocation handler: " + name);
    }

    if (methodName.contains("hike")) {
      double bonusAmount = (double) args[0];
      if (bonusAmount < 0) {
        throw new RuntimeException("bonus cannot be negative..");
      }
    }
  }

  private Object postprocessing(Method method, Object[] args)
      throws IllegalAccessException, InvocationTargetException {

    return method.invoke(employeeTarget, args);
  }
}
