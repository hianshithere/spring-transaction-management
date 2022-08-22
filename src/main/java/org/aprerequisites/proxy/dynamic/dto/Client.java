package org.aprerequisites.proxy.dynamic.dto;

import org.aprerequisites.proxy.dynamic.proxy.EmployeeSalaryInvocationHandler;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        ITEmployee itEmployee = new ITEmployee ();
        itEmployee.setId (101);
        itEmployee.setName ("anshit");
        itEmployee.setSalary (1_00_000.00);
        // question arises what if hike is -10_000; in hike() -> salary reduced ?
        // loads of explanations and validations required
        // segregate business logic and validation logic

        // invoking handler
        EmployeeSalaryInvocationHandler invocationHandler
                = new EmployeeSalaryInvocationHandler (itEmployee);
        // interfaces implementing this class to be proxied
        Class[] implementingInterfaces = {IEmployee.class};

//        itEmployee.giveHike (10_000);
        Object newProxyInstance = Proxy.newProxyInstance (
                itEmployee.getClass ().getClassLoader (),
                implementingInterfaces,
                invocationHandler
        );

        IEmployee employee = (IEmployee) newProxyInstance;

        employee.giveHike (-10_000);

        System.out.println (employee.getSalary ());

    }
}
