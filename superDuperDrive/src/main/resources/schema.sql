CREATE TABLE IF NOT EXISTS USERS
(
    userId    INT PRIMARY KEY auto_increment,
    username  VARCHAR(20),
    salt      VARCHAR,
    password  VARCHAR,
    firstname VARCHAR(20),
    lastname  VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES
(
    noteId          INT PRIMARY KEY auto_increment,
    notetitle       VARCHAR(20),
    notedescription VARCHAR(1000),
    userId          INT,
    foreign key (userId) references USERS (userId)
);

CREATE TABLE IF NOT EXISTS FILES
(
    fileId      INT PRIMARY KEY auto_increment,
    filename    VARCHAR,
    contenttype VARCHAR,
    filesize    VARCHAR,
    userId      INT,
    filedata    BLOB,
    foreign key (userId) references USERS (userId)
);


CREATE TABLE IF NOT EXISTS CREDENTIALS
(
    credentialId INT PRIMARY KEY auto_increment,
    url          VARCHAR(100),
    username     VARCHAR(30),
    passwordKey  VARCHAR,
    password     VARCHAR,
    userId       INT,
    foreign key (userId) references USERS (userId)
);