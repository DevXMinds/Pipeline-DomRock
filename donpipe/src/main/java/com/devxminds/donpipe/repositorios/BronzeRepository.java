package com.devxminds.donpipe.repositorios;

import com.devxminds.donpipe.entidade.Bronze;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BronzeRepository extends JpaRepository<Bronze, Long> {
    Optional<Bronze> findTopByOrderByIdDesc();
}