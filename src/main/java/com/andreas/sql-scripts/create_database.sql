/* Create and populate user table */
DROP TABLE IF EXISTS user;
CREATE TABLE user (
`id` INT(38) NOT NULL,
`firstName` VARCHAR(35) NOT NULL,
`lastName` VARCHAR(35) NOT NULL,
`userName` VARCHAR(25) NOT NULL,
`password` VARCHAR(25) NOT NULL
);

INSERT INTO user(id, firstName, lastName, userName, password)
VALUES
    (1,'Adam','Adamsson','AdamA','password'),
    (2,'Boris','Borisov','BorisB','password'),
    (3,'Catalina','Catalan','CatalinaC','password'),
    (4,'Denise','Denisedotter','DeniseD','password'),
    (5,'Erkan','Erkanoglhu','ErkanE','password');

ALTER TABLE user ADD PRIMARY KEY(id);
ALTER TABLE user MODIFY id INTEGER NOT NULL AUTO_INCREMENT;
SET @incrementValue = 0;
UPDATE user SET id = (SELECT @incrementValue := @incrementValue + 1);
/*********** END OF users TABLE CREATE ************/

/* Create and populate account table */
DROP TABLE IF EXISTS account;
CREATE TABLE account(
    `user_id` INT(38) NOT NULL,
    `balance` DECIMAL(30, 0) NOT NULL
);
INSERT INTO account(user_id, balance)
VALUES
    (1, 14000.0),
    (2, 35000.0),
    (3, 16350.3),
    (4, -12822.0),
    (5, 522.0);

/*********** END OF account TABLE CREATE ************/

/* Create and populate transactions table */
DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions(
`id` INT(38) NOT NULL,
`user_id` INT(38) NOT NULL,
`type` VARCHAR(15) NOT NULL,
`amount` DECIMAL(38, 0) NOT NULL,
`date` DATE NOT NULL
);
INSERT INTO transactions(id, user_id, type, amount, date)
VALUES
    (1, 1, 'DEPOSIT', 103.5, '2021-01-30'),
    (2, 1, 'DEPOSIT', 1200.0, '2021-02-16'),
    (3, 1, 'WITHDRAW', 250.5, '2021-02-18'),
    (4, 1, 'WITHDRAW', 956.0, '2021-02-22'),
    (5, 1, 'DEPOSIT', 13902.7, '2021-04-26'),
    (6, 2, 'DEPOSIT', 15800.0, '2021-02-13'),
    (7, 2, 'DEPOSIT', 16822.0, '2021-04-19'),
    (8, 2, 'DEPOSIT', 2378.0, '2021-05-01'),
    (9, 3, 'DEPOSIT', 20000.0, '2021-02-12'),
    (10, 3, 'WITHDRAW', 3650.0, '2021-02-16'),
    (11, 4, 'DEPOSIT', 9300.0, '2021-02-13'),
    (12, 4, 'WITHDRAW', 10350.0, '2021-04-19'),
    (13, 4, 'DEPOSIT', 952.0, '2021-05-01'),
    (14, 4, 'WITHDRAW', 12724.0, '2021-05-16'),
    (15, 5, 'DEPOSIT', 4320.0, '2021-02-19'),
    (16, 5, 'WITHDRAW', 3798.0, '2021-04-01');

ALTER TABLE transactions ADD PRIMARY KEY(id);
ALTER TABLE transactions MODIFY id INTEGER NOT NULL AUTO_INCREMENT;
/*********** END OF transactions TABLE CREATE ************/