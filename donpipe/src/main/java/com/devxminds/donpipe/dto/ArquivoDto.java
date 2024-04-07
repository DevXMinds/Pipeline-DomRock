package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * Data Transfer Object (DTO) para objetos Arquivo. Serializa Jsons em Objetos ArquivoDTO.
 *<p>
 * DTO for {@link com.devxminds.donpipe.entidade.Arquivo}
 * @author Caue
 * @version 0.1
 */
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArquivoDto implements Serializable {
    Long id;
    User idUser;
    Empresa idEmpresa;
    String tipoArquivo;
    String dadosArquivo;
    String nomeArquivo;
    LocalDate dataCriacao;
    String estagio;
    String estatus;
    LocalDate dataModificacao;
    Set<Bronze> bronzes;
    Set<Log> logs;
    Set<Lz> lzs;
}