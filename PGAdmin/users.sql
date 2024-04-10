CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	first_name VARCHAR(150) NOT NULL,
	last_name VARCHAR(150) NOT NULL,
	email VARCHAR(255) NOT NULL,
	username VARCHAR(25) UNIQUE NOT NULL,
	hashed_password TEXT NOT NULL,
	role varchar(10) NOT NULL
)