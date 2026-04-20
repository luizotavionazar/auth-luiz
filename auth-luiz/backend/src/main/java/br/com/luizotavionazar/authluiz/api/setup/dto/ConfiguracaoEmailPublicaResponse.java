package br.com.luizotavionazar.authluiz.api.setup.dto;

public record ConfiguracaoEmailPublicaResponse(
        String smtpHost,
        Integer smtpPort,
        String smtpUsername,
        String mailFrom,
        String frontendBaseUrl,
        boolean smtpStarttls,
        boolean setupConcluido
) {
}