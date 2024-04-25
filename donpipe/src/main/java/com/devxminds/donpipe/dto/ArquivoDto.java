package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Data Transfer Object (DTO) para objetos Arquivo. Serializa Jsons em Objetos ArquivoDTO.
 * <p>
 * DTO for {@link Arquivo}
 * <p>
 * [ATUALIZAÇÃO] Mudança de estrutura de record para class
 * @author Caue
 * @version 1.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArquivoDto {
    private Long id;
    private User idUser;
    private Empresa idEmpresa;
    private String nomeUpload;
    private boolean header;
    private String delimiter;
    private String tipoArquivo;
    private String dadosArquivo;
    private String nomeArquivo;
    private LocalDate dataCriacao;
    private String estagio;
    private String estatus;
    private LocalDate dataModificacao;
    private Set<Bronze> bronzes = new LinkedHashSet<>();
    private Set<Log> logs = new LinkedHashSet<>();
    private Set<Lz> lzs = new LinkedHashSet<>();
}