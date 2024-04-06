package com.devxminds.donpipe;

import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.repositorio.RepositorioArquivo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DonpipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonpipeApplication.class, args);


		ApplicationContext context = SpringApplication.run(DonpipeApplication.class, args);

		RepositorioArquivo repositorioArquivo = context.getBean(RepositorioArquivo.class);
		repositorioArquivo.save(new Arquivo(1L,"teste" , "csv", "abluble"));

		Arquivo arquivo = repositorioArquivo.findArquivoById(1L);
		System.out.println(arquivo);

	}
}



