-- Create tables
CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS employee (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        firstname VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    roleid BIGINT,
    FOREIGN KEY (roleid) REFERENCES role(id)
    );

CREATE TABLE IF NOT EXISTS project (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employee(id)
    );

-- Insert roles
INSERT INTO role (name) VALUES ('Developer'), ('Manager');

-- Insert employees
INSERT INTO employee (firstname, surname, roleid) VALUES ('John', 'Doe', 1), ('Jane', 'Smith', 2);

-- Insert projects
INSERT INTO project (name, employee_id) VALUES ('Project A', 1), ('Project B', 2);
