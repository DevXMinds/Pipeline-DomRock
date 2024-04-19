package com.devxminds.donpipe.dto;

import lombok.Value;

import java.io.Serializable;

/**
 *Data Transfer Object (DTO) para objetos Empresa. Serializa Jsons em Objetos EmpresaDto.
 *<p>
 *DTO for {@link com.devxminds.donpipe.entidade.Empresa}
 *@author Caue
 *@version 1.0
 */
@Value
public class EmpresaDto{
    Integer id;
    String nome;
    String setor;
}