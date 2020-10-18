CREATE TABLE people(id int primary key auto_increment, name VARCHAR(255), phone_number VARCHAR(255), email_address VARCHAR(255));
CREATE TABLE books(id int primary key auto_increment, title VARCHAR(255), author VARCHAR(255), ISBN VARCHAR(20), lent_person_id int);
