package shakh.supermarketdemo.configurations;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


import static java.util.Collections.singletonList;
import static shakh.supermarketdemo.utils.Swagger.*;
import static shakh.supermarketdemo.utils.Swagger.SECURE_PATH;


@Configuration
 class SwaggerConfig {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).forCodeGeneration(true)
                .securityContexts(singletonList(securityContext()))
                .securitySchemes(singletonList(apiKey())).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(SECURE_PATH)).build()
                .tags(new Tag(API_TAG, "All APIs relatin to this ptojects"));

    }

    private ApiInfo apiInfo(){
        return new ApiInfo(API_TITLE, API_DESCRIPTION,API_VERSION, TERMS_OF_SERVICE,contact(),LICENSE,LICENSE_URL, Collections.emptyList());
    }

    private Contact contact(){
        return new Contact(CONTACT_NAME,CONTACT_URL,CONTACT_EMAIL);
    }

    private ApiKey apiKey(){
        return new ApiKey(SECURITY_REFERENCE,AUTHORIZATION, SecurityScheme.In.HEADER.name());
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(securityReference()).build();
    }

    private List<SecurityReference> securityReference(){
        AuthorizationScope[] authorizationScopes= {new AuthorizationScope(AUTHORIZATION_SCOPE,AUTHORIZATION_DESCRIPTION)};
        return singletonList(new SecurityReference(SECURITY_REFERENCE,authorizationScopes));
    }


}