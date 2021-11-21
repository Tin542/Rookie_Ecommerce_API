package com.tinnt.AssigmentRookie.config;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import springfox.documentation.builders.PathSelectors;
 import springfox.documentation.builders.RequestHandlerSelectors;
 import springfox.documentation.service.*;
 import springfox.documentation.spi.DocumentationType;
 import springfox.documentation.spi.service.contexts.SecurityContext;
 import springfox.documentation.spring.web.plugins.Docket;
 import springfox.documentation.swagger.web.*;
 import springfox.documentation.swagger2.annotations.EnableSwagger2;

 import java.util.Arrays;
 import java.util.Collections;
 import java.util.List;

 import java.util.Collections;

@Configuration
public class SwaggerConfig {
         public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

         @Bean
         UiConfiguration uiConfig() {
         return UiConfigurationBuilder.builder()
                 .deepLinking(true)
                 .displayOperationId(false)
                 .defaultModelsExpandDepth(1)
                 .defaultModelExpandDepth(1)
                 .defaultModelRendering(ModelRendering.EXAMPLE)
                 .displayRequestDuration(false)
                 .docExpansion(DocExpansion.NONE)
                 .filter(false)
                 .maxDisplayedTags(null)
                 .operationsSorter(OperationsSorter.ALPHA)
                 .showExtensions(false)
                 .tagsSorter(TagsSorter.ALPHA)
                 .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                 .validatorUrl(null)
                 .build();
     }

     private ApiKey apiKey() {
         return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
     }

     private SecurityContext securityContext() {
         return SecurityContext.builder()
                 .securityReferences(defaultAuth())
                 .build();
     }

     List<SecurityReference> defaultAuth() {
         AuthorizationScope authorizationScope
                 = new AuthorizationScope("global", "accessEverything");
         AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
         authorizationScopes[0] = authorizationScope;
         return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
     }
}
