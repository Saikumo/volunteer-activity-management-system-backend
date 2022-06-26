package org.saikumo.vams.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
public class AccessToken {
    private String loginAccount;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
    private Date expirationTime;
}
