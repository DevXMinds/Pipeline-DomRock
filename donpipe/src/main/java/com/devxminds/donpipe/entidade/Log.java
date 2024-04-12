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
@Table(name = "log")
public class Log {
    @Id
    @ColumnDefault("nextval('api_bd3.log_id_log_seq'")
    @Column(name = "id_log", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_arquivo", nullable = false)
    private Arquivo idArquivo;

    @Column(name = "data_insercao")
    private LocalDate dataInsercao;

    @Column(name = "data_edicao")
    private LocalDate dataEdicao;

    @Column(name = "estagio", length = Integer.MAX_VALUE)
    private String estagio;

}