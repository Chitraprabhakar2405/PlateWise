package platewise.service;

import platewise.model.MealRecord;

import java.util.List;

public class RecommendationEngine {

    private static final double SAFETY_BUFFER = 0.05;

    public int calculateAverageConsumption(List<MealRecord> records) {

        if (records.isEmpty()) {
            return 0;
        }

        int totalConsumption = 0;

        for (MealRecord record : records) {
            totalConsumption += record.getQuantityConsumed();
        }

        return totalConsumption / records.size();
    }

    public int recommendQuantity(int expectedAttendance,
                                 int averageConsumption) {

        if (expectedAttendance <= 0) {
            return 0;
        }

        int baseQuantity = Math.max(expectedAttendance, averageConsumption);

        return (int) Math.ceil(baseQuantity * (1 + SAFETY_BUFFER));
    }

    public void displayRecommendation(int expectedAttendance,
                                      int averageConsumption) {

        int recommendation =
                recommendQuantity(expectedAttendance, averageConsumption);

        System.out.println("\n====== PREPARATION RECOMMENDATION ======");
        System.out.println("Expected Attendance: " + expectedAttendance);
        System.out.println("Average Historical Consumption: "
                + averageConsumption);
        System.out.println("Recommended Preparation Quantity: "
                + recommendation);
        System.out.println("Safety Buffer: 5%");
        System.out.println("========================================");
    }
}
