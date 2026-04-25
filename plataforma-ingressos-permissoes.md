# Permissões e Roles — Plataforma de Ingressos

## Permissões

Cadastrar em `/admin/permissoes` no PermLuiz (`recurso` + `acao`).

| recurso    | acao        | O que representa                             |
|------------|-------------|----------------------------------------------|
| `ingresso` | `ver`       | Ver detalhes de um ingresso                  |
| `ingresso` | `comprar`   | Comprar ingresso para um evento              |
| `ingresso` | `criar`     | Criar tipos/lotes de ingresso para um evento |
| `ingresso` | `editar`    | Editar tipos/lotes de ingresso               |
| `ingresso` | `cancelar`  | Cancelar/solicitar reembolso                 |
| `ingresso` | `validar`   | Escanear ingresso na entrada do evento       |
| `evento`   | `ver`       | Ver detalhes de eventos publicados           |
| `evento`   | `criar`     | Criar um novo evento                         |
| `evento`   | `editar`    | Editar informações do evento                 |
| `evento`   | `publicar`  | Publicar ou despublicar um evento            |
| `evento`   | `cancelar`  | Cancelar um evento                           |
| `evento`   | `relatorio` | Ver relatório de vendas do evento            |

## Roles

Cadastrar em `/admin/roles` no PermLuiz e associar as permissões acima.

### Comprador
Usuário padrão — atribuído automaticamente no primeiro login na plataforma.

- `ingresso:ver`
- `ingresso:comprar`
- `ingresso:cancelar`
- `evento:ver`

### Organizador
Quem cria e gerencia eventos e ingressos.

- `ingresso:ver`
- `ingresso:comprar`
- `ingresso:cancelar`
- `ingresso:criar`
- `ingresso:editar`
- `ingresso:validar`
- `evento:ver`
- `evento:criar`
- `evento:editar`
- `evento:publicar`
- `evento:cancelar`
- `evento:relatorio`

### Fiscal
Staff de entrada — só valida ingressos, não compra nem cria eventos.

- `ingresso:ver`
- `ingresso:validar`
- `evento:ver`
