package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI custonOpenAPI () {
		return new OpenAPI()
				.info(new Info()
						.title("API REST Full | Java | CoderHouse")
						.version("1.0.0")
						.description("La API REST proporciona endpoints para administrar alumnos y "
                        		+ "cursos en una plataforma educativa. Permite realizar operaciones "
                        		+ "CRUD (Crear, Leer, Actualizar, Eliminar) tanto para alumnos como "
                        		+ "para cursos. Los endpoints permiten listar, agregar, mostrar, "
                        		+ "editar y eliminar alumnos y cursos. La API está documentada utilizando "
                        		+ "Swagger, lo que facilita la comprensión de los endpoints y su uso.")
						.contact(new Contact()
								.name("Nehuel Caraballo")
								.email("nehuelcaraballo@gmail.com")
								.url("https://portfolio-beta-lac-53.vercel.app/"))
						.license(new License()
								.name("Licencia")
								.url("https://github.com/nehuis/Java.git"))
						)
						.servers(List.of(
								new Server()
									.url("http://localhost:8080")
									.description("Servidor Local"),
								new Server()
									.url("http://localhost:5000")
									.description("Servidor de Testing")
								)
							);	
						
}
	

}
