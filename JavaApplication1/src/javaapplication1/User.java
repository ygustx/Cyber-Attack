package javaapplication1;


public class User {
    private String name;
    private String id;
    private String email;
    private String phone;

    public User(String name, String id, String email, String phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
