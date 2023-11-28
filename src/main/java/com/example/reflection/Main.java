package com.example.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
//            Constructor userClass = Class.forName(User.class.getName()).getConstructor(String.class, Integer.TYPE);
//            User user = (User) userClass.newInstance("minsu", 20);
//            System.out.println(user);
            Class clazz = Class.forName(User.class.getName());
            Object user = clazz.getDeclaredConstructor().newInstance();
            Field userNameField = clazz.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "marco");
            String userName = (String) userNameField.get(user);
            Field userAgeField = clazz.getDeclaredField("userAge");
            userAgeField.setAccessible(true);
            userAgeField.set(user, 30);
            int userAge = userAgeField.getInt(user);
            System.out.println("userName:" + userName);
            System.out.println("userAge:" + userAge);
        } catch (ClassNotFoundException | NoSuchMethodException exception) {
            exception.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

    }
}
