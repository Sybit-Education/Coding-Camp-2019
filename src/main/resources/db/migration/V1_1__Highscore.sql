
CREATE TABLE highscore (
    id bigint NOT NULL AUTO_INCREMENT,
    score integer,
    timestamp TIME,
    user varchar NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO highscore(id, score, user)
VALUES (1, 250, 'Test1');

INSERT INTO highscore(id, score, user)
VALUES (2, 300, 'Test2');

INSERT INTO highscore(id, score, user)
VALUES (3, 350, 'Test3');
