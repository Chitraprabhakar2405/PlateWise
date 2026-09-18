package platewise.model;

public abstract class User {

    private int userId;
    private String name;
    private String email;

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayUserInfo() {
        System.out.println("ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }

    public abstract void displayMenu();
}
