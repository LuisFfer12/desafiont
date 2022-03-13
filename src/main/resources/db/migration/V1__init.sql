CREATE TABLE pauta(
    id bigserial NOT NULL PRIMARY KEY,
    titulo varchar(255) NOT NULL,
    descricao varchar(255) NOT NULL
);

CREATE TABLE associado(
    id bigserial NOT NULL PRIMARY KEY,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    cpf varchar(255) NOT NULL
);

CREATE TABLE sessao(
    id bigserial NOT NULL PRIMARY KEY,
    id_pauta int NOT NULL,
    tempo_sessao int NOT NULL,
    dt_inicio timestamp NOT NULL,
    dt_fim timestamp NOT NULL,
    CONSTRAINT fk_id_pauta
        FOREIGN KEY(id_pauta)
            REFERENCES pauta(id)
);

CREATE TABLE voto(
    id bigserial NOT NULL PRIMARY KEY,
    id_associado int NOT NULL,
    id_sessao int NOT NULL,
    voto int NOT NULL,
    CONSTRAINT fk_id_sessao
        FOREIGN KEY(id_sessao)
            REFERENCES sessao(id),
    CONSTRAINT fk_id_associado
        FOREIGN KEY(id_associado)
            REFERENCES associado(id)
);
