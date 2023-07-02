package com.demo.showcase.common.sso;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

public class KeycloakUtils {

    public static UUID getUserId() {
        KeycloakAuthenticationToken authentication =
            (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        String userIdByToken = "";
        KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
        IDToken token = kPrincipal.getKeycloakSecurityContext().getToken();
        userIdByToken = token.getSubject();
        UUID userId = UUID.fromString(userIdByToken);
        return userId;
    }

    private static KeycloakPrincipal<RefreshableKeycloakSecurityContext> getPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) securityContext.getAuthentication()
                                                                                      .getPrincipal();
    }

    public static String getBearerToken() {
        return "Bearer " + getPrincipal().getKeycloakSecurityContext()
                                         .getTokenString();
    }

    public static Role getRole() {
        AccessToken.Access access = getPrincipal().getKeycloakSecurityContext()
                                                  .getToken()
                                                  .getRealmAccess();
        Set<String> roles = access.getRoles();
        return Role.valueOf(roles.stream()
                                 .filter(x -> Role.names()
                                                  .contains(x))
                                 .findFirst()
                                 .get());
    }

}
