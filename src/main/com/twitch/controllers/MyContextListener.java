package twitch.controllers;

import twitch.models.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;

public class MyContextListener implements ServletContextListener {
    static public ArrayList<User> users = new ArrayList<>();

    public void contextInitialized(ServletContextEvent e) {
        users.add(new User("u1","u1"));
        users.add(new User("u2","u2"));
        users.add(new User("u3","u3"));
        //TODO function for read from XLM - Unmarshaller
    }

    public void contextDestroyed(ServletContextEvent e) {
        //TODO function to write to XML - Marshaller
    }
}

