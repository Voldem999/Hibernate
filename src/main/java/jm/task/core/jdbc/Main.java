package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        hibernate();

    }

    public static void jdbc(){
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Петр", "Петров", (byte) 35);
        userService.saveUser("Сергей", "Сидоров", (byte) 40);
        userService.saveUser("Владислав", "Светлый", (byte) 22);
        userService.saveUser("Иосиф", "Смирнов", (byte) 15);
        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

    public static void hibernate() {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.saveUser("Владимир", "Католиков", (byte) 30);
    }
}