package org.hibernate.demojwt.common;

import  org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesExternos {

    @Value("${access.token.secret}")
    public String accessTokenSecret;
    @Value("${access.token.validity.seconds}")
    public String accessTokenValiditySeconds;
}
