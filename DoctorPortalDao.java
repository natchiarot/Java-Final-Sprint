import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The DoctorPortalDao class provides methods for interacting with the doctor portal data in the database.
 * It includes methods to retrieve doctor information, patient information, patients health data, and manage appointments.
 */
public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

   // Complete all these methods and add more as needed

   /**
     * Constructs a DoctorPortalDao object and initializes UserDao and HealthDataDao.
     */
    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    /**
     * Retrieves a doctor by their ID.
     *
     * @param doctorId The ID of the doctor to retrieve.
     * @return The doctor with the specified ID.
     */
    public Doctor getDoctorById(int doctorId) {
        User user = userDao.getUserById(doctorId);

        // Checking to see if the user is a doctor. If not, print message and return null.
        if (user.isDoctor()) {
        return new Doctor(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.isDoctor(), user.getMedicalLicenseNumber(), user.getSpecialization());
        } else {
            System.out.println("User is not a doctor!");
            return null;
        }
    }

    /**
     * Retrieves patients associated with a doctor by the doctor's ID.
     *
     * @param doctorId The ID of the doctor.
     * @return A list of patients associated with the doctor.
     */
    public List<User> getPatientsByDoctorId(int doctorId) {
        List<User> patientsByDoctorIdList = new ArrayList<>();
        int patient_id = 0;

        String query = "SELECT patient_id FROM doctor_patient WHERE doctor_id = ?";

        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
    
            statement.setInt(1, doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                patient_id = rs.getInt("patient_id");
                User patient = userDao.getUserById(patient_id);

                patientsByDoctorIdList.add(patient);
            }
            } catch (SQLException error) {
                error.printStackTrace();
            }
            return patientsByDoctorIdList;
    }

    /**
     * Retrieves health data for a patient by their ID.
     *
     * @param patientId The ID of the patient.
     * @return A list of health data for the patient.
     */
    public List<HealthData> getHealthDataByPatientId(int patientId) {
        List<HealthData> healthDataList = healthDataDao.getHealthDataByUserId(patientId);

        return healthDataList;
    }

    /**
     * Books an appointment between a doctor and a patient.
     *
     * @param doctorId        The ID of the doctor.
     * @param patientId       The ID of the patient.
     * @param appointmentDate The date and time of the appointment.
     * @return True if the appointment is successfully booked, false otherwise.
     */
    public boolean bookAppointment(int doctorId, int patientId, LocalDateTime appointmentDate) {
        boolean bool = false;
        String query = "INSERT INTO doctor_patient (doctor_id, patient_id, appointment) VALUES (?, ?, ?)";
        
        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
    
            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);
            statement.setObject(3, appointmentDate);

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
     * Updates an existing appointment between a doctor and a patient.
     *
     * @param doctorId        The ID of the doctor.
     * @param patientId       The ID of the patient.
     * @param appointmentDate The new date and time of the appointment.
     * @return True if the appointment is successfully updated, false otherwise.
     */
    public boolean updateAppointment(int doctorId, int patientId, LocalDateTime appointmentDate) {
        boolean bool = false;
        String query = "UPDATE doctor_patient SET appointment = ? WHERE doctor_id = ? AND patient_id = ?";

        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
    
            statement.setObject(1, appointmentDate);
            statement.setInt(2, doctorId);
            statement.setInt(3, patientId);

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
     * Cancels an existing appointment between a doctor and a patient.
     *
     * @param doctorId        The ID of the doctor.
     * @param patientId       The ID of the patient.
     * @param appointmentDate The date and time of the appointment to cancel.
     * @return True if the appointment is successfully canceled, false otherwise.
     */
    public boolean cancelAppointment(int doctorId, int patientId, LocalDateTime appointmentDate) {
        boolean bool = false;
        String query = "DELETE FROM doctor_patient WHERE doctor_id = ? AND patient_id = ? AND appointment = ?";

        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
    
            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);
            statement.setObject(3, appointmentDate);

           int newRow = statement.executeUpdate();
            if(newRow != 0) {
                bool = true;
            }
        } catch (SQLException error){
            error.printStackTrace();
        }
        return bool;
    }
}

