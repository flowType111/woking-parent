package com.woke.working.web.config;

import com.woke.working.web.aspect.JurisdictionAspect;
import com.woke.working.web.exception.ExceptionAdapter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class WebUtilConfiguration {

    @Configuration
    public class ValidatorConfiguration {
        @Bean
        public Validator validator() {
            ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                    .configure()
                    .failFast(false)
                    .buildValidatorFactory();
            javax.validation.Validator validator = validatorFactory.getValidator();
            return validator;
        }
    }

    @Configuration
    public class WebAppConfigurer implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // 可添加多个
        }
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public JurisdictionAspect jurisdictionAspect() {
        return new JurisdictionAspect();
    }

    @Bean
    public ExceptionAdapter exceptionAdapter() {
        return new ExceptionAdapter();
    }
}
