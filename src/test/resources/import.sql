INSERT INTO USER_AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');

INSERT INTO MOVIE (ID, TITLE, DESCRIPTION) VALUES (1, 'Spider man', 'Action Movie');
INSERT INTO MOVIE(ID, TITLE, DESCRIPTION) VALUES (2, 'Despicable Me', 'Animation');

INSERT INTO USER (ID, EMAIL, PASSWORD, ENABLED) VALUES (1, 'email@test.com', '$2a$10$lDytwunczW4zm7/aXI4eP.O5Ei1FEgsI94opuyv6JQqk1mUMr0W6e', 1);
INSERT INTO USER_AUTHORITIES (USERS_ID, AUTHORITIES_ID) VALUES (1, 1);