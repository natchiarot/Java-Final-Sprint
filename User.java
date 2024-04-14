/**
 * Represents a user in the health monitoring system.
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isDoctor;
    private String medicalLicenseNumber;
    private String specialization;

    /**
     * Constructs a new User object with the attributes specified.
     * 
     * @param id                   The unique users id.
     * @param firstName            The first name of the user.
     * @param lastName             The last name of the user.
     * @param email                The email address of the user.
     * @param password             The password of the user.
     * @param isDoctor             A boolean indicating whether the user is a doctor or not.
     * @param medicalLicenseNumber The medical license number of the user (if applicable).
     * @param specialization       The specialization of the user (if applicable).
     */

    public User(int id, String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDoctor = isDoctor;
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }

    /**
     * Constructs a new User object with the attributes specified.
     * 
     * @param firstName            The first name of the user.
     * @param lastName             The last name of the user.
     * @param email                The email address of the user.
     * @param password             The password of the user.
     * @param isDoctor             A boolean indicating whether the user is a doctor or not.
     * @param medicalLicenseNumber The medical license number of the user (if applicable).
     * @param specialization       The specialization of the user (if applicable).
     */

    // Added this so that the user doesn't have to enter a user id to create their account. Due to it being a serial value in sql it is automatically assigned a value.
    public User(String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDoctor = isDoctor;
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the is doctor boolean value of the user.
     *
     * @return The is doctor boolean of the user.
     */
    public boolean isDoctor() {
        return isDoctor;
    }

    /**
     * Sets the is doctor boolean value of the user.
     *
     * @param doctor The is doctor boolean value of the user.
     */
    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    /**
     * Gets the medical license if applicable of the user.
     *
     * @return The medical license if applicable of the user.
     */
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    /**
     * Sets the medical license if applicable of the user.
     *
     * @param medicalLicenseNumber The medical license if applicable of the user.
     */
    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    /**
     * Gets the specialization if applicable of the user.
     *
     * @return The specialization if applicable of the user.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization if applicable of the user.
     *
     * @param specialization The specialization if applicable of the user.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

     /**
     * Returns a string representation of the User object.
     * 
     * @return A string representation of the User object.
     */
    
    @Override
    public String toString() {
        return ("User " + id + ": " + firstName + lastName);
    }
}
