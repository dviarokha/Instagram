package com.solvd.instagram;

import com.solvd.instagram.models.User;
import com.solvd.instagram.parser.UserStaxReader;
import com.solvd.instagram.parser.UserStaxWriter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<User> users = UserStaxReader.readUsers("/Users/dashaviarokha/Downloads/Instagram/src/main/resources/Users.xml");

            for (User user : users) {
                System.out.println(user);
            }

            UserStaxWriter.writeUsers(users, "/Users/dashaviarokha/Downloads/Instagram/src/main/resources/Users_output.xml");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
