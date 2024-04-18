package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.Arquivo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para objetos Lz. Serializa Jsons em Objetos LzDTO.
 * DTO for {@link com.devxminds.donpipe.entidade.Lz}
 * <p>
 * [ATUALIZAÇÃO] Mudança de estrutura de record para class
 * @author AndreWakugawa
 * @version 1.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LzDto {
    Long id;
    Arquivo idArquivo;
    LocalDate data;
    String colunaPk;
    String notdeletable;
}