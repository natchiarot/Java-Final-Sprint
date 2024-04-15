
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;

/**
 * The HealthDataDao class provides methods to interact with the database for CRUD operations related to health data.
 */
public class HealthDataDao {

    /**
     * Inserts health data into the database.
     *
     * @param healthData The health data to be inserted.
     * @return true if the operation is successful, false otherwise.
     */
   public boolean createHealthData(HealthData healthData) { /* insert health data into database */ 
    boolean bool = false;
    
    String query = "INSERT INTO health_data (user_id, weight_pounds, height_inches, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?)";

    // Database logic to insert data using PREPARED Statement
    try {
    Connection db = DatabaseConnection.getCon();
    PreparedStatement statement = db.prepareStatement(query);
    statement.setInt(1, healthData.getUserId());
    statement.setDouble(2, healthData.getWeight());
    statement.setDouble(3, healthData.getHeight());
    statement.setInt(4,healthData.getSteps());
    statement.setInt(5, healthData.getHeartRate());
    // statement.setDate(6, java.sql.Date.valueOf(healthData.getDate()));
    // Set a default date if healthData.getDate() is null
    LocalDate dateToInsert = healthData.getDate() != null ? healthData.getDate() : LocalDate.now();
    statement.setDate(6, java.sql.Date.valueOf(dateToInsert));
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
     * Retrieves health data by ID from the database.
     *
     * @param id The ID of the health data.
     * @return The health data object corresponding to the given ID.
     */
   public HealthData getHealthDataById(int id) { /* get health data by id from database */ 
      int health_id = 0;
      int user_id = 0;
      double weight = 0.0;
      double height = 0.0;
      int steps = 0;
      int heart_rate = 0;
      LocalDate date = null;

        String query = "SELECT * FROM health_data WHERE id = ?";

        try {
          Connection db = DatabaseConnection.getCon();
          PreparedStatement statement = db.prepareStatement(query);
          statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                health_id = rs.getInt("id");
                user_id = rs.getInt("user_id");
                weight = rs.getDouble("weight_pounds");
                height = rs.getDouble("height_inches");
                steps = rs.getInt("steps");
                heart_rate = rs.getInt("heart_rate");
                // date = rs.getDate("date").toLocalDate();
                if (rs.getDate("date") != null) {
                    date = rs.getDate("date").toLocalDate();
                } else {
                    System.err.println("Date is null...");
                }
            }
          } catch (SQLException error){
              error.printStackTrace();
          }
          return new HealthData(health_id, user_id, weight, height, steps, heart_rate, date);
    }
  
     /**
     * Retrieves health data by user ID from the database.
     *
     * @param userId The ID of the user.
     * @return A list of health data objects corresponding to the given user ID.
     */
  public List<HealthData> getHealthDataByUserId(int userId) { /* get health data by user id from database */
      List<HealthData> healthDataArrayList = new ArrayList<>();

      int id = 0;
      int user_id = 0;
      double weight = 0.0;
      double height = 0.0;
      int steps = 0;
      int heart_rate = 0;
      LocalDate date = null;

      String query = "SELECT * FROM health_data WHERE user_id = ?";

        try {
          Connection db = DatabaseConnection.getCon();
          PreparedStatement statement = db.prepareStatement(query);
          statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                user_id = rs.getInt("user_id");
                weight = rs.getDouble("weight_pounds");
                height = rs.getDouble("height_inches");
                steps = rs.getInt("steps");
                heart_rate = rs.getInt("heart_rate");
                if (rs.getDate("date") != null) {
                    date = rs.getDate("date").toLocalDate();
                } else {
                    System.err.println("Date is null...");
                }

                // Adding the created health data object to list.
                HealthData healthData = new HealthData(id, user_id, weight, height, steps, heart_rate, date);
                healthDataArrayList.add(healthData);
            }

          } catch (SQLException error){
              error.printStackTrace();
          }
          return healthDataArrayList;
   }
   
   /**
     * Updates health data in the database.
     *
     * @param healthData The health data to be updated.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean updateHealthData(HealthData healthData) { /* update health data in the database */ 
        boolean bool = false;
        // Prepare the SQL query
        String query = "UPDATE health_data SET user_id = ?, weight_pounds = ?, height_inches = ?, steps = ?, heart_rate = ?, date = ?, WHERE id = ?";

        // Database logic to get update user Using Prepared Statement
        try {
          Connection db = DatabaseConnection.getCon();
          PreparedStatement statement = db.prepareStatement(query);
          statement.setInt(1, healthData.getUserId());
          statement.setDouble(2, healthData.getWeight());
          statement.setDouble(3, healthData.getHeight());
          statement.setInt(4,healthData.getSteps());
          statement.setInt(5, healthData.getHeartRate());
            // Set a default date if healthData.getDate() is null
    LocalDate dateToInsert = healthData.getDate() != null ? healthData.getDate() : LocalDate.now();
    statement.setDate(6, java.sql.Date.valueOf(dateToInsert));
          int newRow = statement.executeUpdate();
          if(newRow != 0) {
              bool = true;
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }
   
    /**
     * Deletes health data from the database.
     *
     * @param id The ID of the health data to be deleted.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean deleteHealthData(int id) { /* delete health data from the database */ 
      boolean bool = false;
        // Prepare the SQL query
        String query = "DELETE FROM health_data WHERE id = ?";

        // Database logic to delete user
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated != 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    };
}
