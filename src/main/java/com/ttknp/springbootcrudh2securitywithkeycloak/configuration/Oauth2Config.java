package com.ttknp.springbootcrudh2securitywithkeycloak.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class Oauth2Config {


    private String clientId;

    private String clientSecret;

    private String pathRedirect;

    // work for import like key classpath as spring.config.import=classpath:<Path> another case won work
    public Oauth2Config(@Value("${CLIENT_ID}") String clientId,
                        @Value("${CLIENT_SECRET_ID}") String clientSecret,
                        @Value("${PATH_REDIRECT}") String pathRedirect) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.pathRedirect = pathRedirect;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.keyCloakClientRegistration());
    }

    /*
        can use Spring Boot Auto-configuration set all detail on application.yml, application.properties
    */
    private ClientRegistration keyCloakClientRegistration() {
        return ClientRegistration
                .withRegistrationId("keycloak")
                // secret token
                .clientId(clientId) // Using Client Id
                .clientSecret(clientSecret) // Using Client Secret
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(pathRedirect) // *** call back after login success
                .scope("profile","openid","roles")
                // *** all configs see at => localhost:8888/realms/master/.well-known/openid-configuration
                .issuerUri("http://localhost:8888/realms/master")
                .authorizationUri("http://localhost:8888/realms/master/protocol/openid-connect/auth")
                .tokenUri("http://localhost:8888/realms/master/protocol/openid-connect/token")
                .jwkSetUri("http://localhost:8888/realms/master/protocol/openid-connect/certs")
                .build();
    }
}
