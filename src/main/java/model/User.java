package model;

import com.github.javafaker.Faker;

public class User {
    static Faker faker = new Faker();
    private String email;
    private String password;
    private String name;

    public User() {

    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
            }

    public String getEmail() {
        return email;
    }

    public String setEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String invalidPassword) {
//        return password;
        return "123";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User generateUser() {
        return new User(faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName());
    }
}
