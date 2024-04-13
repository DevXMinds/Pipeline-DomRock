package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.Arquivo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.devxminds.donpipe.entidade.Lz}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LzDto(Long id,
                    Arquivo idArquivo,
                    LocalDate data,
                    String colunaPk,
                    String notdeletable) implements Serializable {
}