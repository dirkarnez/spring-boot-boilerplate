package com.alex.springbootboilerplate.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SetterInterceptor {
    @Before("execution(* com.alex.springbootboilerplate.entity.DemoInfo.set*(..))")
    public void beforeSetterCalled(JoinPoint thisJoinPoint) {
        String methodName = thisJoinPoint.getSignature().getName();
        try {
//            System.out.println(
//                    methodName.substring(3) + ": " +
//                            instance
//                                    .getClass()
//                                    .getMethod(methodName.replaceFirst("set", "get"))
//                                    .invoke(instance) +
//                            " -> " + newValue
//            );

            System.out.println("Excute advice on Service set Method: " + thisJoinPoint.getSignature().getName());
        } catch (Exception e) {
            System.out.println("@Excute advice on Service set Method: " + thisJoinPoint.getSignature().getName());
        }
    }
}