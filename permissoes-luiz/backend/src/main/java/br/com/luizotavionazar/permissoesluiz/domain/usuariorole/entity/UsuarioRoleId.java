package br.com.luizotavionazar.permissoesluiz.domain.usuariorole.entity;

import java.io.Serializable;

public record UsuarioRoleId(Long idUsuario, Long idRole) implements Serializable {
}
