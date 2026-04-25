package br.com.luizotavionazar.permluiz.infra;

import java.time.LocalDateTime;

public record UsuarioAuthResponse(
        Long id,
        String nome,
        String email,
        LocalDateTime dataCriacao
) {}
