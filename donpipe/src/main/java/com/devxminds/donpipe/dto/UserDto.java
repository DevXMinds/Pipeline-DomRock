package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.Empresa;
import lombok.Value;

import java.io.Serializable;

/**
 *Data Transfer Object (DTO) para objetos User. Serializa Jsons em Objetos UserDTO.
 *<p>
 *DTO for {@link com.devxminds.donpipe.entidade.User}
 *@author Caue
 *@version 0.1
 */
@Value
public class UserDto implements Serializable {
    Integer id;
    String nomeUser;
    String email;
    String senha;
    Empresa idEmpresa;
}