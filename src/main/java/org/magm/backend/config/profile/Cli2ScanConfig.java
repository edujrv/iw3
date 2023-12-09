package org.magm.backend.config.profile;
//La clase Cli2ScanConfig en el paquete org.magm.backend.config.profile es otra configuración específica para el perfil cli2 de la aplicación Spring Boot.
//En resumen, la clase Cli2ScanConfig se encarga de configurar la exploración de repositorios JPA y entidades JPA para el perfil de Spring "cli2", y excluye algunos paquetes específicos del escaneo, en este caso, los relacionados con cli1.
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.magm.backend", 
excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.magm\\.backend\\.integration\\.cli1\\..*" )
})


//Entidades
@EntityScan(basePackages = { 
		"org.magm.backend.model", 
		"org.magm.backend.auth", 
		"org.magm.backend.integration.cli2.model" 
})

@Profile("cli2")
public class Cli2ScanConfig {

}

