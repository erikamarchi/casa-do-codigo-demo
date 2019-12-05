create table categorias (
	id BIGINT NOT NULL AUTO_INCREMENT,
        nome VARCHAR(30) NOT NULL,
        primary key (id),
	UNIQUE(nome)
);