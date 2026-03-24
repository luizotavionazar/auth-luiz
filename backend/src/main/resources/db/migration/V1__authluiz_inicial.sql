CREATE TABLE "usuario" (
  "id" BIGSERIAL PRIMARY KEY,
  "nome" VARCHAR(100) NOT NULL,
  "email" VARCHAR(255) NOT NULL,
  "senhaHash" VARCHAR(255) NOT NULL,
  "dataCriacao" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "dataAtualiza" TIMESTAMP NULL,
  CONSTRAINT "usuario_email_uk" UNIQUE ("email")
);

CREATE TABLE "tokenRecuperacaoSenha" (
  "id" BIGSERIAL PRIMARY KEY,
  "idUsuario" BIGINT NOT NULL,
  "tokenHash" VARCHAR(64) NOT NULL,
  "expiraEm" TIMESTAMP NOT NULL,
  "usadoEm" TIMESTAMP NULL,
  "ipSolicitacao" VARCHAR(45) NULL,
  "encerradoEm" TIMESTAMP NULL,
  "dataCriacao" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "dataAtualiza" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT "fk_token_recuperacao_usuario"
    FOREIGN KEY ("idUsuario") REFERENCES "usuario"("id")
);

CREATE INDEX "idx_token_recuperacao_token_hash"
  ON "tokenRecuperacaoSenha" ("tokenHash");

CREATE INDEX "idx_token_recuperacao_usuario"
  ON "tokenRecuperacaoSenha" ("idUsuario");

CREATE TABLE "controleRecuperacaoSenha" (
  "id" BIGSERIAL PRIMARY KEY,
  "ip" VARCHAR(45) NOT NULL UNIQUE,
  "janelaInicio" TIMESTAMP NOT NULL,
  "quantidade" INTEGER NOT NULL,
  "bloqueadoAte" TIMESTAMP NULL,
  "dataCriacao" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "dataAtualiza" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "configuracaoAplicacao" (
  "id" BIGSERIAL PRIMARY KEY,
  "setupConcluido" BOOLEAN NOT NULL DEFAULT FALSE,
  "smtpHost" VARCHAR(255),
  "smtpPort" INTEGER,
  "smtpUsername" VARCHAR(255),
  "smtpPasswordCriptografada" TEXT,
  "smtpAuth" BOOLEAN NOT NULL DEFAULT TRUE,
  "smtpStarttls" BOOLEAN NOT NULL DEFAULT TRUE,
  "mailFrom" VARCHAR(255),
  "frontendBaseUrl" VARCHAR(500),
  "dataCriacao" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "dataAtualiza" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO "configuracaoAplicacao" ("id", "setupConcluido")
VALUES (1, FALSE)
ON CONFLICT ("id") DO NOTHING;
