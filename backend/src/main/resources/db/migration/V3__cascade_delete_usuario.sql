-- Recria as FKs com ON DELETE CASCADE para permitir deleção de usuário sem violar integridade referencial.
-- No Postgres não é possível alterar uma constraint existente; é necessário removê-la e recriá-la.

ALTER TABLE "tokenRecuperacaoSenha"
    DROP CONSTRAINT "fk_token_recuperacao_usuario",
    ADD CONSTRAINT "fk_token_recuperacao_usuario"
        FOREIGN KEY ("idUsuario") REFERENCES "usuario"("id") ON DELETE CASCADE;

ALTER TABLE "identidadeExterna"
    DROP CONSTRAINT "fk_identidade_externa_usuario",
    ADD CONSTRAINT "fk_identidade_externa_usuario"
        FOREIGN KEY ("idUsuario") REFERENCES "usuario"("id") ON DELETE CASCADE;
