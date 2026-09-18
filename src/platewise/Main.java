package platewise;

import java.util.List;
import java.util.Scanner;

import platewise.model.Attendance;
import platewise.model.FoodItem;
import platewise.model.Meal;
import platewise.model.MealRecord;
import platewise.model.MessManager;
import platewise.model.Student;
import platewise.service.FileManager;
import platewise.service.RecommendationEngine;
import platewise.service.WasteAnalyzer;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Sample student
        Student student = new Student(
                101,
                "Demo Student",
                "student@college.com",
                "AIML",
                3
        );

        // Sample mess manager
        MessManager manager = new MessManager(
                201,
                "Mess Manager",
                "manager@college.com",
                "Main College Mess"
        );

        // Default meal
        Meal meal = new Meal(
                1,
                "Lunch",
                "18-09-2026"
        );

        // File manager
        FileManager fileManager = new FileManager();

        // Load previously saved records
        List<MealRecord> records =
                fileManager.loadRecords();

        // Service classes
        WasteAnalyzer wasteAnalyzer =
                new WasteAnalyzer();

        RecommendationEngine recommendationEngine =
                new RecommendationEngine();

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("              PLATEWISE");
            System.out.println("   Smart College Mess Waste Management");
            System.out.println("========================================");
            System.out.println();
            System.out.println("1. Student Portal");
            System.out.println("2. Mess Manager Portal");
            System.out.println("3. Waste Analysis");
            System.out.println("4. Preparation Recommendation");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                // STUDENT PORTAL
                case 1:

                    student.displayStudentInfo();
                    student.displayMenu();

                    System.out.print(
                            "\nEnter attendance status (1 = Present, 0 = Absent): "
                    );

                    int status = scanner.nextInt();

                    if (status == 1 || status == 0) {

                        Attendance attendance =
                                new Attendance(
                                        1,
                                        student,
                                        meal,
                                        status == 1
                                );

                        System.out.println();

                        attendance.displayAttendance();

                    } else {

                        System.out.println(
                                "Invalid attendance status."
                        );
                    }

                    break;


                // MESS MANAGER PORTAL
                case 2:

                    manager.displayManagerInfo();
                    manager.displayMenu();

                    System.out.println();
                    System.out.println(
                            "Manager portal is ready."
                    );

                    System.out.println(
                            "Use Waste Analysis to record food usage."
                    );

                    break;


                // WASTE ANALYSIS
                case 3:

                    System.out.println();
                    System.out.println(
                            "========== ADD MEAL RECORD =========="
                    );

                    scanner.nextLine();

                    System.out.print(
                            "Enter food name: "
                    );

                    String foodName =
                            scanner.nextLine();

                    System.out.print(
                            "Enter quantity prepared: "
                    );

                    int prepared =
                            scanner.nextInt();

                    System.out.print(
                            "Enter quantity consumed: "
                    );

                    int consumed =
                            scanner.nextInt();

                    if (prepared < 0 ||
                            consumed < 0) {

                        System.out.println(
                                "Error: Quantity cannot be negative."
                        );

                    } else if (consumed > prepared) {

                        System.out.println(
                                "Error: Consumed quantity cannot be greater than prepared quantity."
                        );

                    } else {

                        FoodItem currentFood =
                                new FoodItem(
                                        records.size() + 1,
                                        foodName,
                                        "Meal Item"
                                );

                        MealRecord record =
                                new MealRecord(
                                        records.size() + 1,
                                        meal,
                                        currentFood,
                                        prepared,
                                        consumed
                                );

                        records.add(record);

                        System.out.println();

                        wasteAnalyzer.analyzeRecord(
                                record
                        );

                        fileManager.saveRecords(
                                records
                        );

                        wasteAnalyzer.analyzeAllRecords(
                                records
                        );
                    }

                    break;


                // PREPARATION RECOMMENDATION
                case 4:

                    System.out.println();
                    System.out.println(
                            "====== PREPARATION RECOMMENDATION ======"
                    );

                    System.out.print(
                            "Enter expected attendance: "
                    );

                    int expectedAttendance =
                            scanner.nextInt();

                    if (expectedAttendance < 0) {

                        System.out.println(
                                "Error: Attendance cannot be negative."
                        );

                    } else if (records.isEmpty()) {

                        System.out.println(
                                "No historical meal records available."
                        );

                        System.out.println(
                                "Please add meal records through Waste Analysis first."
                        );

                    } else {

                        int averageConsumption =
                                recommendationEngine
                                        .calculateAverageConsumption(
                                                records
                                        );

                        recommendationEngine
                                .displayRecommendation(
                                        expectedAttendance,
                                        averageConsumption
                                );
                    }

                    break;


                // EXIT
                case 5:

                    System.out.println();
                    System.out.println(
                            "Thank you for using PlateWise!"
                    );

                    System.out.println(
                            "Goodbye!"
                    );

                    break;


                // INVALID CHOICE
                default:

                    System.out.println();

                    System.out.println(
                            "Invalid choice. Please enter a number between 1 and 5."
                    );
            }

        } while (choice != 5);

        scanner.close();
    }
}