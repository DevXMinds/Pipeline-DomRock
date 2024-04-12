package com.devxminds.donpipe;

import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.repositorio.RepositorioArquivo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DonpipeApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DonpipeApplication.class, args);
	}
}



