package com.devminds.csvtojson.controller;

import com.devminds.csvtojson.model.Conversor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@SpringBootApplication
public class ConversaoController {

    public static void main(String[] args) {
        SpringApplication.run(ConversaoController.class, args);

    }
    @PostMapping("/converter")
    public String converterCSVParaJSON(@RequestParam("arquivo") MultipartFile caminhoArquivo) {
        try {
            return Conversor.converterCSVParaJSON(caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

