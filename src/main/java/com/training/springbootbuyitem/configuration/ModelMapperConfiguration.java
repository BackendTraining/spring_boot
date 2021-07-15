package com.training.springbootbuyitem.configuration;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Double, BigDecimal> converter = mappingContext -> BigDecimal
            .valueOf(Optional.ofNullable(mappingContext.getSource())
                .orElse(0D));

        modelMapper.createTypeMap(Double.class, BigDecimal.class).setConverter(converter);
        return modelMapper;
    }

}
