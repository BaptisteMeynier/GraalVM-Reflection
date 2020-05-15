package com.keywer.aot;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Laboratory {

    private static final String USER_CLASS = "com.keywer.aot.User";

    void beginExperience() {
        try {
            System.out.println("ClassLoading");
            classLoading();
            System.out.println("reflection");
            reflection();
            System.out.println("Change Field by reflection");
            reflectionChangePrivateField();
            System.out.println("Call private method by reflection");
            reflectionCallMethod();
            System.out.println("Call private method with parameter by reflection");
            reflectionCallMethodWithParam();
            System.out.println("Call dynamic Proxy");
            dynamicProxy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void classLoading() {
        try {
            Class classe = Class.forName(USER_CLASS);
            System.out.println("classe de l'objet chaine = " + classe.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reflection() throws ClassNotFoundException {
        List<String> cp = new ArrayList<>();
        Class classe = Class.forName(USER_CLASS);
        Field[] champs = classe.getFields();
        for (Field champ : champs) cp.add(champ.getType().getName() + " " + champ.getName());
        cp.forEach(System.out::println);
        System.out.println("Hello" + champs.length);
    }

    private void reflectionChangePrivateField() throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        user.setAge(10);

        Field f1 = user.getClass().getDeclaredField("age");
        f1.setAccessible(true);
        f1.set(user, 18);
        int age = (int) f1.get(user);
        System.out.println("field: " + age);
    }

    private void reflectionCallMethod() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = new User();

        Method m1 = user.getClass().getMethod("sayHello");
        m1.setAccessible(true);
        m1.invoke(user);
    }

    private void reflectionCallMethodWithParam() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = new User();

        Method m1 = user.getClass().getMethod("sayHelloForUser", String.class);
        m1.setAccessible(true);
        m1.invoke(user, "Norman");
    }

    private void dynamicProxy() {
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                Laboratory.class.getClassLoader(),
                new Class[]{Map.class},
                new DynamicInvocationHandler());
        proxyInstance.put("hello", "world");
    }

}
