package br.com.example.SpringBootH2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableSwagger2
public class SpringBootH2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootH2Application.class, args);
    }

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.example.SpringBootH2"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(this.info());
    }

    private ApiInfo info() {
        ApiInfo info = new ApiInfo(
                "Player Management API",
                "RestFull API using SpringBoot, H2 and Swagger2",
                "1.0",
                null,
                new Contact("João Vitor Motta", "https://github.com/joaovmotta", "jaoo.motta@gmail.com"),
                null,
                null,
                new ArrayList<VendorExtension>()
        );
        return info;
    }
}
