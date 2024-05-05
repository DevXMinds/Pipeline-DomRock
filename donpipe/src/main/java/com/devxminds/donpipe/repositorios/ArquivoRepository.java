package com.devxminds.donpipe.repositorios;

import com.devxminds.donpipe.entidade.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório da entidade Arquivo. Interface responsável por implementar o "CRUDE" da entidade.
 * <p>
 * Serve como uma "Lista" bem complexa para ler e gravar objetos.Nesse caso, Arquivos.
 *
 * @author Caue
 * @version 0.1
 *
 */
@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    /**
     *Método JPA:
     * find -> palavra-chave de busca.
     * Top -> busca somente o resultado no topo.
     * By -> separador de busca (antes) e critério (depois)
     * OrderBy -> define classificação
     *
     */
    Optional<Arquivo> findTopByOrderByIdDesc();

    List<Arquivo> findAllByEstagio(String estagio);
}