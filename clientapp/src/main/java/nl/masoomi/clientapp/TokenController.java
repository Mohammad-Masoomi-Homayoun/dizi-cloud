package nl.masoomi.clientapp;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @GetMapping("/token")
    public String getToken(@RegisteredOAuth2AuthorizedClient("dizi-cloud-app") OAuth2AuthorizedClient client) {
        if (client == null || client.getAccessToken() == null) {
            return "No token available yet";
        }
        return client.getAccessToken().getTokenValue();
    }
}
