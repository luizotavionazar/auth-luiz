package br.com.luizotavionazar.authluiz.domain.configuracao.repository;

import br.com.luizotavionazar.authluiz.domain.configuracao.entity.ConfiguracaoAplicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoAplicacaoRepository extends JpaRepository<ConfiguracaoAplicacao, Long> {
}