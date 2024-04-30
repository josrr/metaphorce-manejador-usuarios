package com.jmarr.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestHandler;
import org.springframework.security.web.server.csrf.XorServerCsrfTokenRequestAttributeHandler;

import org.springframework.web.server.WebFilter;
import org.springframework.security.web.server.csrf.CsrfToken;
//import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
//import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
//import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {
    @Autowired
    private ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Bean
    SecurityWebFilterChain SecurityWebFilterChain(ServerHttpSecurity http) {
        XorServerCsrfTokenRequestAttributeHandler delegate = new XorServerCsrfTokenRequestAttributeHandler();
	ServerCsrfTokenRequestHandler requestHandler = delegate::handle;
        http.authorizeExchange((authorize) -> authorize.anyExchange().authenticated())
            .oauth2Login(Customizer.withDefaults())
            .logout((logout) -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()))
            .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                              .csrfTokenRequestHandler(requestHandler));
            return http.build();
    }

    @Bean
    WebFilter csrfCookieWebFilter() {
        return (exchange, chain) -> {
            Mono<CsrfToken> csrfToken = exchange.getAttributeOrDefault(CsrfToken.class.getName(), Mono.empty());
            return csrfToken.doOnSuccess(token -> {
                    /* Ensures the token is subscribed to. */
        	}).then(chain.filter(exchange));
        };
    }

    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
            new OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository);
        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/login");
        return oidcLogoutSuccessHandler;
    }
}
