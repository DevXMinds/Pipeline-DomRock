package com.devxminds.donpipe.entidade;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissao_seq_generator")
    @SequenceGenerator(name = "permissao_seq_generator", sequenceName = "permissao_id_seq", schema = "api_bd3", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;
}