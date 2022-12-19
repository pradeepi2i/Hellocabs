package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.exception.HelloCabsException;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null == authentication || !authentication.isAuthenticated()) {
            throw new HelloCabsException(HelloCabsConstants.USER_NOT_AUTHENTICATED);
        }
        return Optional.ofNullable(((String) authentication.getPrincipal()));
    }

    /*@Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Ramesh");
        // Use below commented code when will use Spring Security.
    }*/
}
