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
@Table(name = "bronze")
public class Bronze {
    @Id
    @ColumnDefault("nextval('api_bd3.bronze_id_bronze_seq'")
    @Column(name = "id_bronze", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_arquivo", nullable = false)
    private Arquivo idArquivo;

    @Column(name = "data_modificacao")
    private LocalDate dataModificacao;

    @Column(name = "pk", length = Integer.MAX_VALUE)
    private String pk;

    @Column(name = "naodeletavel", length = Integer.MAX_VALUE)
    private String naodeletavel;

    @Column(name = "hash_", length = Integer.MAX_VALUE)
    private String hash;

    @Column(name = "tipagem_bronze", length = Integer.MAX_VALUE)
    private String tipagemBronze;

}