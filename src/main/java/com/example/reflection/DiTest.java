package com.example.reflection;

public class DiTest {
    public static void main(String[] args) {
        UserService userService = InjectUtil.getObject(UserService.class);
        User user = new User("mar", 10);
        userService.addUser(user);
        System.out.println(userService.getUSer("mar"));
    }
}
