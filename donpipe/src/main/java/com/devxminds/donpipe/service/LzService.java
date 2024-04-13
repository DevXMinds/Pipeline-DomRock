package com.devxminds.donpipe.service;

import com.devxminds.donpipe.dto.LzDto;
import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.entidade.Lz;
import com.devxminds.donpipe.repositorios.LzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Stream;


@Service
public class LzService {
    @Autowired
    public LzRepository lzRepository;

    /**
     * Armazena uma entidade Lz recebida.
     * @param file objeto LzDTO, desserialização do JSON recebido.
     * @return retorna o objeto Lz com os atributos do DTO serializado.
     * @throws IOException falha de leitura ou busca
     */
    public Lz store(LzDto file) throws IOException {
        Lz lz = new Lz(
                file.id(),
                file.idArquivo(),
                file.data(),
                file.colunaPk(),
                file.notdeletable());
        return lz;
    }

    /**
     * Busca uma Lz no repositório.
     * @param id id da Lz
     * @return Lz com o id inserido
     */
    public Lz getLz(Long id) {
        return lzRepository.findById(id).get();
    }

    /**
     * Busca todas as Lz's no repositório.
     * @return todas as Lz's
     */
    public Stream<Lz> getAllLz(){
        return lzRepository.findAll().stream();
    }

    /**
     * Excluí uma Lz no repositório.
     * @param id id da Lz
     */
    public void deleteLzById(Long id) {
        if (lzRepository.existsById(id)) {
            lzRepository.deleteById(id);
        }
    }

}