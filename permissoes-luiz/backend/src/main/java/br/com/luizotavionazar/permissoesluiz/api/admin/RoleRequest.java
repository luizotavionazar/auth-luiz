package br.com.luizotavionazar.permissoesluiz.api.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequest(
        @NotBlank @Size(max = 100) String nome,
        @Size(max = 255) String descricao
) {
}
