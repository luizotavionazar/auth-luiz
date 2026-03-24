package br.com.luizotavionazar.authluiz.api.setup.dto;

public record StatusSetupResponse(
        boolean bootstrapOk,
        boolean setupConcluido
) {
}