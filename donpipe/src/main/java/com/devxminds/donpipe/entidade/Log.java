package com.devxminds.donpipe.entidade;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq_generator")
    @SequenceGenerator(name = "log_seq_generator", sequenceName = "log_id_log_seq", schema = "api_bd3", allocationSize = 1)
    @Column(name = "id_log", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @Column(name = "log_date")
    private LocalDate logDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_arquivo")
    private Arquivo idArquivo;

}