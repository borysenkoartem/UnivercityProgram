DROP DATABASE practice8;
CREATE DATABASE practice8;
USE practice8;

CREATE TABLE users (
  id    INT PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(16) UNIQUE NOT NULL
);

CREATE TABLE teams (
  id   INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(16) UNIQUE NOT NULL
);

CREATE TABLE users_teams (
  user_id  INTEGER REFERENCES users (id),
  team_id INTEGER REFERENCES teams (id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, team_id)
);

INSERT INTO users VALUE
(DEFAULT, 'ivanov');

INSERT INTO teams VALUE
(DEFAULT, 'teamA');


SELECT u.login, ' in ', t.name
FROM users_teams ut
JOIN users u ON ut.user_id = u.id
JOIN teams t ON ut.team_id = t.id
