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
@Table(name = "lz")
public class Lz {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lz_id_gen")
    @SequenceGenerator(name = "lz_id_gen", sequenceName = "lz_id_seq", allocationSize = 1)
    @ColumnDefault("nextval('api_bd3.lz_id_seq1'")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_arquivo", nullable = false)
    private Arquivo idArquivo;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "coluna_pk", length = Integer.MAX_VALUE)
    private String colunaPk;

    @Column(name = "notdeletable", length = Integer.MAX_VALUE)
    private String notdeletable;

}