package com.cv.mailguntest.application;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
//@Profile("dev")
public class SwaggerConfig {

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "GeoVision API service",
                "REST API of GeoVision service",
                "0.1",
                null,
                new Contact("Taras Halynskyi", "https://geovisionapi.com", "support@geovisipnapi.com"),
                null,
                null, newArrayList());
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("0.1")
                .apiInfo(getApiInfo())
                .pathProvider(new BasePathAwareRelativePathProvider("/api"))
                .select()
                .paths(blockBasicErrorPath())
                .paths(versionPath())
                .build();
    }


    private Predicate<String> blockBasicErrorPath() {
        return Predicates.not(PathSelectors.regex("/error"));
    }

    private Predicate<String> versionPath() {
        return regex("/api/.*");
    }

    class BasePathAwareRelativePathProvider extends AbstractPathProvider {
        private String basePath;

        private BasePathAwareRelativePathProvider(String basePath) {
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
        }
    }

}
