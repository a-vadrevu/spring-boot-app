CREATE TABLE align_users (
id int not null,
role_id int not null,
user_name varchar(255) ,
password varchar(255),
email_addr varchar(255)
);

ALTER TABLE ALIGN_USERS ADD PRIMARY KEY(ID,ROLE_ID);

INSERT INTO ALIGN_USERS VALUES (1,1,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');
INSERT INTO ALIGN_USERS VALUES (1,2,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');
INSERT INTO ALIGN_USERS VALUES (1,3,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');
INSERT INTO ALIGN_USERS VALUES (2,1,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');
INSERT INTO ALIGN_USERS VALUES (2,2,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');
INSERT INTO ALIGN_USERS VALUES (2,3,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');
INSERT INTO ALIGN_USERS VALUES (3,1,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');
INSERT INTO ALIGN_USERS VALUES (4,1,'TEST_123', 'PASSWORD' , 'TEST123@GMAIL.COM');

CREATE TABLE ROLES (
role_id int not null,
role varchar(50)
);

INSERT  INTO ROLES VALUES (1,'TESTER');
INSERT  INTO ROLES VALUES (2,'Dev');
INSERT  INTO ROLES VALUES (3,'Manager');