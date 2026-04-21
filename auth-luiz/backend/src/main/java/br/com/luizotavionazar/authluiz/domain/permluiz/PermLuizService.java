package br.com.luizotavionazar.authluiz.domain.permluiz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class PermLuizService {

    @Value("${perm.luiz.url:}")
    private String permLuizUrl;

    private final RestClient restClient = RestClient.create();

    public boolean isAdmin(String token) {
        if (permLuizUrl == null || permLuizUrl.isBlank()) {
            return false;
        }
        try {
            Map<String, Object> body = restClient.get()
                    .uri(permLuizUrl + "/me/admin")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return Boolean.TRUE.equals(body != null ? body.get("isAdmin") : null);
        } catch (Exception e) {
            return false;
        }
    }
}
