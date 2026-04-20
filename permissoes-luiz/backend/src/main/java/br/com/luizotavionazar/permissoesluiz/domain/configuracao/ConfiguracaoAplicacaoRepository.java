package br.com.luizotavionazar.permissoesluiz.domain.configuracao;

import br.com.luizotavionazar.permissoesluiz.domain.configuracao.entity.ConfiguracaoAplicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoAplicacaoRepository extends JpaRepository<ConfiguracaoAplicacao, Long> {
}
