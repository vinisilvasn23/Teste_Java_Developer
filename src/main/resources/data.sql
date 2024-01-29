CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    cpfCnpj VARCHAR(14),
    email VARCHAR(255),
    password VARCHAR(255),
    type VARCHAR(20),
    balance DECIMAL(10,2),
    fee DECIMAL(10,2),
    PRIMARY KEY (id)
);

CREATE TABLE transactions (
    id INT NOT NULL AUTO_INCREMENT,
    clientId INT,
    companyId INT,
    amount DECIMAL(10,2),
    type VARCHAR(10),
    FOREIGN KEY (clientId) REFERENCES users(id),
    FOREIGN KEY (companyId) REFERENCES users(id),
    PRIMARY KEY (id)
);