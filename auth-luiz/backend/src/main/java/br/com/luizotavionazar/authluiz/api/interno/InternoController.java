package br.com.luizotavionazar.authluiz.api.interno;

import br.com.luizotavionazar.authluiz.domain.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/auth/interno")
@RequiredArgsConstructor
public class InternoController {

    private final UsuarioRepository usuarioRepository;

    @Value("${auth.service.key}")
    private String serviceKey;

    @GetMapping("/usuarios")
    List<UsuarioInternoResponse> listarUsuarios(@RequestHeader("X-Service-Key") String chave) {
        if (!serviceKey.equals(chave)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Chave de serviço inválida!");
        }
        return usuarioRepository.findAll().stream()
                .map(UsuarioInternoResponse::de)
                .toList();
    }
}
