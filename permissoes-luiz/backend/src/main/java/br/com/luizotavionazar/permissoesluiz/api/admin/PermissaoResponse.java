package br.com.luizotavionazar.permissoesluiz.api.admin;

import br.com.luizotavionazar.permissoesluiz.domain.permissao.entity.Permissao;

public record PermissaoResponse(Long id, String recurso, String acao, String descricao) {

    public static PermissaoResponse de(Permissao p) {
        return new PermissaoResponse(p.getId(), p.getRecurso(), p.getAcao(), p.getDescricao());
    }
}
