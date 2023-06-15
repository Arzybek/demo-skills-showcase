package com.demo.showcase.common.sso;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class KeycloakUtils {
    private static KeycloakPrincipal<RefreshableKeycloakSecurityContext> getPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) securityContext.getAuthentication()
                                                                                      .getPrincipal();
    }

    public static String getBearerToken() {
        return "Bearer %s".formatted(getPrincipal().getKeycloakSecurityContext()
                                                   .getTokenString());
    }

}
