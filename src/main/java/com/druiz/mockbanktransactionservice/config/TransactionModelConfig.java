package com.druiz.mockbanktransactionservice.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class TransactionModelConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, byte[]> stringToByteConverter = new AbstractConverter<String, byte[]>() {
            @Override
            protected byte[] convert(String source) {
                if (source == null) {
                    return null;
                }
                return source.getBytes(StandardCharsets.UTF_8);
            }
        };
        modelMapper.addConverter(stringToByteConverter);

        Converter<byte[], String> byteToStringConverter = new AbstractConverter<byte[], String>() {
            @Override
            protected String convert(byte[] source) {
                return  new String(source, StandardCharsets.UTF_8);
            }
        };
        modelMapper.addConverter(byteToStringConverter);
        return modelMapper;
    }
}


