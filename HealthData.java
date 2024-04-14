import java.time.LocalDate;

/**
 * The HealthData class represents health data recorded for a specific user on a specific date.
 */
public class HealthData {
    private int id;
    private int userId;
    private double weight;
    private double height;
    private int steps;
    private int heartRate;
    private LocalDate date;

    // Constructor, getters, and setters
    /**
     * Constructs a HealthData object with the specified attributes.
     *
     * @param id        The ID of the health data entry.
     * @param userId    The ID of the user to whom the health data belongs.
     * @param weight    The weight of the user.
     * @param height    The height of the user.
     * @param steps     The number of steps taken by the user.
     * @param heartRate The heart rate of the user.
     * @param date      The date when the health data was recorded.
     */
    public HealthData(int id, int userId, double weight, double height, int steps, int heartRate, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
    }

    // Added for user inputs.
    /**
     * Constructs a HealthData object with the specified attributes, excluding the ID.
     *
     * @param userId    The ID of the user to whom the health data belongs.
     * @param weight    The weight of the user.
     * @param height    The height of the user.
     * @param steps     The number of steps taken by the user.
     * @param heartRate The heart rate of the user.
     * @param date      The date when the health data was recorded.
     */
    public HealthData(int userId, double weight, double height, int steps, int heartRate, LocalDate date) {
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
    }

    /**
     * Retrieves the ID of the health data entry.
     *
     * @return The ID of the health data entry.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the health data entry.
     *
     * @param id The ID of the health data entry.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the user to whom the health data belongs.
     *
     * @return The ID of the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user to whom the health data belongs.
     *
     * @param userId The ID of the user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the weight of the user.
     *
     * @return The weight of the user.
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * Sets the weight of the user.
     *
     * @param weight The weight of the user.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Retrieves the height of the user.
     *
     * @return The height of the user.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the user.
     *
     * @param height The height of the user.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Retrieves the number of steps taken by the user.
     *
     * @return The number of steps taken by the user.
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Sets the number of steps taken by the user.
     *
     * @param steps The number of steps taken by the user.
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * Retrieves the heart rate of the user.
     *
     * @return The heart rate of the user.
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * Sets the heart rate of the user.
     *
     * @param heartRate The heart rate of the user.
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * Retrieves the date when the health data was recorded.
     *
     * @return The date when the health data was recorded.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the health data was recorded.
     *
     * @param date The date when the health data was recorded.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

     /**
     * Returns a string representation of the HealthData object.
     *
     * @return A string representation of the HealthData object.
     */
    @Override
    public String toString() {
        return ("User [" + userId + "]: \n" +
        "Weight " + weight + ", Height " + height + ", Steps" + steps + ", heart rate " + heartRate + ", date added " + date);
    }
}
