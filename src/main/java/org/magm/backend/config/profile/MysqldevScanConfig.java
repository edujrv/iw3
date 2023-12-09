package org.magm.backend.config.profile;
//La clase MysqldevScanConfig en el paquete org.magm.backend.config.profile es una configuración específica para el perfil mysqldev de la aplicación Spring Boot.
//En resumen, la clase MysqldevScanConfig se encarga de configurar la exploración de repositorios JPA y entidades JPA para el perfil de Spring "mysqldev", escaneando todos los repositorios y entidades en los paquetes especificados.
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.magm.backend", 
excludeFilters = {
})


//Entidades
@EntityScan(basePackages = { 
		"org.magm.backend.model", 
		"org.magm.backend.auth", 
		"org.magm.backend.integration.cli1.model", 
		"org.magm.backend.integration.cli2.model" 
})

@Profile("mysqldev")
public class MysqldevScanConfig {

}

