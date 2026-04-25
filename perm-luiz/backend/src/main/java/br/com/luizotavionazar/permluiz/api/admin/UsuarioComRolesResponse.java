package br.com.luizotavionazar.permluiz.api.admin;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioComRolesResponse(
        Long idUsuario,
        String nome,
        String email,
        LocalDateTime dataCriacao,
        List<RoleResponse> roles
) {}
