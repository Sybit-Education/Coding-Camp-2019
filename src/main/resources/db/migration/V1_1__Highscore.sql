
CREATE TABLE highscore (
    id bigint NOT NULL AUTO_INCREMENT,
    score integer,
    timestamp TIME,
    user varchar NOT NULL,
    PRIMARY KEY (id)
);
