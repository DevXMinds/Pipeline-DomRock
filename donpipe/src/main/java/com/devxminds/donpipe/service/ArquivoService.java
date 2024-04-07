package com.devxminds.donpipe.service;

import com.devxminds.donpipe.dto.ArquivoDto;
import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.repositorios.ArquivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * A classe ArquivoService oferece funcionalidades de acesso
 * para entidades Arquivo no Repositório ArquivoRepository.
 *
 * @author AndreWakugawa
 * @version 0.2
 */
@Service
public class ArquivoService {
    @Autowired
    public ArquivoRepository arquivoRepository;

//    @Autowired
//    private RepositorioArquivo repositorioArquivo;

    /**
     * Armazena uma entidade Arquivo recebida como MultipartFile.
     * @param file objeto ArquivoDTO, desserialização do JSON recebido.
     * @return retorna o objeto Arquivo com os atributos do DTO serializado.
     * @throws IOException falha de leitura ou busca
     */
    public Arquivo store(ArquivoDto file) throws IOException {
        Arquivo arquivo = new Arquivo(
                file.getId(),
                file.getIdUser(),
                file.getIdEmpresa(),
                file.getTipoArquivo(),
                file.getDadosArquivo(),
                file.getNomeArquivo(),
                file.getDataCriacao(),
                file.getEstagio(),
                file.getEstatus(),
                file.getDataModificacao(),
                file.getBronzes(),
                file.getLogs(),
                file.getLzs());
        return arquivo;

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
