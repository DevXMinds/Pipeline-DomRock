package com.devxminds.donpipe.repositorio;

import com.devxminds.donpipe.entidade.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
