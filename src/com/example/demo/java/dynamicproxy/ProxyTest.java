package com.example.demo.java.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Person zhangsan = new Student("张三");

        InvocationHandler exeTimeHandler = new ExecutionTimeHandler<Person>(zhangsan);
        Person exeTimeProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class<?>[]{Person.class}, exeTimeHandler);

        InvocationHandler printParamHandler = new PrintParamHandler<Person>(exeTimeProxy);
        Person printParamProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class<?>[]{Person.class}, printParamHandler);

        printParamProxy.giveMoney();
    }
}
