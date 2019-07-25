CREATE TABLE user (
    id bigint NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE game (
    id bigint NOT NULL AUTO_INCREMENT,
    attempt_count integer (99),
    start_time TIME,
    end_time TIME,
    user bigint NOT NULL,
    FOREIGN KEY (user) REFERENCES user(id),
    PRIMARY KEY (id)
);
