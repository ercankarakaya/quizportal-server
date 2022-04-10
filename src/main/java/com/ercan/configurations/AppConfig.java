package com.ercan.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@EnableJpaRepositories("com.ercan.repositories")
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    /**
     * For Json Root Name.
     * Alternative -> spring.jackson.serialization.wrap-root-value=true  (application.properties)
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE); // enables wrapping for root elements
        //builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }


}
