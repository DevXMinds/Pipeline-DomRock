package com.devxminds.donpipe.service;

import com.devxminds.donpipe.dto.ArquivoDto;
import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.repositorios.ArquivoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A classe ArquivoService oferece funcionalidades de acesso
 * para entidades Arquivo no Repositório ArquivoRepository.
 *
 * @author AndreWakugawa
 * @version 1.1
 */
@Service
public class ArquivoService {
    @Autowired
    public ArquivoRepository arquivoRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Armazena uma entidade Arquivo através da conversão de um ArquivoDto.
     * @param file objeto ArquivoDTO, desserialização do JSON recebido.
     * @return retorna o objeto Arquivo com os atributos do DTO serializado.
     */
    public Arquivo store(ArquivoDto file) {
        Arquivo arquivo = modelMapper.map(file, Arquivo.class);
        arquivo = arquivoRepository.save(arquivo);
        return arquivo;
    }

    /**
     * Busca um arquivo no repositório.
     * @param id id do Arquivo
     * @return Arquivo com o id inserido
     */
    public ArquivoDto findById(Long id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        if (arquivo.isPresent()) {
            return modelMapper.map(arquivo.get(), ArquivoDto.class);
        } else {
            throw new NoSuchElementException("Arquivo não encontrado com o id:" +id);
        }
    }

    public ArquivoDto getByEstagio(String estagio) {
        Optional<Arquivo> arquivo = arquivoRepository.findAllByEstagio(estagio);
        if (arquivo.isPresent()) {
            return modelMapper.map(arquivo.get(), ArquivoDto.class);
        } else {
            throw new NoSuchElementException("Arquivos não encontrados com o Estágio:" +estagio);
        }
    }

    /**
     * Busca o arquivo mais recente no repositório.
     * @return Arquivo com o id mais recente
     */
    public Optional<Arquivo> getMostRecentArquivo() {
        return arquivoRepository.findTopByOrderByIdDesc();
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
