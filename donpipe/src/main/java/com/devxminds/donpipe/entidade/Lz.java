package com.devxminds.donpipe.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lz")
public class Lz {
    @Id
    @Column(name = "pk", nullable = false, length = Integer.MAX_VALUE)
    private String pk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_arquivo", nullable = false)
    private Arquivo idArquivo;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "notdeletable", length = Integer.MAX_VALUE)
    private String notdeletable;

}