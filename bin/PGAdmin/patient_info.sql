CREATE TABLE patient_info (
	patient_id SERIAL PRIMARY KEY,
	DOB DATE NOT NULL,
	gender CHAR NOT NULL,
	emergency_contact VARCHAR(15) NOT NULL,
	phone_num VARCHAR(15) NOT NULL,
	address VARCHAR (100) NOT NULL,
	height_inches INT NOT NULL,
	weight_pounds NUMERIC (5,1) NOT NULL,
	exercise_level INT NOT NULL,
	user_id SERIAL,
	doctor_id SERIAL,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (doctor_id) REFERENCES doctor_info(doctor_id)
)