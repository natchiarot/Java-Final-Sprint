import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the ability to generate health recommendations
 * based on user's individual health data and store them in the database.
 */
public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final int MIN_STEPS = 10000;

    // Creating a date instance for the current date to conform with the sql data type.
    LocalDate currentDate = LocalDate.now();
    
    /**
     * Generates health recommendations based on the user's heart rate and weight to height relation.
     *
     * @param healthData The health data of the user.
     * @return A list of recommendations.
     */
    public List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<>();

//        // Analyze heart rate
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
            recommendations.add("Your heart rate is lower than the recommended range. " +
                    "Consider increasing your physical activity to improve your cardiovascular health.");
        }

        heartRate = healthData.getHeartRate();
        if (heartRate > MAX_HEART_RATE) {
            recommendations.add("Your heart rate is higher than the reccomended range. Try taking it easy to improve your cardiovascular health. " +
            "Could be due to heavy exercise, stress, low blood sugar or low blood pressure. May");
        }
//
//
//        // Analyze steps
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
            recommendations.add("You're not reaching the recommended daily step count (" + MIN_STEPS +
                    " steps). Try to incorporate more walking or other physical activities into your daily routine.");
        }

        // Analyze BMI
        double weight = healthData.getWeight();
        double height = healthData.getHeight();

        // BMI calculating formula (height in inches and weight in pounds).
        double BMI = weight / (height * height) * 703;

        if (BMI < 18.5) {
                recommendations.add("Your Body Mass Index (BMI) falls within the underweight range. Consider diet and exercise changes.");
        }
        if (BMI >= 25.0 && BMI <= 29.9) {
                recommendations.add("Your Body Mass Index (BMI) falls within the overweight range. Consider making diet and exercise changes.");
        }
        if (BMI >= 30.0) {
                recommendations.add("Your Body Mass Index (BMI) falls within the obese range. Consider making diet and exercise changes.");
        }

        return recommendations;
    }

    /**
     * Stores the recommendations for a user in the database.
     *
     * @param userId          The ID of the user.
     * @param recommendations The list of recommendations to store.
     * @return True if recommendations are successfully stored, false otherwise.
     */
    public boolean createRecommendation(int userId, List<String> recommendations) {
        boolean bool = false;
    
    String query = "INSERT INTO recommendations (user_id, recommendation_text, date) VALUES (?, ?, ?)";

    // Database logic to insert data using PREPARED Statement
    try {
    Connection db = DatabaseConnection.getCon();
    PreparedStatement statement = db.prepareStatement(query);
    for (String recommendation : recommendations) {
        statement.setInt(1, userId);
        statement.setString(2, recommendation);
        statement.setDate(3, Date.valueOf(currentDate));

        int newRow = statement.executeUpdate();

        if(newRow != 0) {
                bool = true;
            }
    }
    
    } catch (SQLException error){
        error.printStackTrace();
    }
    return bool;
    };

    /**
     * Updates the recommendations for a user in the database.
     *
     * @param userId          The ID of the user.
     * @param recommendations The list of recommendations to update.
     * @return True if recommendations are successfully updated, false otherwise.
     */
    public boolean updateRecommendation(int userId, List<String> recommendations) {
        boolean bool = false;

        // Prepare the SQL query
        String query = "UPDATE recommendations SET user_id = ?, recommendation_text = ?, date = ?";
        // Database logic to get update user Using Prepared Statement
        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);

            for (String recommendation : recommendations) {
                statement.setInt(1, userId);
                statement.setString(2, recommendation);
                statement.setDate(3, Date.valueOf(currentDate));
        
                int newRow = statement.executeUpdate();
        
                if(newRow != 0) {
                        bool = true;
                    }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return bool;
    }

     /**
     * Deletes recommendations from the database.
     *
     * @param id The ID of the recommendations to delete.
     * @return True if recommendations are successfully deleted, false otherwise.
     */
    public boolean deleteRecommendation(int id) {
        boolean bool = false;
          // Prepare the SQL query
          String query = "DELETE FROM recommendations WHERE id = ?";
  
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
