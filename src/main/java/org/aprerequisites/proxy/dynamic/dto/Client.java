package org.aprerequisites.proxy.dynamic.dto;

import java.lang.reflect.Proxy;
import org.aprerequisites.proxy.dynamic.proxy.EmployeeSalaryInvocationHandler;

public class Client {

  public static void main(String[] args) {

    /**
     * Stage 1: Without Proxy Client (HR) will talk directly to main object (ITEmployee) Business
     * logic and validation logic both are mixed up
     */
    ITEmployee itEmployee1 = new ITEmployee();
    itEmployee1.setId(101);
    itEmployee1.setName("anshit");
    itEmployee1.setSalary(1_00_000.00);

    /* question arises what if hike is -10_000; in hike() -> salary reduced ?
        loads of explanations and validations required
        segregate business logic and validation logic
    */

    /***
     * Solution: Stage 2: With Proxy Client (HR) will talk to proxy (EmployeeSalaryInvocationHandler)
     * which will validate and then talk to main object (ITEmployee), validation logic is separated
     * from business logic, hence code is cleaner.
     *
     */

    ITEmployee  itEmployee = new ITEmployee();
    itEmployee.setId(101);
    itEmployee.setName("anshit");
    itEmployee.setSalary(1_00_000.00);

    EmployeeSalaryInvocationHandler invocationHandler =
        new EmployeeSalaryInvocationHandler(itEmployee);
    /**
     * Tell JVM to create a proxy instance for the given interfaces
     */

    Class[] implementingInterfaces = {IEmployee.class};

    Object newProxyInstance =
        Proxy.newProxyInstance(
            itEmployee.getClass().getClassLoader(), implementingInterfaces, invocationHandler);

    IEmployee employee = (IEmployee) newProxyInstance;
    employee.giveHike(-10_000);
    System.out.println(employee.getSalary());
  }
}
