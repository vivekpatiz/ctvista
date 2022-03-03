package com.danaherdigital.che_hx.schedular;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import com.danaherdigital.che_hx.schedular.config.SchedularJobConfig;

import lombok.extern.log4j.Log4j2;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@Import(SchedularJobConfig.class)

@Log4j2
public class CheHxSchedularApplication {



	public static void main(String[] args) {
		log.info("main started");

		SpringApplication.run(CheHxSchedularApplication.class);
	}
	@Configuration
	public class SpringFoxConfig {
	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	          .select()
	          .apis(RequestHandlerSelectors.any())
	          .paths(PathSelectors.any())
	          .build();
	    }
	}

}
