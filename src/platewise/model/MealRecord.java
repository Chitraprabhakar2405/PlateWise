package platewise.model;

public class MealRecord {

    private int recordId;
    private Meal meal;
    private FoodItem foodItem;
    private int quantityPrepared;
    private int quantityConsumed;

    public MealRecord(int recordId, Meal meal, FoodItem foodItem,
                      int quantityPrepared, int quantityConsumed) {

        this.recordId = recordId;
        this.meal = meal;
        this.foodItem = foodItem;
        this.quantityPrepared = quantityPrepared;
        this.quantityConsumed = quantityConsumed;
    }

    public int getRecordId() {
        return recordId;
    }

    public Meal getMeal() {
        return meal;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getQuantityPrepared() {
        return quantityPrepared;
    }

    public int getQuantityConsumed() {
        return quantityConsumed;
    }

    public void setQuantityPrepared(int quantityPrepared) {
        this.quantityPrepared = quantityPrepared;
    }

    public void setQuantityConsumed(int quantityConsumed) {
        this.quantityConsumed = quantityConsumed;
    }

    public int calculateWaste() {
        return quantityPrepared - quantityConsumed;
    }

    public double calculateWastePercentage() {
        if (quantityPrepared == 0) {
            return 0;
        }

        return ((double) calculateWaste() / quantityPrepared) * 100;
    }

    public void displayRecord() {
        System.out.println("Record ID: " + recordId);
        System.out.println("Meal: " + meal.getMealType());
        System.out.println("Food: " + foodItem.getFoodName());
        System.out.println("Prepared: " + quantityPrepared);
        System.out.println("Consumed: " + quantityConsumed);
        System.out.println("Wasted: " + calculateWaste());
        System.out.printf("Waste Percentage: %.2f%%%n",
                calculateWastePercentage());
    }
}