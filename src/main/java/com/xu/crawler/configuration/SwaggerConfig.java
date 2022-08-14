package com.xu.crawler.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("sign-auth")
                .apiInfo(webApiInfo())
                .enable(true)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xu.crawler.controller"))  // The path to scan
                .paths(PathSelectors.any())  //authority
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("sign authorized interfaces-swagger")
                .description("authorized interfaces（openid userId）")
                .contact(new Contact("Aaron Xu", "http://baidu.com", "feigong1234@163.com"))
                .version("1.0.0") // document version
                .build();
    }
}
