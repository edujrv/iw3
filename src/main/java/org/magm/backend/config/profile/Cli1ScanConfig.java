package org.magm.backend.config.profile;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//La clase Cli1ScanConfig en el paquete org.magm.backend.config.profile es una configuración específica para el perfil cli1 de la aplicación Spring Boot.
//n resumen, la clase Cli1ScanConfig se encarga de configurar la exploración de repositorios JPA y entidades JPA para el perfil de Spring "cli1", y excluye algunos paquetes específicos del escaneo.
@Configuration
//Repositorios
@EnableJpaRepositories(basePackages = "org.magm.backend", 
excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.magm\\.backend\\.integration\\.cli2\\..*" )
		//Se pueden definir más filtros de exclusión
		//,@ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.magm\\.backend\\.integration\\.cliN\\..*" )
})


//Entidades
@EntityScan(basePackages = { 
		"org.magm.backend.model", 
		"org.magm.backend.auth", 
		"org.magm.backend.integration.cli1.model" 
},
basePackageClasses = {
	// Se pueden cargar entidades particulares que no estén en los paquetes base
	//org.magm.backend.integration.cliN.model.Entidad1.class, 
	//org.magm.backend.integration.cliN.model.Entidad2.class
})
//Las dos líneas que siguen son equivalentes, se puede usar cualquiera
@ConditionalOnExpression(value = "'${spring.profiles.active:-}'=='cli1'")
@Profile("cli1")
public class Cli1ScanConfig {

}

