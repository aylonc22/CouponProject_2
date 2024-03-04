package CouponSystem.CouponSystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenAPI(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("CouponSystem api for development");

        Contact myContact = new Contact();
        myContact.setName("Aylon Cohen");
        myContact.setEmail("aylonc1@gmail.com");

        Info info = new Info()
                .title("CouponSystem Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage CouponSystem")
                .contact(myContact);

        return new OpenAPI().info(info).servers(List.of(server));

    }
}
