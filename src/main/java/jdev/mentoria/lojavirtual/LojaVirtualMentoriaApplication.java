package jdev.mentoria.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LojaVirtualMentoriaApplication {

	public static void main(String[] args) {

		//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(LojaVirtualMentoriaApplication.class, args);
	}

}
