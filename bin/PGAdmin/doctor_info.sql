CREATE TABLE doctor_info (
	doctor_id SERIAL PRIMARY KEY,
	user_id SERIAL,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
)