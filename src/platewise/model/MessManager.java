package platewise.model;

public class MessManager extends User {

    private String messName;

    public MessManager(int userId, String name, String email, String messName) {
        super(userId, name, email);
        this.messName = messName;
    }

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    @Override
    public void displayMenu() {
        System.out.println();
        System.out.println("========== MESS MANAGER ==========");
        System.out.println("Welcome, " + getName());
        System.out.println("Mess: " + messName);
        System.out.println("1. Add Meal Record");
        System.out.println("2. View Waste Analytics");
        System.out.println("3. Check Waste Alerts");
        System.out.println("4. Get Preparation Recommendation");
        System.out.println("5. Exit");
        System.out.println("==================================");
    }

    public void displayManagerInfo() {
        displayUserInfo();
        System.out.println("Mess: " + messName);
    }
}