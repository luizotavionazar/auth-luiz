# LuizStack

Orquestração Docker da stack completa formada por AuthLuiz e PermissoesLuiz. Com um único `docker compose up`, os dois serviços (e seus bancos de dados) sobem em uma rede compartilhada.

## Serviços orquestrados

| Serviço | Porta | Repositório |
|---------|-------|-------------|
| **authluiz-backend** | `8080` | `github.com/luizotavionazar/auth-luiz` |
| **authluiz-frontend** | `80` | `github.com/luizotavionazar/auth-luiz` |
| **authluiz-db** | interno | PostgreSQL do AuthLuiz |
| **permissoes-backend** | `8081` | `github.com/luizotavionazar/permissoes-luiz` |
| **permissoes-frontend** | `81` | `github.com/luizotavionazar/permissoes-luiz` |
| **permissoes-db** | interno | PostgreSQL do PermissoesLuiz |

Todos os serviços compartilham a rede `luiz-network`. O PermissoesLuiz busca a chave pública do AuthLuiz via JWKS na rede interna — sem expor segredos.

## Pré-requisitos

- Docker com Docker Compose
- Repositórios clonados como irmãos deste diretório:
  ```
  c:\auth-luiz\
  c:\permissoes-luiz\
  c:\luiz-stack\        ← este repo
  ```

## Configuração

```bash
cp .env.example .env
# Edite .env com todas as credenciais dos dois serviços
```

### Variáveis de ambiente (`.env`)

#### Auth-Luiz

| Variável                   | Descrição                                      |
|----------------------------|------------------------------------------------|
| `AUTHLUIZ_DB_USER`         | Usuário do banco do AuthLuiz                   |
| `AUTHLUIZ_DB_PASSWORD`     | Senha do banco do AuthLuiz                     |
| `AUTHLUIZ_SETUP_MASTER_KEY`| Chave mestra do setup do AuthLuiz              |
| `GOOGLE_OAUTH_CLIENT_ID`   | Client ID do Google OAuth                      |
| `JWT_EXPIRATION_MINUTES`   | Expiração dos JWTs (padrão: 120)               |
| `JWT_RSA_PRIVATE_KEY`      | Chave privada RSA em base64 (PKCS#8)           |
| `JWT_RSA_PUBLIC_KEY`       | Chave pública RSA em base64 (X.509)            |

#### PermissoesLuiz

| Variável                     | Descrição                                    |
|------------------------------|----------------------------------------------|
| `PERMISSOES_DB_USER`         | Usuário do banco do PermissoesLuiz           |
| `PERMISSOES_DB_PASSWORD`     | Senha do banco do PermissoesLuiz             |
| `PERMISSOES_SETUP_MASTER_KEY`| Chave mestra do setup do PermissoesLuiz      |

> A `AUTH_LUIZ_JWKS_URI` é injetada automaticamente pelo `compose.yaml` — não precisa estar no `.env`.

> As chaves RSA são compartilhadas entre AuthLuiz e qualquer serviço que precise validar JWTs. Gere-as uma vez com `GerarChavesRSA.java` (no repositório do AuthLuiz).

## Uso

```bash
# Subir a stack completa (builds incluídos)
docker compose up --build

# Subir em background
docker compose up --build -d

# Parar tudo
docker compose down

# Parar e remover volumes (apaga dados dos bancos)
docker compose down -v
```

## Fluxo de funcionamento

1. `authluiz-db` sobe e passa o healthcheck
2. `authluiz-backend` sobe, aplica migrations Flyway, expõe `:8080`
3. `permissoes-db` sobe e passa o healthcheck
4. `permissoes-backend` sobe, aplica migrations Flyway, expõe `:8081`
   - Busca e cacheia a chave pública via `http://authluiz-backend:8080/auth/.well-known/jwks.json`
5. Frontends sobem e servem via nginx em `:80` e `:81`
