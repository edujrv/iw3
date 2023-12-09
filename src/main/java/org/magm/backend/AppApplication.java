package org.magm.backend;
//La clase AppApplication es la clase principal de la aplicación Spring Boot.
//En resumen, la clase AppApplication es la entrada principal de la aplicación Spring Boot, y su método run muestra información sobre el perfil activo en el registro. Además, contiene comentarios sobre consultas que podrían ser utilizadas para interactuar con el repositorio ProductRepository, pero actualmente, estas están comentadas.
import org.magm.backend.model.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AppApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Value("${spring.profiles.active}")
	private String profile;

//	@Autowired
//	private ProductRepository productDAO;

	@Override
	public void run(String... args) throws Exception {
		log.info("Perfil Activo: {}", profile);

//		log.info("Cantidad de productos de la categoría id=1: {}", productDAO.countProductsByCategory(1));
//		log.info("Set stock=true producto id que no existe, resultado={}", productDAO.setStock(true, 333));
		
		

	}

}
