package br.com.prova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan({"br.com.prova.model"})//mapeia as classes do pacote para o banco de dados
@ComponentScan(basePackages = {"br.*"})// injeção de dependência 
@EnableJpaRepositories(basePackages = {"br.com.prova.repository"}) // CRUD
@EnableTransactionManagement // gerenciar transações
@EnableWebMvc //MVC
@RestController //REST
@EnableAutoConfiguration //configuração automática
public class ProvabackendjavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvabackendjavaApplication.class, args);
	}

}
