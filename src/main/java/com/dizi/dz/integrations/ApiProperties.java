package com.dizi.dz.integrations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "dizi-cloud.api")
@Component
public class ApiProperties {

    private String url;
}
