package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BronzeDto {
    private Long id;
    private User idUser;
    private Arquivo idArquivo;
    private LocalDate dataModificacao;
    private String pk;
    private String naodeletavel;
    private String hash;
    private String tipagemBronze;
}