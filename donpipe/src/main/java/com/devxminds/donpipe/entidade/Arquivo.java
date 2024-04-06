package com.devxminds.donpipe.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.*;


/**
 * A classe Arquivo representa arquivos armazenados no banco de dados.
 * Cada instância desta classe contém informações sobre um arquivo,
 * incluindo seu nome, tipo e os dados do arquivo em si.
 *</p>
 * O atributo 'id' é a chave primária da entidade e é gerado automaticamente pelo banco de dados.
 *
 * @author Gabriel
 * @version 0.1
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARQUIVO", schema = "API_BD3")
public class Arquivo {
    /**
     * Identificador único para o arquivo.
     * É gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_arquivo", length = 100)
    private String name;

    @Column(name = "tipo_arquivo")
    private String type;
    /**
     * Os dados do arquivo como um array de bytes. Não pode ser nulo.
     * Utiliza-se a anotação '@Lob' para indicar que é um objeto grande.
     */
    @Lob
    @Column(name = "dados_arquivo")
    private String data;

    /**
     * Construtor sem ID que será gerado sequencialmente
     * @param fileName nome do arquivo
     * @param contentType tipo do arquivo
     * @param content conteúdo do arquivo em String
     */
    public Arquivo(String fileName, String contentType, String content) {
        this.name = fileName;
        this.type = contentType;
        this.data = content;
    }
}
