package com.devxminds.donpipe;

import com.devxminds.donpipe.entidades.Arquivo;
import com.devxminds.donpipe.entidades.RepositorioArquivo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DonpipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonpipeApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.devxminds.donpipe");
		context.refresh();
		RepositorioArquivo repositorioArquivo = context.getBean(RepositorioArquivo.class);
		repositorioArquivo.save(new Arquivo(1L,"teste" , "csv", "abluble"));

		Arquivo arquivo = repositorioArquivo.findArquivoById(1L);
		System.out.println(arquivo);

		context.close();
	}
}



