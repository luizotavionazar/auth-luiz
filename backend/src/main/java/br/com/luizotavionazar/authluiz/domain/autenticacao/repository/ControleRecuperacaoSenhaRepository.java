package br.com.luizotavionazar.authluiz.domain.autenticacao.repository;

import br.com.luizotavionazar.authluiz.domain.autenticacao.entity.ControleRecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ControleRecuperacaoSenhaRepository extends JpaRepository<ControleRecuperacaoSenha, Long> {

    Optional<ControleRecuperacaoSenha> findByIp(String ip);
}