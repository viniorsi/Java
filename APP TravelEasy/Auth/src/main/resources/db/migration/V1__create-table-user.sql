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

CREATE TABLE hoteis (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        destino_id BIGINT NOT NULL,
                        nome VARCHAR(100) NOT NULL,
                        localizacao VARCHAR(255) NOT NULL,
                        descricao TEXT,
                        classificacao DECIMAL(3,1),  -- Ex: 4.5 (opcional)
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE quartos (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID do quarto
                         hotel_id BIGINT NOT NULL, -- Relacionamento com a tabela hoteis
                         tipo VARCHAR(20) NOT NULL, -- Tipo do quarto (STANDARD, LUXO, SUITE, etc.)
                         numero VARCHAR(10) NOT NULL, -- Número do quarto (ex: "101A")
                         preco_por_noite DECIMAL(10, 2) NOT NULL, -- Preço por noite
                         capacidade INT NOT NULL, -- Capacidade máxima de hóspedes
                         aceita_pet BOOLEAN NOT NULL DEFAULT FALSE, -- Aceita pets?
                         version BIGINT NOT NULL DEFAULT 0, -- Controle de concorrência (Otimistic Locking)
                         FOREIGN KEY (hotel_id) REFERENCES hoteis(id) -- Chave estrangeira para a tabela hoteis
);

CREATE TABLE quarto_fotos (
                              quarto_id BIGINT NOT NULL, -- Relacionamento com a tabela quartos
                              foto VARCHAR(255) NOT NULL, -- URL da foto
                              FOREIGN KEY (quarto_id) REFERENCES quartos(id) -- Chave estrangeira para a tabela quartos
);


CREATE TABLE reservas (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          quarto_id BIGINT NOT NULL,  -- Relacionamento com o quarto reservado
                          usuario_id BIGINT NOT NULL,  -- ID do usuário (referência externa)
                          check_in DATE NOT NULL,  -- Data de entrada
                          check_out DATE NOT NULL,  -- Data de saída
                          valor_total DECIMAL(10,2) NOT NULL,  -- Valor total da reserva
                          status ENUM('PENDENTE', 'CONFIRMADA', 'CANCELADA') NOT NULL DEFAULT 'PENDENTE',  -- Status da reserva
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Data de criação
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- Data de atualização
                          FOREIGN KEY (quarto_id) REFERENCES quartos(id)  -- FK para o quarto
);

INSERT INTO hoteis (destino_id, nome, localizacao, descricao, classificacao)
VALUES
    (1, 'Hotel Lagos', 'Lagos, Nigéria', 'Luxuoso hotel em Lagos, Nigéria', 4.5),
    (2, 'Hotel Cairo', 'Cairo, Egito', 'Hotel com vista para as pirâmides no Cairo, Egito', 4.7),
    (3, 'Cape Town Lodge', 'Cidade do Cabo, África do Sul', 'Aconchegante lodge na Cidade do Cabo', 4.2),
    (4, 'Safari Hotel', 'Parque Nacional do Quênia', 'Hotel próximo ao Parque Nacional do Quênia', 4.4),
    (5, 'Accra Beach Resort', 'Accra, Gana', 'Resort de luxo na praia de Accra, Gana', 4.6);

INSERT INTO hoteis (destino_id, nome, localizacao, descricao, classificacao)
VALUES
    (6, 'New York Hotel', 'Nova York, EUA', 'Hotel de luxo em Nova York, EUA', 4.8),
    (7, 'Toronto Inn', 'Toronto, Canadá', 'Hotel confortável em Toronto, Canadá', 4.3),
    (8, 'Cancun Resort', 'Cancun, México', 'Resort all-inclusive em Cancun, México', 4.7),
    (9, 'Havana Club', 'Havana, Cuba', 'Hotel histórico em Havana, Cuba', 4.5),
    (10, 'Punta Cana Resort', 'Punta Cana, República Dominicana', 'Resort de luxo em Punta Cana', 4.6);

INSERT INTO hoteis (destino_id, nome, localizacao, descricao, classificacao)
VALUES
    (11, 'Rio Palace', 'Copacabana, Brasil', 'Hotel luxuoso em Copacabana', 4.7),
    (12, 'Buenos Aires Plaza', 'Buenos Aires, Argentina', 'Hotel elegante em Buenos Aires', 4.5),
    (13, 'Bogota Grand Hotel', 'Bogotá, Colômbia', 'Hotel central em Bogotá', 4.3),
    (14, 'Santiago Resort', 'Santiago, Chile', 'Resort all-inclusive em Santiago', 4.4),
    (15, 'Lima Beach Hotel', 'Lima, Peru', 'Hotel de praia em Lima', 4.2);

INSERT INTO hoteis (destino_id, nome, localizacao, descricao, classificacao)
VALUES
    (16, 'Beijing Grand Hotel', 'Pequim, China', 'Hotel luxuoso em Pequim', 4.7),
    (17, 'Taj Mahal Palace', 'Mumbai, Índia', 'Hotel histórico em Mumbai', 4.8),
    (18, 'Tokyo Tower Hotel', 'Tóquio, Japão', 'Hotel moderno em Tóquio', 4.6),
    (19, 'Seoul Plaza', 'Seul, Coreia do Sul', 'Hotel elegante em Seul', 4.5),
    (20, 'Bali Beach Resort', 'Bali, Indonésia', 'Resort de luxo em Bali', 4.7);

INSERT INTO hoteis (destino_id, nome, localizacao, descricao, classificacao)
VALUES
    (21, 'Berlin Grand Hotel', 'Berlim, Alemanha', 'Hotel luxuoso em Berlim', 4.6),
    (22, 'Paris Eiffel Hotel', 'Paris, França', 'Hotel elegante em Paris', 4.8),
    (23, 'London Royal Palace', 'Londres, Reino Unido', 'Hotel clássico em Londres', 4.7),
    (24, 'Rome Colosseum Hotel', 'Roma, Itália', 'Hotel histórico em Roma', 4.6),
    (25, 'Madrid Central Hotel', 'Madri, Espanha', 'Hotel central em Madri', 4.5);

INSERT INTO hoteis (destino_id, nome, localizacao, descricao, classificacao)
VALUES
    (26, 'Sydney Opera Hotel', 'Sydney, Austrália', 'Hotel luxuoso em Sydney', 4.6),
    (27, 'Auckland Harbor Hotel', 'Auckland, Nova Zelândia', 'Hotel elegante em Auckland', 4.5),
    (28, 'Fiji Beach Resort', 'Fiji', 'Resort all-inclusive em Fiji', 4.7),
    (29, 'Papua New Guinea Paradise', 'Papua-Nova Guiné', 'Resort de luxo em Papua-Nova Guiné', 4.4),
    (30, 'Samoa Island Hotel', 'Samoa', 'Hotel de ilha em Samoa', 4.3);

INSERT INTO quartos (hotel_id, tipo, numero, preco_por_noite, capacidade, aceita_pet) VALUES
-- Quartos para Hotel Lagos (ID: 1)
(1, UPPER('STANDARD'), '101', 150.00, 2, TRUE),
(1, UPPER('STANDARD'), '102', 150.00, 2, TRUE),
(1, UPPER('LUXO'), '201', 200.00, 3, FALSE),
(1, UPPER('LUXO'), '202', 200.00, 3, FALSE),
(1, UPPER('SUITE'), '301', 300.00, 4, TRUE),
(1, UPPER('SUITE'), '302', 300.00, 4, TRUE),
(1, UPPER('PRESIDENCIAL'), '401', 500.00, 5, FALSE),
(1, UPPER('PRESIDENCIAL'), '402', 500.00, 5, FALSE),
(1, UPPER('FAMILIA'), '501', 250.00, 4, TRUE),
(1, UPPER('FAMILIA'), '502', 250.00, 4, TRUE),

-- Quartos para Hotel Cairo (ID: 2)
(2, UPPER('STANDARD'), '101', 180.00, 2, FALSE),
(2, UPPER('STANDARD'), '102', 180.00, 2, FALSE),
(2, UPPER('LUXO'), '201', 250.00, 3, TRUE),
(2, UPPER('LUXO'), '202', 250.00, 3, TRUE),
(2, UPPER('SUITE'), '301', 350.00, 4, FALSE),
(2, UPPER('SUITE'), '302', 350.00, 4, FALSE),
(2, UPPER('PRESIDENCIAL'), '401', 600.00, 5, TRUE),
(2, UPPER('PRESIDENCIAL'), '402', 600.00, 5, TRUE),
(2, UPPER('FAMILIA'), '501', 270.00, 4, FALSE),
(2, UPPER('FAMILIA'), '502', 270.00, 4, FALSE),

-- Quartos para Cape Town Lodge (ID: 3)
(3, UPPER('STANDARD'), '101', 160.00, 2, TRUE),
(3, UPPER('STANDARD'), '102', 160.00, 2, TRUE),
(3, UPPER('LUXO'), '201', 210.00, 3, FALSE),
(3, UPPER('LUXO'), '202', 210.00, 3, FALSE),
(3, UPPER('SUITE'), '301', 310.00, 4, TRUE),
(3, UPPER('SUITE'), '302', 310.00, 4, TRUE),
(3, UPPER('PRESIDENCIAL'), '401', 550.00, 5, FALSE),
(3, UPPER('PRESIDENCIAL'), '402', 550.00, 5, FALSE),
(3, UPPER('FAMILIA'), '501', 230.00, 4, TRUE),
(3, UPPER('FAMILIA'), '502', 230.00, 4, TRUE),

-- Quartos para Safari Hotel (ID: 4)
(4, UPPER('STANDARD'), '101', 170.00, 2, FALSE),
(4, UPPER('STANDARD'), '102', 170.00, 2, FALSE),
(4, UPPER('LUXO'), '201', 230.00, 3, TRUE),
(4, UPPER('LUXO'), '202', 230.00, 3, TRUE),
(4, UPPER('SUITE'), '301', 330.00, 4, FALSE),
(4, UPPER('SUITE'), '302', 330.00, 4, FALSE),
(4, UPPER('PRESIDENCIAL'), '401', 570.00, 5, TRUE),
(4, UPPER('PRESIDENCIAL'), '402', 570.00, 5, TRUE),
(4, UPPER('FAMILIA'), '501', 240.00, 4, FALSE),
(4, UPPER('FAMILIA'), '502', 240.00, 4, FALSE),

-- Quartos para Accra Beach Resort (ID: 5)
(5, UPPER('STANDARD'), '101', 190.00, 2, TRUE),
(5, UPPER('STANDARD'), '102', 190.00, 2, TRUE),
(5, UPPER('LUXO'), '201', 260.00, 3, FALSE),
(5, UPPER('LUXO'), '202', 260.00, 3, FALSE),
(5, UPPER('SUITE'), '301', 360.00, 4, TRUE),
(5, UPPER('SUITE'), '302', 360.00, 4, TRUE),
(5, UPPER('PRESIDENCIAL'), '401', 620.00, 5, FALSE),
(5, UPPER('PRESIDENCIAL'), '402', 620.00, 5, FALSE),
(5, UPPER('FAMILIA'), '501', 280.00, 4, TRUE),
(5, UPPER('FAMILIA'), '502', 280.00, 4, TRUE);




INSERT INTO quarto_fotos (quarto_id, foto) VALUES
-- Fotos para os quartos do Hotel Lagos (ID: 1)
(1, 'https://exemplo.com/hotel-lagos/101/foto1.jpg'),
(1, 'https://exemplo.com/hotel-lagos/101/foto2.jpg'),
(2, 'https://exemplo.com/hotel-lagos/102/foto1.jpg'),
(2, 'https://exemplo.com/hotel-lagos/102/foto2.jpg'),
(3, 'https://exemplo.com/hotel-lagos/201/foto1.jpg'),
(3, 'https://exemplo.com/hotel-lagos/201/foto2.jpg'),
(4, 'https://exemplo.com/hotel-lagos/202/foto1.jpg'),
(4, 'https://exemplo.com/hotel-lagos/202/foto2.jpg'),
(5, 'https://exemplo.com/hotel-lagos/301/foto1.jpg'),

-- Fotos para os quartos do Hotel Cairo (ID: 2)
(11, 'https://exemplo.com/hotel-cairo/101/foto1.jpg'),
(11, 'https://exemplo.com/hotel-cairo/101/foto2.jpg'),
(12, 'https://exemplo.com/hotel-cairo/102/foto1.jpg'),
(12, 'https://exemplo.com/hotel-cairo/102/foto2.jpg'),
(13, 'https://exemplo.com/hotel-cairo/201/foto1.jpg'),
(13, 'https://exemplo.com/hotel-cairo/201/foto2.jpg'),
(14, 'https://exemplo.com/hotel-cairo/202/foto1.jpg'),

-- Fotos para os quartos do Cape Town Lodge (ID: 3)
(21, 'https://exemplo.com/cape-town-lodge/101/foto1.jpg'),
(21, 'https://exemplo.com/cape-town-lodge/101/foto2.jpg'),
(22, 'https://exemplo.com/cape-town-lodge/102/foto1.jpg'),
(22, 'https://exemplo.com/cape-town-lodge/102/foto2.jpg'),
(23, 'https://exemplo.com/cape-town-lodge/201/foto1.jpg'),
(23, 'https://exemplo.com/cape-town-lodge/201/foto2.jpg'),
(24, 'https://exemplo.com/cape-town-lodge/202/foto1.jpg'),
(24, 'https://exemplo.com/cape-town-lodge/202/foto2.jpg');


