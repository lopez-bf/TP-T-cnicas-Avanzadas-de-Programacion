package edu.palermo.tp.controlvehiculos.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "Control de vehiculos",
        description = "Microservicio de control de vehiculos",
        contact = @Contact( name = "Blanca López",
                            url = "palermo.edu",
                            email = "lopez.blancaflorencia@gmail.com"),
        license = @License(
                name = "MIT Licence",
                url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")),
        servers = @Server(url = "http://localhost:8080/palermo/tp/controlVehiculos/api/v1/")


)
public class SwaggerConfiguration {
}
