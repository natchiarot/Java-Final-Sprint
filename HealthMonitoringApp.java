

// import com.DataBaseConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The main application class for the Health Monitoring System App.
 * This class provides methods to test the functionalities of the system,
 * including user registration, login, health data management, recommendation generation,
 * medicine reminder management, and doctor portal functionalities.
 */
public class HealthMonitoringApp {

    private static UserDao userDao = new UserDao();
    private static HealthDataDao healthDataDao = new HealthDataDao();
    private static RecommendationSystem recommendationSystem = new RecommendationSystem();
    private static MedicineReminderManager medicineReminderManager = new MedicineReminderManager();
    private static DoctorPortalDao doctorPortalDao = new DoctorPortalDao();

    

    /**
     *  The main method to test the functionalities of the Health Monitoring System.
     * Test the following functionalities within the Main Application
     *  1. Register a new user
     *  2. Log in the user
     *  3. Add health data
     *  4. Generate recommendations
     *  5. Add a medicine reminder
     *  6. Get reminders for a specific user
     *  7. Get due reminders for a specific user
     *  8. test doctor portal
     *
     * @param args The command-line arguments (unused).
     */
    MedicineReminder reminderOne = new MedicineReminder(3, "Tyelenol Extra Strength", "Take 1-2 daily", "12:00 PM", LocalDate.of(2024, 04, 13), LocalDate.of(2024,8,13));

    /**
     * The main method for testing the application.
     * 
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        System.out.println();
    //    DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao();

        // test register a new user
        List<User> userList = new ArrayList<>();

        User userOne = new User("Leonardo", "DaVinci", "articticemail@vscode.com", "VitruvianMan1234", true, "A123343", "General Family Doctor");
        userList.add(userOne);

        User userTwo = new User("Ainee", "Malik","qmalik@gmail.com", "guggu", false, null, null);
        userList.add(userTwo);

        User userThree = new User("Vincent", "VanGogh", "starrynight@vscode.com", "Sunflower2", false, null, null);
        userList.add(userThree);

        User userFour = new User("Monty", "Python", "movieieiei@vscode.com", "HelloThere8", true, "B45345", "Ears");
        userList.add(userFour);

        User userFive = new User("Bobby", "Or", "hockey1@vscode.com", "djkfndf", false, null, null);
        userList.add(userFive);

        User userSix = new User("Sydney", "Crosby", "heyitsanotherhockey@vscode.com", "lalalalalala", false, null, null);
        userList.add(userSix);

        User userSeven = new User("Tom", "Thomas", "tommytoms@vscode.com", "Hihellohowareyou", true, "C23645263", "Nose");
        userList.add(userSeven);

        User userEight = new User("Gingerbread", "Man", "fionaandshrek@vscode.com", "donkey", false, null, null);
        userList.add(userEight);

        User userNine = new User("Violet", "Colour", "rainbows@vscode.com", "dfgdw34sef3r3", false, null, null);
        userList.add(userNine);

        User userTen = new User("Naruto", "Uzumaki", "hiddenleaf@vscode.com", "Shippuden", false, null, null);
        userList.add(userTen);

        // Inserting users into database.
        for (User users : userList) {
            userDao.createUser(users);
        }
        
        // test Login user (call testLoginUser() here)
        testLoginUser(userTwo.getEmail(), "guggu");
        System.out.println();

        // Add health data
        HealthData healthDataOne = new HealthData(3, 100, 70, 2000, 55, LocalDate.of(2024, 04, 12));
        boolean addHealthDataOne = healthDataDao.createHealthData(healthDataOne);
        if (addHealthDataOne) {
            System.out.println("Health data added successfully.");
        } else {
            System.out.println("Health data addition failed.");
        }
        System.out.println();

        // Generate recommendations
        List<String> recommendationsUserOne = recommendationSystem.generateRecommendations(healthDataOne);
        System.out.println("Health Recommendations:");
        System.out.println();
        for (String recommendationUserOne : recommendationsUserOne) {
            System.out.println(recommendationUserOne);
            System.out.println();
        }
        System.out.println();

        // Add medicine reminders
        MedicineReminder medicineReminderOne = new MedicineReminder(3, "Ibuprofen", "400mg Twice Daily", "12:00 PM, 8:00 PM", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 30));
        boolean addReminderOne = medicineReminderManager.storeReminder(medicineReminderOne);
        if (addReminderOne) {
            System.out.println("Medicine reminder added successfully.");
        } else {
            System.out.println("Medicine reminder addition failed.");
        }
        System.out.println();

        MedicineReminder medicineReminderTwo = new MedicineReminder(3, "Tylenol Extra Strength", "500mg Once Daily", "12:00 PM", LocalDate.of(2024, 4, 14), LocalDate.of(2025, 4, 14));
        boolean addReminderTwo = medicineReminderManager.storeReminder(medicineReminderTwo);
        if (addReminderTwo) {
            System.out.println("Medicine reminder added successfully.");
        } else {
            System.out.println("Medicine reminder addition failed.");
        }
        System.out.println();

        // Get reminders for a specific user
        int userId = 3;
        List<MedicineReminder> remindersForTwo = medicineReminderManager.getRemindersForUser(userId);
        System.out.println("Medicine reminders for user: " + userId);
        for (MedicineReminder reminderForTwo : remindersForTwo) {
            System.out.println(reminderForTwo.toString());
            System.out.println();
        }
        System.out.println();

        // Get due reminders for a specific user
        userId = 3;
        List<MedicineReminder> dueRemindersForTwo = medicineReminderManager.getDueReminders(userId);
        System.out.println("Due medicine reminders for user " + userId);
        for (MedicineReminder dueReminderForTwo : dueRemindersForTwo) {
            System.out.println(dueReminderForTwo.toString());
        }
        System.out.println();

        //test doctor portal (call testDoctorPortal() here)
        testDoctorPortal();
        System.out.println();
    }
    

    


    /**
     * Tests the Doctor Portal functionalities of the Health Monitoring System.
     * This method tests the following functionalities:
     * 1. Fetching a doctor by ID
     * 2. Fetching patients associated with a doctor
     * 3. Fetching health data for a specific patient
     * 4. Booking an appointment
      */
    public static void testDoctorPortal() {
        // Replace the doctorId with a valid ID from your database
        int doctorIdOne = 1;
        int patientIdTwo = 2;
        LocalDateTime appointmentDate = LocalDateTime.of(2024, 4, 15, 10, 0);

        boolean appointmentOne = doctorPortalDao.bookAppointment(doctorIdOne, patientIdTwo, appointmentDate);
        if (appointmentOne) {
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("Failed to book appointment.");
        }
        // Add code to Fetch the doctor by ID
        Doctor doctor = doctorPortalDao.getDoctorById(doctorIdOne);

        if (doctor != null) {
            System.out.println("Getting doctor by id [" + doctorIdOne + "]: " + doctor);
            System.out.println();
        } else {
            System.out.println("Doctor could not be found.");
        }

        // Add code to Fetch patients associated with the doctor
        List<User> doctorsPatients = doctorPortalDao.getPatientsByDoctorId(doctorIdOne);
        // Checking that the doctors patients list is not empty first.
        if (!doctorsPatients.isEmpty()) {
            System.out.println(doctor + "'s patients: ");
            System.out.println();
            for (User doctorsPatient : doctorsPatients) {
                System.out.println(doctorsPatient);
            }
        } else {
            System.out.println(doctor + " has no patients.");
        }

        // Add code to Fetch health data for the patient
        int patientIdOne = 3;
        List<HealthData> healthDataList = doctorPortalDao.getHealthDataByPatientId(patientIdOne);
        // Checking that the health data is nott empty first.
        if (!healthDataList.isEmpty()) {
            System.out.println("Health data for patient " + patientIdOne);
            System.out.println();
            for (HealthData healthData : healthDataList) {
                System.out.println(healthData);
                System.out.println();
            }
        } else {
            System.out.println("Patient " + patientIdOne + " doesn't have any health data.");
        }


    }

     /**
     * Tests the login functionality of the Health Monitoring System.
     * This method attempts to log in a user with the provided email and password.
     *
     * @param email    The email of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     */
    public static void testLoginUser(String email, String password) {
        // Was having a null pointer exception error.
        if (email == null || password == null) {
            System.out.println("Error - email and/ or password cannot be null.");
            return;
        }
        //implement method to login user.
        User user = userDao.getUserByEmail(email);
        
        if (user != null) {
            // Compare the stored hashed password with the given password and return result
            String hashedPassword = user.getPassword();

            // Checking if the password matches the hashed password from database.
            boolean passwordVerification = BCrypt.checkpw(password, hashedPassword);

            if (passwordVerification) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Login failed, incorrect email or password. Please try again.");
            };
        } else {
        System.out.println("Error - User does not exist.");
        }
    }

}
