package com.devxminds.donpipe.repositorios;

import com.devxminds.donpipe.entidade.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da entidade User. Interface responsável por implementar o "CRUDE" da entidade.
 * <p>
 * Serve como uma "Lista" bem complexa para ler e gravar objetos.Nesse caso, User.
 *
 * @author Caue
 * @version 0.1
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}