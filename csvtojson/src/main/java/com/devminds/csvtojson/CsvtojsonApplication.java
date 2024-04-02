package com.devminds.csvtojson;

import com.devminds.csvtojson.controller.ConversaoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvtojsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversaoController.class, args);

	}

}
