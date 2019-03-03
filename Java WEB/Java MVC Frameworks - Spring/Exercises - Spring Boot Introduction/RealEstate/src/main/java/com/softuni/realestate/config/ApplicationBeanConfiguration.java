package com.softuni.realestate.config;

import com.softuni.realestate.util.htmlReader.HtmlFileReader;
import com.softuni.realestate.util.htmlReader.HtmlFileReaderImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    Validator validator() {
        return Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Bean
    HtmlFileReader htmlFileReader() {
        return new HtmlFileReaderImpl();
    }

}
