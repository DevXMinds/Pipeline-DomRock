package com.devxminds.donpipe.service;

import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.repositorio.ArquivoRepository;
import com.devxminds.donpipe.repositorio.RepositorioArquivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A classe ArquivoService oferece funcionalidades de acesso
 * para entidades Arquivo no Repositório ArquivoRepository.
 *
 * @author AndreWakugawa
 * @version 0.1
 */
@Service
public class ArquivoService {
    @Autowired
    private ArquivoRepository arquivoRepository;

//    @Autowired
//    private RepositorioArquivo repositorioArquivo;

    /**
     * Armazena uma entidade Arquivo recebida como MultipartFile.
     * @param file arquivo Multipart
     * @return adiciona um Arquivo ao repositório
     * @throws IOException falha de leitura ou busca
     */
    public Arquivo store(MultipartFile file) throws IOException {
        String content = new String(file.getBytes());
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Arquivo arquivo = new Arquivo(fileName, file.getContentType(), content);

//        return repositorioArquivo.save(arquivo);
        return arquivoRepository.save(arquivo);
    }

    /**
     * Busca um arquivo no repositório.
     * @param id id do Arquivo
     * @return Arquivo com o id inserido
     */
    public Arquivo getArquivo(Long id) {
        return arquivoRepository.findById(id).get();
    }

    /**
     * Busca todos os Arquivos no repositório.
     * @return todos os Arquivos
     */
    public Stream<Arquivo> getAllArquivos(){
        return arquivoRepository.findAll().stream();
    }

    /**
     * Excluí um arquivo no repositório.
     * @param id id do Arquivo
     */
    public void deleteArquivoById(Long id) {
        if (arquivoRepository.existsById(id)) {
            arquivoRepository.deleteById(id);
        }
    }
}
