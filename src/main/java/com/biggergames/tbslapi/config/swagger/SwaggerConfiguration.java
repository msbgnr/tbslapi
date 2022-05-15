package com.biggergames.tbslapi.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    public static final String MATCH_SCORE_V1_TAG = "matchScoreV1";

    public static final String TEAM_V1_TAG = "teamV1";

    private ApiInfo apiInfo() {
        String url = "";
        return new ApiInfo("Basketball League API", "Basketball League API Case Study for Bigger Games", "1.0.0", url, new Contact("Musab GÜNER", url, "msbgnr@gmail.com"), "", url, Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Collections.singletonList(securityContext()))
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.biggergames.tbslapi.controller"))
                .build()
                .tags(new Tag(TEAM_V1_TAG, " "))
                .tags(new Tag(MATCH_SCORE_V1_TAG, " "));

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().forPaths(PathSelectors.regex("/.*")).build();
    }

}

