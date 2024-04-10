CREATE TABLE health_data (
	med_id SERIAL PRIMARY KEY,
	daily_activities TEXT[] NOT NULL,
	heart_rate INT NOT NULL,
	blood_pressure VARCHAR(15) NOT NULL,
	med_records TEXT[] NOT NULL,
	patient_id SERIAL,
	FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id)
)