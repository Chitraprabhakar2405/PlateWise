package platewise.model;

public class Meal {

    private int mealId;
    private String mealType;
    private String date;

    public Meal(int mealId, String mealType, String date) {
        this.mealId = mealId;
        this.mealType = mealType;
        this.date = date;
    }

    public int getMealId() {
        return mealId;
    }

    public String getMealType() {
        return mealType;
    }

    public String getDate() {
        return date;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void displayMealInfo() {
        System.out.println("Meal ID: " + mealId);
        System.out.println("Meal Type: " + mealType);
        System.out.println("Date: " + date);
    }
}
