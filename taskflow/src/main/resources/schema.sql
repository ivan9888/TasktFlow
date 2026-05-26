-- Erase existing tables
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE
);

-- Create tasks table with its own foreign key
CREATE TABLE tasks (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(150) NOT NULL,
                        description VARCHAR(255),
                        state VARCHAR(50) NOT NULL,
                        user_id BIGINT NOT NULL,
                        CONSTRAINT fk_users_tasks FOREIGN KEY (user_id) REFERENCES users(id)
);