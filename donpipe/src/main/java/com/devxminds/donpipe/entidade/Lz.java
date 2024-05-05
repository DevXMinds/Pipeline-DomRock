package com.devxminds.donpipe.entidade;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lz_seq_generator")
    @SequenceGenerator(name = "lz_seq_generator", sequenceName = "lz_id_seq", schema = "api_bd3", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonBackReference
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