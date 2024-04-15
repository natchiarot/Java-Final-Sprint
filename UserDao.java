import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

/**
 * Data Access Object (DAO) for managing user data in the database.
 */

public class UserDao {
   
    /**
     * Creates a new user in the database.
     *
     * @param user The user object that's created.
     * @return True if the user is successfully created, otherwise false.
     */
    public boolean createUser(User user) {
        boolean bool = false;
        // insert user into database 
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        // Prepare the SQL query
        String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor, medical_license, specialization) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Database logic to insert data using PREPARED Statement
        try {
        Connection db = DatabaseConnection.getCon();
        PreparedStatement statement = db.prepareStatement(query);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4,hashedPassword);
        statement.setBoolean(5, user.isDoctor());
        statement.setString(6, user.getMedicalLicenseNumber());
        statement.setString(7, user.getSpecialization());

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
     * Gets a user from the database by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object corresponding to the given ID, or null if not found.
     */
    public User getUserById(int id) { //get user by id from database 
        int user_id = 0;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        boolean is_doctor = false;
        String medicalLicenseNumber = null;
        String specialization = null;
        
        // Prepare the SQL query
        String query = "SELECT * FROM users WHERE id = ?";
        
        try {
        // Database logic to get data by ID Using Prepared Statement
        Connection db = DatabaseConnection.getCon();
        PreparedStatement statement = db.prepareStatement(query);

        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            user_id = rs.getInt("id");
            firstName = rs.getString("first_name");
            lastName = rs.getString("last_name");
            email = rs.getString("email");
            password = rs.getString("password");
            is_doctor = rs.getBoolean("is_doctor");
            medicalLicenseNumber = rs.getString("medical_license");
            specialization = rs.getString("specialization");
        }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return new User(user_id, firstName, lastName, email, password, is_doctor, medicalLicenseNumber, specialization);
    }

    /**
     * Gets a user from the database by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object connected to the given email address.
     */
    public User getUserByEmail(String email) { // get user by email from database 
        int id = 0;
        String firstName = null;
        String lastName = null;
        String userEmail = null;
        String password = null;
        boolean is_doctor = false;
        String medicalLicenseNumber = null;
        String specialization = null;

        // Prepare the SQL query
        String query = "SELECT * FROM users WHERE email = ?";

        // Database logic to get data by ID Using Prepared Statement
        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                userEmail = rs.getString("email");
                password = rs.getString("password");
                is_doctor = rs.getBoolean("is_doctor");
                medicalLicenseNumber = rs.getString("medical_license");
                specialization = rs.getString("specialization");
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
        return new User(id, firstName, lastName, userEmail, password, is_doctor, medicalLicenseNumber, specialization);
    }

     /**
     * Updates an existing user in the database.
     *
     * @param user The user object with the updated information.
     * @return True if the user is successfully updated, false otherwise.
     */
    public boolean updateUser(User user) {
        boolean bool = false;

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        // Prepare the SQL query
        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, isDoctor = ?, medical_license = ?, specialization = ?";
        // Database logic to get update user Using Prepared Statement
        try {
            Connection db = DatabaseConnection.getCon();
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4,hashedPassword);
            statement.setBoolean(5, user.isDoctor());
            statement.setString(6, user.getMedicalLicenseNumber());
            statement.setString(7, user.getSpecialization());

        } catch (SQLException error) {
            error.printStackTrace();
        }
        return bool;
    }
    
    /**
     * Deletes a user from the database.
     *
     * @param id The ID of the user to delete.
     * @return True if the user is successfully deleted, false otherwise.
     */
    public boolean deleteUser(int id) { // delete user from the database 
        boolean bool = false;

        // Prepare the SQL query
        String query = "DELETE * FROM users WHERE id = ?";

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

    /**
     * Verify the password for a given user email.
     *
     * @param email    The email address of the user.
     * @param password The password to verify.
     * @return True if the password matches the hashed password in the database, false otherwise.
     */
    public boolean verifyPassword (String email, String password) {
        boolean bool = false;

        String query = "SELECT password FROM users WHERE email = ?";    // SQL Statement

        //Implement logic to retrieve password using the Bcrypt
        try {
        Connection db = DatabaseConnection.getCon();
        PreparedStatement statement = db.prepareStatement(query);

        statement.setString(1, email);

        ResultSet rs = statement.executeQuery();

        String hashedPassword = null;
        while(rs.next()) {
            hashedPassword = rs.getString("password");
        }
        if (BCrypt.checkpw(password, hashedPassword)) {
            bool = true;
        }
        
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return bool;
    }

}
