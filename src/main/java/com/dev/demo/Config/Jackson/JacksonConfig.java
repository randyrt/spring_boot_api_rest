package com.dev.demo.Config.Jackson;

import tools.jackson.databind.MapperFeature;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    JsonMapperBuilderCustomizer strictJsonTypes() {
        return builder -> builder.disable(MapperFeature.ALLOW_COERCION_OF_SCALARS);
    }
}
