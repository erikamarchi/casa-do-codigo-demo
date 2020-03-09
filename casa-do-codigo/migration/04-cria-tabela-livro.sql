create table livros (
	id BIGINT NOT NULL AUTO_INCREMENT,
	titulo VARCHAR(255) NOT NULL,    
    categoria_id BIGINT NOT NULL,    
    preco DECIMAL(5,2) NOT NULL,
    resumo TEXT NOT NULL,
    autor_id BIGINT NOT NULL, 
    numero_de_paginas INT NOT NULL,
    isbn VARCHAR(255) NOT NULL,  
    primary key (id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    FOREIGN KEY (autor_id) REFERENCES autores(id),
	UNIQUE(titulo)
);