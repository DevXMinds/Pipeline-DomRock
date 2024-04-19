package com.devxminds.donpipe.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "silver", schema = "api_bd3")
public class Silver {
    @Id
    @ColumnDefault("nextval('api_bd3.silver_id_silver_seq')")
    @Column(name = "id_silver", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_arquivo", nullable = false)
    private Bronze idArquivo;

    @Column(name = "tipagem", length = Integer.MAX_VALUE)
    private String tipagem;

    @Column(name = "data_modificacao")
    private LocalDate dataModificacao;

    @Column(name = "pk", length = Integer.MAX_VALUE)
    private String pk;

    @Column(name = "notdeletable", length = Integer.MAX_VALUE)
    private String notdeletable;

    @Column(name = "yaml", length = Integer.MAX_VALUE)
    private String yaml;

    @Column(name = "hash_", length = Integer.MAX_VALUE)
    private String hash;

}