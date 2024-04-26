package com.devxminds.donpipe.repositorios;

import com.devxminds.donpipe.entidade.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}