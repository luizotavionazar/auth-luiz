package br.com.luizotavionazar.authluiz.api.interno;

import br.com.luizotavionazar.authluiz.domain.usuario.entity.Usuario;

import java.time.LocalDateTime;

public record UsuarioInternoResponse(
        Long id,
        String nome,
        String email,
        LocalDateTime dataCriacao
) {
    public static UsuarioInternoResponse de(Usuario u) {
        return new UsuarioInternoResponse(
                u.getId().longValue(),
                u.getNome(),
                u.getEmail(),
                u.getDataCriacao()
        );
    }
}
