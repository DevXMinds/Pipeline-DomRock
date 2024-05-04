package com.devxminds.donpipe.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.devxminds.donpipe.entidade.Permissao}
 */
@Getter
@Setter
@AllArgsConstructor
@Value
public class PermissaoDto implements Serializable {
    String nome;
    String descricao;
}