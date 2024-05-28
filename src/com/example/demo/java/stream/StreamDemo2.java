package com.example.demo.java.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamDemo2 {
    private static class User {
        private int id;

        public User(int id) {
            this.id = id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        User u1 = new User(100);
        User u2 = new User(1);
        User u3 = new User(2);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        String result = users.stream().map(u -> u.getId()).distinct().sorted().map(id -> String.valueOf(id)).distinct().collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);
    }
}
