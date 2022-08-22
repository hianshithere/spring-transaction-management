package org.aprerequisites.proxy.dynamic.proxy;

import org.aprerequisites.proxy.dynamic.dto.IEmployee;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EmployeeSalaryInvocationHandler implements InvocationHandler {

    private IEmployee employeeTarget;

    public EmployeeSalaryInvocationHandler(IEmployee employeeTarget) {
        this.employeeTarget = employeeTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //.. preprocessing
        if (method.getName ().toLowerCase ().contains ("hike")) {
            double bonusAmount = (double) args[0];
            if (bonusAmount < 0) throw new RuntimeException ("bonus cannot be negative..");
        }
        //.. making actual call to my service
        //.. post-processing
        return method.invoke (employeeTarget, args);
    }
}
