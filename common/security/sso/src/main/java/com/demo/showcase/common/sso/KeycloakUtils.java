package com.demo.showcase.common.sso;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

public class KeycloakUtils {

    private static KeycloakPrincipal<RefreshableKeycloakSecurityContext> getPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) securityContext.getAuthentication()
                                                                                      .getPrincipal();
    }

    public static String getBearerToken() {
        return "Bearer " + getPrincipal().getKeycloakSecurityContext()
                                         .getTokenString();
    }

    public static Role getRole(){
        AccessToken.Access access = getPrincipal().getKeycloakSecurityContext().getToken()
                                                  .getRealmAccess();
        Set<String> roles = access.getRoles();
        return Role.valueOf(roles.stream().filter(x -> Role.names().contains(x)).findFirst().get());
    }

}
