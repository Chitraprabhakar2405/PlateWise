package platewise.model;

public class FoodItem {

    private int foodId;
    private String foodName;
    private String category;

    public FoodItem(int foodId, String foodName, String category) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.category = category;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void displayFoodInfo() {
        System.out.println("Food ID: " + foodId);
        System.out.println("Food Name: " + foodName);
        System.out.println("Category: " + category);
    }
}
