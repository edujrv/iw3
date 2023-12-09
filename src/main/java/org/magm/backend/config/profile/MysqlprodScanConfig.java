package org.magm.backend.config.profile;
//La clase MysqlprodScanConfig en el paquete org.magm.backend.config.profile es una configuración específica para el perfil mysqlprod de la aplicación Spring Boot
//En resumen, la clase MysqlprodScanConfig se encarga de configurar la exploración de repositorios JPA y entidades JPA para el perfil de Spring "mysqlprod", escaneando todos los repositorios y entidades en los paquetes especificados, excluyendo aquellos relacionados con cli1 y cli2.
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.magm.backend", 
excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.magm\\.backend\\.integration\\.cli1\\..*" ),
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.magm\\.backend\\.integration\\.cli2\\..*" )
})


//Entidades
@EntityScan(basePackages = { 
		"org.magm.backend.model", 
		"org.magm.backend.auth"
})

@Profile("mysqlprod")
public class MysqlprodScanConfig {

}

