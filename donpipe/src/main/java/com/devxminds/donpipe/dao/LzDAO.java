package com.devxminds.donpipe.dao;

import com.devxminds.donpipe.entidade.Lz;
import jakarta.persistence.EntityManager;

/**
 * Classe LzDAO com a função de acionar os métodos existentes no EM
 * para Objetos Lz
 *
 * @author AndreWakugawa
 * @Version 1.0
 */
public class LzDAO {
    private final EntityManager em;

    /**
     * Recebe a instãncia do EM e retornoa o em estático
     * @param em retorno do em estático
     */
    public LzDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Recebe o Objeto Lz e persiste ele no BD.
     * @param lz Objeto Lz a ser salvo no BD.(persist é a palavra do Hibernate para "Insert")
     */
    public void salvar(Lz lz) {
        this.em.persist(lz);
    }

    /**
     * Recebe o ID e retorna o Objeto Lz do BD.
     * @param id Identificador único (PK) do objeto Lz presente no BD.O retorno é baseado e um Objeto Lz
     * que contém o mesmo ID.
     */
    public Lz buscar(int id) {
        return this.em.find(Lz.class, id);
    }
}