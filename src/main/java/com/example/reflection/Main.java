package com.example.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
//            Constructor userClass = Class.forName(User.class.getName()).getConstructor(String.class, Integer.TYPE);
//            User user = (User) userClass.newInstance("minsu", 20);
//            System.out.println(user);
            Class clazz = Class.forName(User.class.getName());
            Object user = clazz.getDeclaredConstructor().newInstance();
            Method setUserNameMethod = clazz.getDeclaredMethod("setUserName", String.class);
            setUserNameMethod.invoke(user, "NHN 아카데미");
            Method getUserNameMethod = clazz.getMethod("getUserName");
            String userName = (String) getUserNameMethod.invoke(user);
            Method setUserAgeMethod = clazz.getDeclaredMethod("setUserAge", Integer.TYPE);
            setUserAgeMethod.invoke(user, 23);
            Method getUserAgeMethod = clazz.getDeclaredMethod("getUserAge");
            int userAge = (int) getUserAgeMethod.invoke(user);
            System.out.println("userName: " + userName);
            System.out.println("userAge: " + userAge);
        } catch (ClassNotFoundException | NoSuchMethodException exception) {
            exception.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
