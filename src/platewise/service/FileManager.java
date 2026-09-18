package platewise.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import platewise.model.FoodItem;
import platewise.model.Meal;
import platewise.model.MealRecord;

public class FileManager {

    private static final String FILE_NAME = "meal_records.txt";

    public void saveRecords(List<MealRecord> records) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {

            for (MealRecord record : records) {

                writer.write(
                        record.getMeal().getDate() + " | "
                        + record.getMeal().getMealType() + " | "
                        + record.getFoodItem().getFoodName() + " | "
                        + record.getQuantityPrepared() + " | "
                        + record.getQuantityConsumed() + " | "
                        + record.calculateWaste()
                        + System.lineSeparator()
                );
            }

            System.out.println("Meal records saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving meal records: "
                    + e.getMessage()
            );
        }
    }

    public List<MealRecord> loadRecords() {

        List<MealRecord> records = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            int recordId = 1;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\s*\\|\\s*");

                if (data.length == 6) {

                    String date = data[0];
                    String mealType = data[1];
                    String foodName = data[2];

                    int prepared = Integer.parseInt(data[3]);
                    int consumed = Integer.parseInt(data[4]);

                    Meal meal = new Meal(
                            recordId,
                            mealType,
                            date
                    );

                    FoodItem foodItem = new FoodItem(
                            recordId,
                            foodName,
                            "Meal Item"
                    );

                    MealRecord record = new MealRecord(
                            recordId,
                            meal,
                            foodItem,
                            prepared,
                            consumed
                    );

                    records.add(record);

                    recordId++;
                }
            }

            System.out.println(
                    records.size() + " saved meal record(s) loaded."
            );

        } catch (IOException e) {

            System.out.println(
                    "No previous meal records found."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Invalid data found in meal_records.txt."
            );
        }

        return records;
    }
}
