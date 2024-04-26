package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.entidade.User;
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
public class LogDto {
    Long id;
    User idUser;
    LocalDate logDate;
    Arquivo idArquivo;
}
