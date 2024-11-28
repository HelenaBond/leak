package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    private static final String PATH_NAMES = "files/names.txt";
    private static final String PATH_SURNAMES = "files/surnames.txt";
    private static final String PATH_PATRONS = "files/patr.txt";
    private static final int NEW_USERS = 1000;
    private final List<User> users = new ArrayList<>();
    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;

    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            var name = String.format("%s %s %s",
                    surnames.get(random.nextInt(surnames.size())),
                    names.get(random.nextInt(names.size())),
                    patrons.get(random.nextInt(patrons.size())));
            var user = new User();
            user.setName(name);
            users.add(user);
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}