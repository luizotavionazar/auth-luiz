package br.com.luizotavionazar.authluiz.domain.usuario.service;

import br.com.luizotavionazar.authluiz.domain.autenticacao.service.PoliticaSenhaService;
import br.com.luizotavionazar.authluiz.domain.usuario.entity.Usuario;
import br.com.luizotavionazar.authluiz.domain.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PoliticaSenhaService politicaSenhaService;

    @Transactional
    public Usuario cadastrar(String nome, String email, String senha) {
        String nomeNormalizado = nome.trim();
        String emailNormalizado = email.trim().toLowerCase();

        if (usuarioRepository.existsByEmail(emailNormalizado)) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        politicaSenhaService.validar(senha);

        Usuario usuario = Usuario.builder()
                .nome(nomeNormalizado)
                .email(emailNormalizado)
                .senhaHash(passwordEncoder.encode(senha))
                .build();

        return usuarioRepository.save(usuario);
    }
}
