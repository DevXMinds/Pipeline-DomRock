package com.devxminds.donpipe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 *Data Transfer Object (DTO) para objetos Empresa. Serializa Jsons em Objetos EmpresaDto.
 *<p>
 *DTO for {@link com.devxminds.donpipe.entidade.Empresa}
 *@author Caue
 *@version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpresaDto{
    Long id;
    String nome;
    String setor;
}