package com.devxminds.donpipe.entidade;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Classe entidade do objeto Arquivo
 * Referencia diretamente a tabela 'arquivo' do DB. Seus atributos contém as propriedades que as colunas
 * da tabela tem.
 *<p>
 * IMPORTANTE - Ao instanciar um novo objeto Arquivo, não passar o valor do ID.
 * Passar o valor do ID fará com que o repositório procure por um objeto já existente.
 *
 * @author Caue
 * @version 0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "arquivo", schema = "api_bd3")
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('api_bd3.arquivo_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = false)
    private Empresa idEmpresa;

    @Column(name = "tipo_arquivo", nullable = false, length = Integer.MAX_VALUE)
    private String tipoArquivo;

    @Column(name = "dados_arquivo",nullable = false,length = Integer.MAX_VALUE)
    private String dadosArquivo;

    @Column(name = "nome_arquivo", length = 100)
    private String nomeArquivo;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "estagio", length = Integer.MAX_VALUE)
    private String estagio;

    @Column(name = "estatus", length = Integer.MAX_VALUE)
    private String estatus;

    @Column(name = "data_modificacao")
    private LocalDate dataModificacao;

    @OneToMany(mappedBy = "idArquivo")
    private Set<Bronze> bronzes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idArquivo")
    private Set<Log> logs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idArquivo")
    private Set<Lz> lzs = new LinkedHashSet<>();

}