package com.disqo.calendly_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                //.globalOperationParameters(globalParameterList())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

//    private List<Parameter> globalParameterList() {
//        final Parameter build = new ParameterBuilder()
//                .name("Authorization") // name of the header
//                .modelRef(new ModelRef("string")) // data-type of the header
//                .required(false) // required/optional
//                .parameterType("header") // for query-param, this value can be 'query'
//                .description("Bearer {JWT Token}")
//                .build();
//        return Collections.singletonList(build);
//    }
}
