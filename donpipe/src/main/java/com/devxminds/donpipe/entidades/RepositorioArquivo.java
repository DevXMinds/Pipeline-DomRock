package com.devxminds.donpipe.entidades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RepositorioArquivo implements DemoRepositorio<Arquivo> {
    private Map<Long, Arquivo> repositorio;

    public RepositorioArquivo(){
        this.repositorio = new HashMap<>();
    }

    @Override
    public void save(Arquivo arquivo) {
        repositorio.put(arquivo.getId(), arquivo);
    }

    @Override
    public Arquivo findArquivoById(Long id) {
        return repositorio.get(id);
    }
}
