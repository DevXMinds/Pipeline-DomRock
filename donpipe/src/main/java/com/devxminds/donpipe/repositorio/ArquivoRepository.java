package com.devxminds.donpipe.repositorio;

import com.devxminds.donpipe.entidade.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}