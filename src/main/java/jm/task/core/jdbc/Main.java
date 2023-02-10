package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();// На всякий случай чистим
        userService.createUsersTable();
        userService.saveUser("Pet", "Reg", (byte) 13);
        userService.saveUser("Pet2", "Reg2", (byte) 15);
        userService.saveUser("Pet3", "Reg3", (byte) 19);
        System.out.println("Всего пользователей:");
        System.out.println(userService.getAllUsers());
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();
    }
}
