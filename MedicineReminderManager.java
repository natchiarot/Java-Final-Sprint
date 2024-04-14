
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MedicineReminderManager class manages medicine reminders, including adding, retrieving reminders and specifically due reminders,
 * as well as updating reminders in the database.
 */
public class MedicineReminderManager {
    private List<MedicineReminder> reminders;

    /**
     * Constructs a MedicineReminderManager object with an empty list of reminders.
     */
    public MedicineReminderManager() {
        this.reminders = new ArrayList<>();
    }

    /**
     * Adds a medicine reminder to the list.
     *
     * @param reminder The medicine reminder to add.
     */
    public void addReminder(MedicineReminder reminder) {
        reminders.add(reminder);
    }

     /**
     * Retrieves all reminders associated with a specific user.
     *
     * @param userId The ID of the user whose reminders are to be retrieved.
     * @return A list of medicine reminders associated with the specified user.
     */
    public List<MedicineReminder> getRemindersForUser(int userId) {
        List<MedicineReminder> userReminders = new ArrayList<>();

        int id = 0;
        int user_id = 0;
        String medicineName = null;
        String dosage = null;
        String schedule = null;
        LocalDate start_date = null;
        LocalDate end_date = null;

        String query = "SELECT * FROM medicine_reminders WHERE user_id = ?";

        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
    
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                user_id = rs.getInt("user_id");
                medicineName = rs.getString("medicine_name");
                dosage = rs.getString("dosage");
                schedule = rs.getString("schedule");
                start_date = rs.getDate("start_date").toLocalDate();
                end_date = rs.getDate("end_date").toLocalDate();

                // Adding the created medicine reminder object to the list.
                MedicineReminder medReminder = new MedicineReminder(id, user_id, medicineName, dosage, schedule, start_date, end_date);
                userReminders.add(medReminder);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
        return userReminders;
    }

     /**
     * Retrieves reminders that are due for a specific user.
     *
     * @param userId The ID of the user for whom due reminders are to be retrieved.
     * @return A list of medicine reminders that are due for the specified user.
     */
    public List<MedicineReminder> getDueReminders(int userId) {
        List<MedicineReminder> dueReminders = new ArrayList<>();

        int id = 0;
        int user_id = 0;
        String medicineName = null;
        String dosage = null;
        String schedule = null;
        LocalDate start_date = null;
        LocalDate end_date = null;

        // Plus sign for code readability.
        // CURRENT_DATE is a built in SQL function to get the current date.
        // TO_TIMESTAMP converts the schedule entered as a String (varchar) to a date and time.
        String query = "SELECT * FROM medicine_reminders WHERE user_id = ? AND start_date <= CURRENT_DATE AND end_date >= CURRENT_DATE";

        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
    
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                user_id = rs.getInt("user_id");
                medicineName = rs.getString("medicine_name");
                dosage = rs.getString("dosage");
                schedule = rs.getString("schedule");
                start_date = rs.getDate("start_date").toLocalDate();
                end_date = rs.getDate("end_date").toLocalDate();

                // Adding the created medicine reminder object to the list.
                MedicineReminder medReminder = new MedicineReminder(id, user_id, medicineName, dosage, schedule, start_date, end_date);
                dueReminders.add(medReminder);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return dueReminders;
    }

    /**
     * Stores a medicine reminder in the database.
     *
     * @param medReminder The medicine reminder to be stored.
     * @return True if the reminder is successfully stored, false otherwise.
     */
    public boolean storeReminder(MedicineReminder medReminder) {
        boolean bool = false;

        // Prepare the SQL query
        String query = "INSERT INTO medicine_reminders (user_id, medicine_name, dosage, schedule, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";

        // Database logic to insert data using PREPARED Statement
        try {
        Connection db = DatabaseConnection.getCon();
        PreparedStatement statement = db.prepareStatement(query);
        statement.setInt(1, medReminder.getUserId());
        statement.setString(2, medReminder.getMedicineName());
        statement.setString(3, medReminder.getDosage());
        statement.setString(4, medReminder.getSchedule());
        statement.setDate(5, java.sql.Date.valueOf(medReminder.getStartDate()));
        statement.setDate(6, java.sql.Date.valueOf(medReminder.getEndDate()));
        int newRow = statement.executeUpdate();
        if(newRow != 0) {
            bool = true;
        }
        } catch (SQLException error){
            error.printStackTrace();
        }
        return bool;
    }

    /**
     * Updates a medicine reminder in the database.
     *
     * @param medReminder The medicine reminder to be updated.
     * @return True if the reminder is successfully updated, false otherwise.
     */
    public boolean updateReminder(MedicineReminder medReminder) {
        boolean bool = false;

        // Prepare the SQL query
        String query = "UPDATE medicince_reminders, SET user_id = ?, medicine_name = ?, dosage = ?, schedule = ?, start_date = ?, end_date = ?";
        // Database logic to get update user Using Prepared Statement
        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, medReminder.getUserId());
            statement.setString(2, medReminder.getMedicineName());
            statement.setString(3, medReminder.getDosage());
            statement.setString(4, medReminder.getSchedule());
            statement.setDate(5, java.sql.Date.valueOf(medReminder.getStartDate()));
            statement.setDate(6, java.sql.Date.valueOf(medReminder.getEndDate()));

        } catch (SQLException error) {
            error.printStackTrace();
        }
        return bool;
    }

    /**
     * Deletes a medicine reminder from the database.
     *
     * @param id The ID of the reminder to be deleted.
     * @return True if the reminder is successfully deleted, false otherwise.
     */
    public boolean deleteReminder(int id) {
        boolean bool = false;

        // Prepare the SQL query
        String query = "DELETE * FROM medicine_reminders WHERE id = ?";

        // Database logic to delete user
        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, id);

            int updatedRow = statement.executeUpdate();
            if(updatedRow != 0) {
                bool = true;
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
        return bool;
    }
}
