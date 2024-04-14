CREATE TABLE medication_reminders (
	medication_id SERIAL PRIMARY KEY,
	medication_name VARCHAR(100) NOT NULL,
	medication_time TIME NOT NULL,
	reminder BOOLEAN NOT NULL,
	patient_id SERIAL,
	FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id)
)