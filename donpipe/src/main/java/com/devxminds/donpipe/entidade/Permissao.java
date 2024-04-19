package com.devxminds.donpipe.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissao", schema = "api_bd3")
public class Permissao {
    @Id
    @Column(name = "nome", nullable = false)
    private Long nome;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;
}