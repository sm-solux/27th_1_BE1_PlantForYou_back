package com.be1.plant4you.common.config;

import org.springframework.context.annotation.Configuration;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private static final String API_NAME= "PlantForYou API";
    private static final String API_VERSION= "0.0.1";
    private static final String API_DESCRIPTION= "PlantForYou API 명세서";

    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.OAS_30)
//                .additionalModels(typeResolver.resolve(ExResponse.class))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_NAME)
                .description("<h2>" +API_DESCRIPTION+ "</h2>")
                .version(API_VERSION)
                .build();
    }
}