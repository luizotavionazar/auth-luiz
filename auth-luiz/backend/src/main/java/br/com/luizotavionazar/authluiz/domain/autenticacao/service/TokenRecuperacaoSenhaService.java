package br.com.luizotavionazar.authluiz.domain.autenticacao.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.luizotavionazar.authluiz.domain.autenticacao.entity.TokenRecuperacaoSenha;
import br.com.luizotavionazar.authluiz.domain.autenticacao.repository.TokenRecuperacaoSenhaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenRecuperacaoSenhaService {

    private final TokenRecuperacaoSenhaRepository tokenRecuperacaoSenhaRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void encerrarSeExpirado(TokenRecuperacaoSenha token) {
        if (token.expirado() && !token.usado() && !token.encerrado()) {
            token.setEncerradoEm(LocalDateTime.now());
            tokenRecuperacaoSenhaRepository.saveAndFlush(token);
        }
    }
}