package com.Bosonit.block5profiles;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class Variables {

    @Value("${app.name}")
    private String activeProfile;

    @Value("${bd.url}")
    private String dbUrl;

}
