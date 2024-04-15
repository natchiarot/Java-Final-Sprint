/**
 * The Doctor class represents a user who is a doctor - with a medical license and specialization.
 * It extends the User class.
 */
public class Doctor extends User{

    /**
     * Constructs a Doctor object with the specified attributes.
     *
     * @param id                   The ID of the doctor.
     * @param firstName            The first name of the doctor.
     * @param lastName             The last name of the doctor.
     * @param email                The email address of the doctor.
     * @param password             The password of the doctor.
     * @param isDoctor             A boolean indicating whether the user is a doctor.
     * @param medicalLicenseNumber The medical license number of the doctor.
     * @param specialization       The specialization of the doctor.
     */
    public Doctor(int id, String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
        super(id, firstName, lastName, email, password, isDoctor, medicalLicenseNumber, specialization);
    }

    // Getters and setters
    
    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public boolean isDoctor() {
        return super.isDoctor();
    }

    @Override
   public void setDoctor(boolean doctor) {
       super.setDoctor(doctor);
   }

   @Override
   public String getMedicalLicenseNumber() {
       return super.getMedicalLicenseNumber();
   }

   @Override
   public void setMedicalLicenseNumber(String medicalLicenseNumber) {
       super.setMedicalLicenseNumber(medicalLicenseNumber);
   }

    @Override
    public String getSpecialization() {
        return super.getSpecialization();
    }

   @Override
   public void setSpecialization(String specialization) {
       super.setSpecialization(specialization);
   }

   /**
     * Returns a string representation of the doctor.
     *
     * @return A string containing the doctor's name.
     */
   @Override
   public String toString() {
       return ("Dr. " + super.getFirstName() + " " + super.getLastName());
   }

}

