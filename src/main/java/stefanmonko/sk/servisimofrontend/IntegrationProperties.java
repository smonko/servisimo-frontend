package stefanmonko.sk.servisimofrontend;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("integration")
@RefreshScope
public class IntegrationProperties {

    @Getter @Setter private String viewer;
    @Getter @Setter private String putter;
    @Getter @Setter private String catalog;

}
