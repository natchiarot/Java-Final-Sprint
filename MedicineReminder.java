import java.time.LocalDate;

/**
 * The MedicineReminder class represents a medicine reminder and its details for a user.
 */
public class MedicineReminder {
    private int id;
    private int userId;
    private String medicineName;
    private String dosage;
    private String schedule;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor, getters, and setters
     /**
     * Constructs a MedicineReminder object with specified details.
     *
     * @param id           The ID of the reminder.
     * @param userId       The ID of the user associated with the reminder.
     * @param medicineName The name of the medicine.
     * @param dosage       The dosage of the medicine.
     * @param schedule     The schedule for what time to take the medicine.
     * @param startDate    The start date for the reminder.
     * @param endDate      The end date for the reminder.
     */
    public MedicineReminder(int id, int userId, String medicineName, String dosage, String schedule, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.userId = userId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Added for user input.
    /**
     * Constructs a MedicineReminder object with specified details.
     *
     * @param userId       The ID of the user associated with the reminder.
     * @param medicineName The name of the medicine.
     * @param dosage       The dosage of the medicine.
     * @param schedule     The schedule for taking the medicine.
     * @param startDate    The start date for the reminder.
     * @param endDate      The end date for the reminder.
     */
    public MedicineReminder(int userId, String medicineName, String dosage, String schedule, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets the ID of the reminder.
     *
     * @return The ID of the reminder.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the reminder.
     *
     * @param id The ID of the reminder.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the user associated with the reminder.
     *
     * @return The ID of the user associated with the reminder.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with the reminder.
     *
     * @param userId The ID of the user associated with the reminder.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

     /**
     * Gets the name of the medicine.
     *
     * @return The name of the medicine.
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Sets the name of the medicine.
     *
     * @param medicineName The name of the medicine.
     */
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    
    /**
     * Gets the dosage of the medicine.
     *
     * @return The dosage of the medicine.
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage of the medicine.
     *
     * @param dosage The dosage of the medicine.
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Gets the schedule for taking the medicine.
     *
     * @return The schedule for taking the medicine.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the schedule for taking the medicine.
     *
     * @param schedule The schedule for taking the medicine.
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * Gets the start date for the reminder.
     *
     * @return The start date for the reminder.
     */
   public LocalDate getStartDate() {
       return startDate;
   }

   /**
     * Sets the start date for the reminder.
     *
     * @param startDate The start date for the reminder.
     */
   public void setStartDate(LocalDate startDate) {
       this.startDate = startDate;
   }

   /**
     * Gets the end date for the reminder.
     *
     * @return The end date for the reminder.
     */
   public LocalDate getEndDate() {
       return endDate;
   }

   /**
     * Sets the end date for the reminder.
     *
     * @param endDate The end date for the reminder.
     */
   public void setEndDate(LocalDate endDate) {
       this.endDate = endDate;
   }

   /**
     * Returns a string representation of the MedicineReminder object.
     *
     * @return A string representation of the MedicineReminder object.
     */
   @Override
   public String toString() {
    return ("medicine name = " + medicineName + '\n' +
    "dosage = " + dosage + '\n' +
    "schedule = " + schedule + '\n' +
    "start date = " + startDate + '\n' +
    "end date = " + endDate
    );
   }
}
