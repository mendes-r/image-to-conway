package image.to.conway;

import image.to.conway.image.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public Exporter selectedExporter(@Value("${app.exporter}") String qualifier) {
        return (Exporter) context.getBean(qualifier);
    }
}
