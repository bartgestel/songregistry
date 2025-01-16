package com.bartvangestel.songregistrybackend.logic;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthenticationEventListener implements ApplicationListener<org.springframework.security.authentication.event.AbstractAuthenticationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEventListener.class);

    @Override
    public void onApplicationEvent(org.springframework.security.authentication.event.AbstractAuthenticationEvent event) {
        Authentication authentication = event.getAuthentication();

        if (event instanceof AuthenticationSuccessEvent) {
            // Log successful authentication
            logger.info("Authentication successful for user: {}", authentication.getName());
        } else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            // Log failed authentication
            logger.warn("Authentication failed for user: {}", authentication.getName());
        }
    }
}
