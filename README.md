# AuthLuiz

API reutilizável de autenticação com Spring Boot e frontend Vue.

## O que ficou neste projeto
- cadastro de conta
- login com JWT
- endpoint autenticado `/auth/me`
- recuperação e redefinição de senha
- setup inicial para configuração de envio de e-mail

## O que foi removido
- módulos de pessoa
- módulos de endereço e cidade
- papéis fixos do Cola Ai
- qualquer referência funcional ao domínio do Cola Ai

## Como rodar
1. copie `backend/.env.example` para `backend/.env`
2. ajuste os valores de banco, JWT e chave mestra
3. suba o banco com `docker compose -f compose-dev.yaml up -d`
4. inicie o backend
5. inicie o frontend
6. acesse `/setup` para concluir a configuração inicial

## Próximo passo sugerido
O projeto já está limpo para evoluir autorização customizável por regras, sem papéis fixos.
