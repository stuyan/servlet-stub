package ru.stuyan.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User(1, "Baton", "Murov", 500d));
        model.put(2, new User(2,"Lukas", "Shipelov", 800d));
        model.put(3, new User(3,"Snikers", "Gavov", 400d));
    }

    public void addUser(int id, User user) {
        model.put(id, user);
    }

    public Map<Integer, User> getUsersMap() {
        return model;
    }
}
