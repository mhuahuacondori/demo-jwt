package org.hibernate.demojwt.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "access.token")
public class PropertiesExternos {

    private String secret;
    private Validity validity;

    @Getter
    @Setter
    public static class Validity {

        private String seconds;

    }
}
