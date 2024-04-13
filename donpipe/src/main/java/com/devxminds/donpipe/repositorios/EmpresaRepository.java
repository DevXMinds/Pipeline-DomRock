package com.devxminds.donpipe.repositorios;

import com.devxminds.donpipe.entidade.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da entidade Empresa. Interface responsável por implementar o "CRUDE" da entidade.
 * <p>
 * Serve como uma "Lista" bem complexa para ler e gravar objetos.Nesse caso, Empresa.
 *
 * @author Caue
 * @version 0.1
 *
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}