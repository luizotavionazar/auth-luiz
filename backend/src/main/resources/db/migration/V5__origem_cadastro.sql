-- Armazena qual provider externo originou o cadastro da conta (nullable).
-- NULL indica conta criada por e-mail/senha; valor preenchido indica OAuth (ex: GOOGLE, APPLE, GITHUB).
-- Contas criadas por um provider não podem desvincular esse mesmo provider.
ALTER TABLE "usuario"
    ADD COLUMN "providerOrigem" VARCHAR(50) NULL;

-- Marca contas existentes criadas via Google:
-- heurística: sem senha local e com identidade Google vinculada.
UPDATE "usuario" u
SET "providerOrigem" = 'GOOGLE'
WHERE u."senhaHash" IS NULL
  AND EXISTS (
      SELECT 1 FROM "identidadeExterna" ie
      WHERE ie."idUsuario" = u."id"
        AND ie."provider" = 'GOOGLE'
  );
