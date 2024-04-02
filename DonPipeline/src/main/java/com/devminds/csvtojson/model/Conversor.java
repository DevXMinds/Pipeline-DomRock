package com.devminds.csvtojson.model;

import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Conversor {
    public static String converterCSVParaJSON(MultipartFile caminhoArquivo) throws IOException {
        Reader reader = new FileReader(String.valueOf(caminhoArquivo.getOriginalFilename()));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'));
        List<Object> jsonData = new ArrayList<>();
        for (CSVRecord csvRecord : csvParser) {
            jsonData.add(csvRecord.values());
        }
        Gson gson = new Gson();
        return gson.toJson(jsonData);
    }
}
