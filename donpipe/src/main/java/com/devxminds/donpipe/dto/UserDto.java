package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.Empresa;
import com.devxminds.donpipe.entidade.Permissao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 *Data Transfer Object (DTO) para objetos User. Serializa Jsons em Objetos UserDTO.
 *<p>
 *DTO for {@link com.devxminds.donpipe.entidade.User}
 *@author Caue
 *@version 1.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto{
    Long id;
    String nomeUser;
    String email;
    String senha;
    Empresa idEmpresa;
    Permissao permissao;
}