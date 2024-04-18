package com.devxminds.donpipe.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lz", schema = "api_bd3")
public class Lz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_arquivo", nullable = false)
    private Arquivo idArquivo;
    
    @CreationTimestamp
    @Column(name = "data")
    private LocalDate data;

    @Column(name = "coluna_pk", length = Integer.MAX_VALUE)
    private String colunaPk;

    @Column(name = "notdeletable", length = Integer.MAX_VALUE)
    private String notdeletable;

}