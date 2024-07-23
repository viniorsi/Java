CREATE TABLE users
(
    id              bigint PRIMARY KEY AUTO_INCREMENT,
    Cpf             varchar(11) UNIQUE NOT NULL,
    name            varchar(100)       NOT NULL,
    tel             varchar(9),
    ddd             varchar(2),
    email           varchar(30)        NOT NULL,
    password        varchar(255),
    birthday        date,
    creation_date   date,
    zip_code        varchar(8),
    address         varchar(150),
    number          varchar(4),
    additional_info varchar(8),
    neigborhood     varchar(100),
    state           varchar(2),
    city            varchar(30),
    status          char(1),
    points          INT
);


CREATE TABLE UsersVerification
(
    id                bigint PRIMARY KEY AUTO_INCREMENT,
    user_id           bigint unique NOT NULL,
    verification_code varchar(255)  NOT NULL,
    expiration_date   date,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE html_templates
(
    id      bigint AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    content TEXT         NOT NULL
);



CREATE TABLE destiny
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    country     VARCHAR(25)   NOT NULL,
    description VARCHAR(255)  NOT NULL,
    image_url   varchar(255)  NOT NULL,
    daily_value decimal(5, 2) NOT NULL,
    continent   VARCHAR(20)   NOT NULL
);
CREATE TABLE AIRPLANE
(
    id                  bigint AUTO_INCREMENT PRIMARY KEY,
    destiny_id          bigint,
    model               VARCHAR(50) NOT NULL,
    registration_number VARCHAR(15) NOT NULL,
    capacity            INT         NOT NULL,
    status              char(1)     NOT NULL,
    airline             VARCHAR(50) NOT NULL,
    FOREIGN KEY (destiny_id) REFERENCES destiny (id)

);


CREATE TABLE SEAT
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    airplane_id bigint(50) NOT NULL,
    seat_number varchar(5) NOT NULL,
    status      char(1)    NOT NULL,
    FOREIGN KEY (airplane_id) REFERENCES AIRPLANE (id)
);


INSERT INTO destiny (country, description, image_url, daily_value, continent)
VALUES ('NIGERIA', 'Descrição da Nigéria', 'http://example.com/nigeria.jpg', 100.0, 'AFRICA'),
       ('EGITO', 'Descrição do Egito', 'http://example.com/egito.jpg', 120.0, 'AFRICA'),
       ('AFRICA_DO_SUL', 'Descrição da África do Sul', 'http://example.com/africa_do_sul.jpg', 130.0, 'AFRICA'),
       ('QUENIA', 'Descrição do Quênia', 'http://example.com/kenia.jpg', 110.0, 'AFRICA'),
       ('GANA', 'Descrição de Gana', 'http://example.com/gana.jpg', 140.0, 'AFRICA');

INSERT INTO destiny (country, description, image_url, daily_value, continent)
VALUES ('ESTADOS_UNIDOS', 'Descrição dos Estados Unidos', 'http://example.com/eua.jpg', 200.0, 'AMERICA_DO_NORTE'),
       ('CANADA', 'Descrição do Canadá', 'http://example.com/canada.jpg', 210.0, 'AMERICA_DO_NORTE'),
       ('MEXICO', 'Descrição do México', 'http://example.com/mexico.jpg', 180.0, 'AMERICA_DO_NORTE'),
       ('CUBA', 'Descrição de Cuba', 'http://example.com/cuba.jpg', 170.0, 'AMERICA_DO_NORTE'),
       ('REPUBLICA_DOMINICANA', 'Descrição da República Dominicana', 'http://example.com/republica_dominicana.jpg',
        160.0, 'AMERICA_DO_NORTE');

INSERT INTO destiny (country, description, image_url, daily_value, continent)
VALUES ('BRASIL', 'Descrição do Brasil', 'http://example.com/brasil.jpg', 150.0, 'AMERICA_DO_SUL'),
       ('ARGENTINA', 'Descrição da Argentina', 'http://example.com/argentina.jpg', 140.0, 'AMERICA_DO_SUL'),
       ('COLOMBIA', 'Descrição da Colômbia', 'http://example.com/colombia.jpg', 130.0, 'AMERICA_DO_SUL'),
       ('CHILE', 'Descrição do Chile', 'http://example.com/chile.jpg', 120.0, 'AMERICA_DO_SUL'),
       ('PERU', 'Descrição do Peru', 'http://example.com/peru.jpg', 110.0, 'AMERICA_DO_SUL');

INSERT INTO destiny (country, description, image_url, daily_value, continent)
VALUES ('CHINA', 'Descrição da China', 'http://example.com/china.jpg', 250.0, 'ASIA'),
       ('INDIA', 'Descrição da Índia', 'http://example.com/india.jpg', 230.0, 'ASIA'),
       ('JAPAO', 'Descrição do Japão', 'http://example.com/japao.jpg', 220.0, 'ASIA'),
       ('COREIA_DO_SUL', 'Descrição da Coreia do Sul', 'http://example.com/coreia_do_sul.jpg', 210.0, 'ASIA'),
       ('INDONESIA', 'Descrição da Indonésia', 'http://example.com/indonesia.jpg', 200.0, 'ASIA');

INSERT INTO destiny (country, description, image_url, daily_value, continent)
VALUES ('ALEMANHA', 'Descrição da Alemanha', 'http://example.com/alemanha.jpg', 190.0, 'EUROPA'),
       ('FRANCA', 'Descrição da França', 'http://example.com/franca.jpg', 180.0, 'EUROPA'),
       ('REINO_UNIDO', 'Descrição do Reino Unido', 'http://example.com/reino_unido.jpg', 170.0, 'EUROPA'),
       ('ITALIA', 'Descrição da Itália', 'http://example.com/italia.jpg', 160.0, 'EUROPA'),
       ('ESPANHA', 'Descrição da Espanha', 'http://example.com/espanha.jpg', 150.0, 'EUROPA');

INSERT INTO destiny (country, description, image_url, daily_value, continent)
VALUES ('AUSTRALIA', 'Descrição da Austrália', 'http://example.com/australia.jpg', 140.0, 'OCEANIA'),
       ('NOVA_ZELANDIA', 'Descrição da Nova Zelândia', 'http://example.com/nova_zelandia.jpg', 130.0, 'OCEANIA'),
       ('FIJI', 'Descrição de Fiji', 'http://example.com/fiji.jpg', 120.0, 'OCEANIA'),
       ('PAPUA_NOVA_GUINE', 'Descrição de Papua-Nova Guiné', 'http://example.com/papua_nova_guine.jpg', 110.0,
        'OCEANIA'),
       ('SAMOA', 'Descrição de Samoa', 'http://example.com/samoa.jpg', 100.0, 'OCEANIA');

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'N12345', 200, 'A', 'American Airlines',
        (SELECT id FROM destiny WHERE country = 'ESTADOS_UNIDOS')),
       ('Airbus A320', 'N54321', 180, 'A', 'United Airlines',
        (SELECT id FROM destiny WHERE country = 'ESTADOS_UNIDOS'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 787', 'C12345', 300, 'A', 'Air Canada', (SELECT id FROM destiny WHERE country = 'CANADA')),
       ('Airbus A330', 'C54321', 250, 'A', 'WestJet', (SELECT id FROM destiny WHERE country = 'CANADA'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'M12345', 200, 'A', 'Aeromexico', (SELECT id FROM destiny WHERE country = 'MEXICO')),
       ('Airbus A320', 'M54321', 180, 'A', 'Volaris', (SELECT id FROM destiny WHERE country = 'MEXICO'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'CU12345', 200, 'A', 'Cubana de Aviación', (SELECT id FROM destiny WHERE country = 'CUBA')),
       ('Airbus A320', 'CU54321', 180, 'A', 'Cubana de Aviación', (SELECT id FROM destiny WHERE country = 'CUBA'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'RD12345', 200, 'A', 'Pawa Dominicana',
        (SELECT id FROM destiny WHERE country = 'REPUBLICA_DOMINICANA')),
       ('Airbus A320', 'RD54321', 180, 'A', 'Pawa Dominicana',
        (SELECT id FROM destiny WHERE country = 'REPUBLICA_DOMINICANA'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'BR12345', 200, 'A', 'LATAM Airlines', (SELECT id FROM destiny WHERE country = 'BRASIL')),
       ('Airbus A320', 'BR54321', 180, 'A', 'GOL Airlines', (SELECT id FROM destiny WHERE country = 'BRASIL'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'AR12345', 200, 'A', 'Aerolineas Argentinas',
        (SELECT id FROM destiny WHERE country = 'ARGENTINA')),
       ('Airbus A320', 'AR54321', 180, 'A', 'Austral Líneas Aéreas',
        (SELECT id FROM destiny WHERE country = 'ARGENTINA'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'CO12345', 200, 'A', 'Avianca', (SELECT id FROM destiny WHERE country = 'COLOMBIA')),
       ('Airbus A320', 'CO54321', 180, 'A', 'LATAM Airlines', (SELECT id FROM destiny WHERE country = 'COLOMBIA'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'CH12345', 200, 'A', 'LATAM Airlines', (SELECT id FROM destiny WHERE country = 'CHILE')),
       ('Airbus A320', 'CH54321', 180, 'A', 'Sky Airline', (SELECT id FROM destiny WHERE country = 'CHILE'));

INSERT INTO airplane (model, registration_number, capacity, status, airline, destiny_id)
VALUES ('Boeing 737', 'PE12345', 200, 'A', 'LATAM Airlines', (SELECT id FROM destiny WHERE country = 'PERU')),
       ('Airbus A320', 'PE54321', 180, 'A', 'Viva Air Peru', (SELECT id FROM destiny WHERE country = 'PERU'));

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'N54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'C54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'M54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CU54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'RD54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'BR54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'AR54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CO54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'CH54321'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE12345'), '4A', 'L');

INSERT INTO seat (airplane_id, seat_number, status)
VALUES ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '1A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '1B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '1C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '2A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '2B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '2C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '3A', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '3B', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '3C', 'L'),
       ((SELECT id FROM airplane WHERE registration_number = 'PE54321'), '4A', 'L');


CREATE TABLE hotels
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    destiny_id  bigint         NOT NULL,
    name        VARCHAR(100)   NOT NULL,
    description VARCHAR(255)   NOT NULL,
    type        varchar(100)   NOT NULL,
    value       decimal(10, 2) NOT NULL,
    restriction varchar(255)   NOT NULL,
    pet         boolean        NOT NULL,
    FOREIGN KEY (destiny_id) REFERENCES destiny (id)
);


CREATE TABLE travels
(
    id                 bigint AUTO_INCREMENT PRIMARY KEY,
    user_id            bigint      NOT NULL,
    destiny_id         bigint      NOT NULL,
    departure_location varchar(25) NOT NULL,
    departure_date     DATETIME    NOT NULL,
    return_date        DATETIME    NOT NULL,
    round_trip         INT         NOT NULL,
    tickets_count      int         NOT NULL,
    FOREIGN KEY (destiny_id) REFERENCES destiny (id),
    FOREIGN KEY (user_id) REFERENCES users (id)

);

CREATE TABLE travel_hotels
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    travel_id   bigint   NOT NULL,
    hotels_id   bigint   NOT NULL,
    adult_count INT      NOT NULL,
    kids_count  INT      NOT NULL,
    pet         boolean  NOT NULL,
    entry_date  DATETIME NOT NULL,
    end_date    DATETIME NOT NULL,
    FOREIGN KEY (travel_id) REFERENCES travels (id),
    FOREIGN KEY (hotels_id) REFERENCES hotels (id)
);
CREATE TABLE Transactional
(
    id                bigint AUTO_INCREMENT PRIMARY KEY,
    user_id           bigint         NOT NULL,
    travel_id         bigint         NOT NULL,
    points            INT            NOT NULL,
    value             decimal(10, 2) NOT NULL,
    transaction_date  DATETIME       NOT NULL,
    payment_dead_line DATETIME       NOT NULL,
    status_payment    char(1)        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (travel_id) REFERENCES travels (id)
);


INSERT INTO hotels (name, description, type, value, restriction, pet, destiny_id)
VALUES ('Hotel Lagos', 'Luxuoso hotel em Lagos, Nigéria', 'Hotel', 250.00, 'Nenhuma', true, 1),
       ('Hotel Cairo', 'Hotel com vista para as pirâmides no Cairo, Egito', 'Resort', 300.00, 'Proibido fumar', false,
        2),
       ('Cape Town Lodge', 'Aconchegante lodge na Cidade do Cabo, África do Sul', 'Lodge', 180.00, 'Nenhuma', true, 3),
       ('Safari Hotel', 'Hotel próximo ao Parque Nacional do Quênia', 'Hotel', 220.00, 'Nenhuma', false, 4),
       ('Accra Beach Resort', 'Resort de luxo na praia de Accra, Gana', 'Resort', 270.00, 'Nenhuma', true, 5);

INSERT INTO hotels (name, description, type, value, restriction, pet, destiny_id)
VALUES ('New York Hotel', 'Hotel de luxo em Nova York, EUA', 'Hotel', 350.00, 'Nenhuma', true, 6),
       ('Toronto Inn', 'Hotel confortável em Toronto, Canadá', 'Inn', 320.00, 'Nenhuma', false, 7),
       ('Cancun Resort', 'Resort all-inclusive em Cancun, México', 'Resort', 300.00, 'Nenhuma', true, 8),
       ('Havana Club', 'Hotel histórico em Havana, Cuba', 'Hotel', 280.00, 'Nenhuma', false, 9),
       ('Punta Cana Resort', 'Resort de luxo em Punta Cana, República Dominicana', 'Resort', 260.00, 'Nenhuma', true,
        10);


INSERT INTO hotels (name, description, type, value, restriction, pet, destiny_id)
VALUES ('Rio Palace', 'Hotel luxuoso em Copacabana', 'Hotel', 220.00, 'Nenhuma', true, 11),
       ('Buenos Aires Plaza', 'Hotel elegante em Buenos Aires', 'Hotel', 210.00, 'Nenhuma', false, 12),
       ('Bogota Grand Hotel', 'Hotel central em Bogotá', 'Hotel', 200.00, 'Nenhuma', true, 13),
       ('Santiago Resort', 'Resort all-inclusive em Santiago', 'Resort', 190.00, 'Nenhuma', false, 14),
       ('Lima Beach Hotel', 'Hotel de praia em Lima', 'Hotel', 180.00, 'Nenhuma', true, 15);

INSERT INTO hotels (name, description, type, value, restriction, pet, destiny_id)
VALUES ('Beijing Grand Hotel', 'Hotel luxuoso em Pequim', 'Hotel', 320.00, 'Nenhuma', false, 16),
       ('Taj Mahal Palace', 'Hotel histórico em Mumbai', 'Hotel', 300.00, 'Nenhuma', true, 17),
       ('Tokyo Tower Hotel', 'Hotel moderno em Tóquio', 'Hotel', 290.00, 'Nenhuma', false, 18),
       ('Seoul Plaza', 'Hotel elegante em Seul', 'Hotel', 280.00, 'Nenhuma', true, 19),
       ('Bali Beach Resort', 'Resort de luxo em Bali', 'Resort', 270.00, 'Nenhuma', false, 20);
INSERT INTO hotels (name, description, type, value, restriction, pet, destiny_id)
VALUES ('Berlin Grand Hotel', 'Hotel luxuoso em Berlim', 'Hotel', 260.00, 'Nenhuma', false, 21),
       ('Paris Eiffel Hotel', 'Hotel elegante em Paris', 'Hotel', 250.00, 'Nenhuma', true, 22),
       ('London Royal Palace', 'Hotel clássico em Londres', 'Hotel', 240.00, 'Nenhuma', false, 23),
       ('Rome Colosseum Hotel', 'Hotel histórico em Roma', 'Hotel', 230.00, 'Nenhuma', true, 24),
       ('Madrid Central Hotel', 'Hotel central em Madri', 'Hotel', 220.00, 'Nenhuma', false, 25);
INSERT INTO hotels (name, description, type, value, restriction, pet, destiny_id)
VALUES ('Sydney Opera Hotel', 'Hotel luxuoso em Sydney', 'Hotel', 210.00, 'Nenhuma', false, 26),
       ('Auckland Harbor Hotel', 'Hotel elegante em Auckland', 'Hotel', 200.00, 'Nenhuma', true, 27),
       ('Fiji Beach Resort', 'Resort all-inclusive em Fiji', 'Resort', 190.00, 'Nenhuma', false, 28),
       ('Papua New Guinea Paradise', 'Resort de luxo em Papua-Nova Guiné', 'Resort', 180.00, 'Nenhuma', true, 29),
       ('Samoa Island Hotel', 'Hotel de ilha em Samoa', 'Hotel', 170.00, 'Nenhuma', false, 30);

CREATE TABLE TICKETS
(
    id            bigint AUTO_INCREMENT PRIMARY KEY,
    user_id       bigint,
    travel_id     bigint,
    seat_id       bigint,
    Cpf           varchar(11)        NOT NULL,
    name          varchar(100)       NOT NULL,
    birthday      date,
    Qrcode        varchar(30) UNIQUE NOT NULL,
    emission_date date               NOT NULL,
    status        char(1)            NOT NULL,
    usage_date    date
);


INSERT INTO html_templates (name, content)
VALUES ('welcome_email', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-vindo ao TravelEase</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .header {
            text-align: center;
            padding: 10px 0;
        }
        .header img {
            width: 100px;
        }
        .header h1 {
            margin: 0;
            color: #333333;
        }
        .hero {
            text-align: center;
            padding: 20px 0;
        }
        .hero img {
            width: 50%;
            height: auto;
            border-radius: 10px;
        }
        .content {
            padding: 20px;
            color: #555555;
            line-height: 1.6;
        }
        .content h2 {
            color: #333333;
        }
        .promotion {
            text-align: center;
            margin: 20px 0;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 10px;
            border-color: black;
        }
        .promotion h3 {
            color: #d9534f;
        }
        .promotion h2 {
            color: #d9534f;
        }
        .verification-code {
            display: flex;
            justify-content: space-between;
        }
        .footer {
            text-align: center;
            padding: 10px 0;
            color: #999999;
            font-size: 14px;
        }
        .footer a {
            color: #d9534f;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="cid:imagemInline0" alt="TravelEase Logo">
            <h1>Bem-vindo ao TravelEase</h1>
        </div>
        <div class="hero">
            <img src="cid:ImagemInline1" alt="Imagem de Destino">
        </div>
        <div class="content">
            <h2>Olá, Viajante!</h2>
            <p>Estamos entusiasmados em tê-lo conosco. No TravelEase, nossa missão é tornar suas viagens inesquecíveis. Explore destinos incríveis, descubra promoções exclusivas e planeje a viagem dos seus sonhos com facilidade.</p>
            <div class="promotion">
                <h3>Aqui está seu Código de verificação:</h3>
                <h2>1 2 3 4 5 6</h2>
            </div>
            <p>Se precisar de qualquer ajuda, não hesite em nos contatar. Estamos aqui para garantir que sua experiência seja perfeita.</p>
        </div>
        <div class="footer">
            <p>TravelEase, Rua das Viagens, 123, São Paulo - SP</p>
            <p>© 2024 TravelEase. Todos os direitos reservados.</p>
            <p><a href="https://www.travelease.com/unsubscribe">Cancelar inscrição</a></p>
        </div>
    </div>
</body>
</html>
');