create table empresas(
                         id bigint not null auto_increment,
                         cnpj varchar(14) not null unique,
                         nome varchar(100) not null,
                         email varchar(100) not null unique,
                        saldo  decimal(7,2) not null,
                        taxa   decimal(3,2) not null,

                         primary key(id)
);

create table clientes(
                         id bigint not null auto_increment,
                         cpf varchar(11) not null unique,
                         nome varchar(100) not null,
                         email varchar(100) not null unique,
                         saldo decimal(7,2) not null,
                         id_empresa bigint not null,

                         primary key(id)
)