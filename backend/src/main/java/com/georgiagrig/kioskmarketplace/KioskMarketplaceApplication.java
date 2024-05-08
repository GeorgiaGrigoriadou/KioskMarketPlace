package com.georgiagrig.kioskmarketplace;

import com.georgiagrig.kioskmarketplace.database.factories.MeasurementUnitFactory;
import com.georgiagrig.kioskmarketplace.database.seeders.ProductionSeeder;
import com.georgiagrig.kioskmarketplace.database.seeders.TestingSeeder;
import com.georgiagrig.kioskmarketplace.database.seeders.entities.MeasurementUnitSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
public class KioskMarketplaceApplication {
	public static void main(String[] args) {
		if (args.length == 0) {
			SpringApplication.run(KioskMarketplaceApplication.class, args);
		} else if (args[0].equalsIgnoreCase("db:seed:testing")) {
			ConfigurableApplicationContext context = new SpringApplicationBuilder(KioskMarketplaceApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
			context.getBean(ProductionSeeder.class).seed();
			context.getBean(TestingSeeder.class).seed();
		} else if (args[0].equalsIgnoreCase("db:seed:production")) {
			ConfigurableApplicationContext context = new SpringApplicationBuilder(KioskMarketplaceApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
			context.getBean(ProductionSeeder.class).seed();
		}
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD").allowedHeaders("*");
			}
		};
	}

}
