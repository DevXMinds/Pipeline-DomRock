package com.devxminds.donpipe.service;

import com.devxminds.donpipe.dto.BronzeDto;
import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.entidade.Bronze;
import com.devxminds.donpipe.repositorios.ArquivoRepository;
import com.devxminds.donpipe.repositorios.BronzeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * A classe BronzeService oferece funcionalidades de acesso
 * para entidades Bronze no Repositório BronzeRepository.
 * @author AndreWakugawa
 * @version 1.0
 */
@Service
public class BronzeService {
    @Autowired
    private BronzeRepository bronzeRepository;
    @Autowired
    private ArquivoRepository arquivoRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Armazena a entidade Bronze e atualiza a entidade Arquivo
     * com o estagio bronze finalizado.
     * @param file objeto bronzeDto
     * @return Entidade para confirmação ao usuario.
     */
    @Transactional
    public Bronze store(BronzeDto file){
        Bronze bronze = modelMapper.map(file, Bronze.class);
        bronze = bronzeRepository.save(bronze);
        Arquivo arquivo = arquivoRepository.findById(bronze.getIdArquivo().getId())
                                           .orElseThrow(() -> new NoSuchElementException("Arquivo nao encontrado"));
        arquivo.setEstagio("bronze");
        arquivoRepository.save(arquivo);
        return bronze;
    }

    public BronzeDto findById(long id){
        Optional<Bronze> bronze = bronzeRepository.findById(id);
        if(bronze.isPresent()){
            return modelMapper.map(bronze.get(), BronzeDto.class);
        } else {
            throw new NoSuchElementException("Bronze não encontrado com o id:" +id);
        }
    }
}
