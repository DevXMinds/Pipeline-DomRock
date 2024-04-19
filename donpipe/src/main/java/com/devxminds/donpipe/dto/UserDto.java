package com.devxminds.donpipe.dto;

import com.devxminds.donpipe.entidade.Empresa;
import com.devxminds.donpipe.entidade.Permissao;
import lombok.Value;

/**
 *Data Transfer Object (DTO) para objetos User. Serializa Jsons em Objetos UserDTO.
 *<p>
 *DTO for {@link com.devxminds.donpipe.entidade.User}
 *@author Caue
 *@version 1.1
 */
@Value
public class UserDto{
    Integer id;
    String nomeUser;
    String email;
    String senha;
    Empresa idEmpresa;
    Permissao permissao;
}