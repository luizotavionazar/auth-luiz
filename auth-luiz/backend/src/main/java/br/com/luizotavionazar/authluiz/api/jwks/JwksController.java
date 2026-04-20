package br.com.luizotavionazar.authluiz.api.jwks;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@RequestMapping("/auth/.well-known")
@RequiredArgsConstructor
public class JwksController {

    private final RSAPublicKey rsaPublicKey;

    @GetMapping("/jwks.json")
    Map<String, Object> jwks() {
        RSAKey rsaKey = new RSAKey.Builder(rsaPublicKey)
                .keyID("authluiz-key")
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .build();
        return new JWKSet(rsaKey).toJSONObject();
    }
}
