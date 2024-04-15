package com.devxminds.donpipe.dao;

import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.entidade.Lz;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * Classe ArquivoDAO com a função de acionar os métodos existentes no EM
 * para Objetos Arquivo
 *
 * @author caueh
 * @Version 1.0
 */
public class ArquivoDAO {
    private final EntityManager em;

    /**
     * Recebe a instãncia do EM e retornoa o em estático
     * @param em retorno do em estático
     */
    public ArquivoDAO(EntityManager em) {
        this.em = em;
    }
    /**
     * Recebe o Objeto arquivo e persiste ele no BD.
     * @param arquivo Objeto Arquivo a ser salvo no BD.(persist é a palavra do Hibernate para "Insert")
     */
    public void salvar(Arquivo arquivo) {
        this.em.persist(arquivo);
    }
    /**
     * Recebe o ID e retorna o Objeto Arquivo do BD.
     * @param id Identificador único (PK) do objeto Arquivo presente no BD.O retorno é baseado e um Objeto Arquivo
     * que contém o mesmo ID.
     */
    public Arquivo buscar(int id) {
        return this.em.find(Arquivo.class, id);
    }
    public List<Arquivo> buscarTodos() {
        return this.em.createQuery("from Arquivo ", Arquivo.class).getResultList();
    }
}
