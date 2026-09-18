package platewise.service;

import java.util.List;

import platewise.model.MealRecord;

public class WasteAnalyzer {

    private static final double HIGH_WASTE_THRESHOLD = 10.0;
    private static final double MODERATE_WASTE_THRESHOLD = 5.0;

    public int calculateWaste(MealRecord record) {

        return record.getQuantityPrepared()
                - record.getQuantityConsumed();
    }

    public double calculateWastePercentage(MealRecord record) {

        int prepared = record.getQuantityPrepared();

        if (prepared == 0) {
            return 0;
        }

        return ((double) calculateWaste(record) / prepared) * 100;
    }

    public String classifyWaste(MealRecord record) {

        double wastePercentage = calculateWastePercentage(record);

        if (wastePercentage > HIGH_WASTE_THRESHOLD) {
            return "HIGH";
        } else if (wastePercentage >= MODERATE_WASTE_THRESHOLD) {
            return "MODERATE";
        } else {
            return "LOW";
        }
    }

    public void generateAlert(MealRecord record) {

        double wastePercentage = calculateWastePercentage(record);
        String level = classifyWaste(record);

        if (level.equals("HIGH")) {

            System.out.printf(
                    "⚠ HIGH WASTE ALERT: %.2f%% food was wasted.%n",
                    wastePercentage
            );

        } else if (level.equals("MODERATE")) {

            System.out.printf(
                    "⚠ Moderate waste detected: %.2f%%%n",
                    wastePercentage
            );

        } else {

            System.out.printf(
                    "✓ Waste level is LOW: %.2f%%%n",
                    wastePercentage
            );
        }
    }

    public void analyzeRecord(MealRecord record) {

        System.out.println();
        System.out.println("========== WASTE ANALYSIS ==========");
        System.out.println(
                "Food: " + record.getFoodItem().getFoodName()
        );
        System.out.println(
                "Prepared: " + record.getQuantityPrepared()
        );
        System.out.println(
                "Consumed: " + record.getQuantityConsumed()
        );
        System.out.println(
                "Wasted: " + calculateWaste(record)
        );

        System.out.printf(
                "Waste Percentage: %.2f%%%n",
                calculateWastePercentage(record)
        );

        System.out.println(
                "Waste Level: " + classifyWaste(record)
        );

        System.out.println("====================================");

        generateAlert(record);
    }

    // Overall analytics for all recorded meals
    public void analyzeAllRecords(List<MealRecord> records) {

        if (records.isEmpty()) {

            System.out.println(
                    "\nNo meal records available for analytics."
            );

            return;
        }

        int totalPrepared = 0;
        int totalConsumed = 0;
        int totalWasted = 0;

        System.out.println();
        System.out.println("========== WASTE HISTORY ==========");

        for (MealRecord record : records) {

            int wasted = calculateWaste(record);

            totalPrepared += record.getQuantityPrepared();
            totalConsumed += record.getQuantityConsumed();
            totalWasted += wasted;

            System.out.println(
                    record.getFoodItem().getFoodName()
                    + " | Prepared: "
                    + record.getQuantityPrepared()
                    + " | Consumed: "
                    + record.getQuantityConsumed()
                    + " | Wasted: "
                    + wasted
            );
        }

        double overallWastePercentage = 0;

        if (totalPrepared > 0) {

            overallWastePercentage =
                    ((double) totalWasted / totalPrepared) * 100;
        }

        String overallStatus;

        if (overallWastePercentage > HIGH_WASTE_THRESHOLD) {

            overallStatus = "HIGH";

        } else if (overallWastePercentage >= MODERATE_WASTE_THRESHOLD) {

            overallStatus = "MODERATE";

        } else {

            overallStatus = "LOW";
        }

        System.out.println("-----------------------------------");
        System.out.println("Total Records: " + records.size());
        System.out.println("Total Prepared: " + totalPrepared);
        System.out.println("Total Consumed: " + totalConsumed);
        System.out.println("Total Wasted: " + totalWasted);

        System.out.printf(
                "Overall Waste Percentage: %.2f%%%n",
                overallWastePercentage
        );

        System.out.println(
                "Overall Waste Status: " + overallStatus
        );

        System.out.println("===================================");
    }
}
