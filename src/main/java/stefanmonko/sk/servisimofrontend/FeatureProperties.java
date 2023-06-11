package stefanmonko.sk.servisimofrontend;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "features")
@RefreshScope 
public class FeatureProperties {
    
    @Getter @Setter private boolean canaryflag;
    @Getter @Setter private boolean reportticket;
    @Getter @Setter private boolean advancefilter;


}
