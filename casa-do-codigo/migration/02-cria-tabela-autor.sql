create table autores (
	id BIGINT NOT NULL AUTO_INCREMENT,
        nome VARCHAR(100) NOT NULL,
        resumo TEXT NOT NULL,
        primary key (id),
	UNIQUE(nome)
);