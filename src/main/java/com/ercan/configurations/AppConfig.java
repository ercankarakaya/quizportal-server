package com.ercan.configurations;

import ch.qos.logback.core.util.TimeUtil;
import com.ercan.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

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
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // for json date
        builder.dateFormat(new SimpleDateFormat(TimeUtils.DATEFORMAT.YYYY_MM_DD_HH_MM_SS));
       // builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE); // enables wrapping for root elements
        //builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }


}
