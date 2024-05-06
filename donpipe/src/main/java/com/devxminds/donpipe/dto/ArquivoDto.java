package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.Empresa;
import com.devxminds.donpipe.entidade.Lz;
import com.devxminds.donpipe.entidade.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.devxminds.donpipe.entidade.Arquivo}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArquivoDto {
    Long id;
    User idUser;
    Empresa idEmpresa;
    String nomeUpload;
    boolean header;
    String delimiter;
    String tipoArquivo;
    String dadosArquivo;
    String nomeArquivo;
    LocalDate dataCriacao;
    String estagio;
    String estatus;
    LocalDate dataModificacao;
    Set<BronzeDto> bronzes;
    Set<LogDto> logs;
    Lz lzs;
}