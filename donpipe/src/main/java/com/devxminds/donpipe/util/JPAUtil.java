package com.devxminds.donpipe.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsável por criar os EMFactory e passar os EM para os métodos necessários.
 * Pode ser utilizada para declarar TODOS os EMFactory de cada entidade.
 *
 * Retorna para quem chamar a instância EM da devida classe. Uma instância é estática e única para cada entidade.
 * Não instanciar NEW.
 *
 * @Author Caue
 * @Version 0.1
 *
 */
public class JPAUtil {

    /**
     * Instanciação do EMF utilizando o arquivo 'persistence.xml' localizado em /resources/persistence.xml'
     * No arquivo, está declarado o nome da Persistence Unit como  "Arquivo", mas teoricamente serve para qualquer objeto
     *<p>
     *  Não é necessário Instanciar novamente o EMF.
     */
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.
            createEntityManagerFactory("Arquivo");

    /**
     *Retorna o EM a partir do EMF estático criado acima.
     *
     *
     * @return retorna o objeto EM para ser utilizado para persistir,atualizar e deletar.
     */

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

}
