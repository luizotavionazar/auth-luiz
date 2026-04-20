package br.com.luizotavionazar.permissoesluiz.api.setup;

import jakarta.validation.constraints.NotNull;

public record SetupRequest(@NotNull Long idUsuario) {
}
