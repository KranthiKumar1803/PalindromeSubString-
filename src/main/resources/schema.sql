DROP TABLE IF EXISTS Palindrome;

CREATE TABLE Palindrome(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
palindrome_string varchar2(1000),
PRIMARY KEY (id)
);