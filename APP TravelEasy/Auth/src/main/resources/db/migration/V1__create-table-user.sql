CREATE TABLE users
(
    id                 bigint PRIMARY KEY AUTO_INCREMENT,
    Cpf                varchar(11) UNIQUE NOT NULL,
    name               varchar(100)       NOT NULL,
    tel                varchar(9),
    ddd                varchar(2),
    email              varchar(30)        NOT NULL,
    password           varchar(255),
    birthday           date,
    creation_date      date,
    zip_code           varchar(8),
    address            varchar(150),
    number             varchar(4),
    additional_info    varchar(8),
    neigborhood        varchar(100),
    state              varchar(2),
    city               varchar(30),
    status             char(1),
    points             INT
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
            <img src="cid:imagemInline1" alt="Imagem de Destino">
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

