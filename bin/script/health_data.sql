CREATE TABLE health_data (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    weight_pounds DECIMAL(5,1) NOT NULL,
    height_inches DECIMAL(4,1) NOT NULL,
    steps INT NOT NULL,
    heart_rate INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
)
