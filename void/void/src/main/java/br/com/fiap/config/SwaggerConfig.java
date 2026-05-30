package br.com.fiap.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI voidOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API VOID - Telemetria Espacial e Reabilitação")
                        .description("API REST para gerenciamento de pacientes, fisioterapeutas e sessões de telemetria baseada no padrão ISS.")
                        .version("v1.0.0"));
    }
}