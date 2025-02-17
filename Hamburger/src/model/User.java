package model;

public class User {
    private int id;
    private String username, password;

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}

