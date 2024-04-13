package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dao.ArquivoDAO;
import com.devxminds.donpipe.dto.ArquivoDto;
import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.service.ArquivoService;
import com.devxminds.donpipe.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

/**
 * Controller Rest da aplicação. Aqui ficam os métodos HTTP para conversação do Front-end com o Back-end.
 * <p>
 * Define o comportamento da API.
 *
 * @Author Caue
 * @Version 0.1
 */
@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
    @Autowired
    private ArquivoService arquivoService;

    /**
     * Mapeamento do método HTTP "Post", quando chamado pelo caminho "endereço/load".
     * <p>
     * Recebe uma String Serializada (JSON) via API Post, cria o Objeto Arquivo para tal Json e então persiste ele no BD.
     * <p>
     * 'EM' se refere a instância EM criada na classe JPAUtil a partir do EMF estático. Se refere ao persistence.xml
     * @param arquivoDto Objeto '@RequestBody' ArquivoDTO transformado a partir do JSON recebido no Body da Requisição.
     * @return Retorna nada. Mas pode ser alterado para alguma mensagem devidamente estruturada. (Enviar String não funciona).
     * @throws IOException Tratamento da exceção de Body = null.
     */
    @PostMapping("/load")
    public ResponseEntity<ArquivoDto> register(@RequestBody ArquivoDto arquivoDto) throws IOException {
        EntityManager em = JPAUtil.getEntityManager();
        ArquivoDAO daoSave = new ArquivoDAO(em);
        em.getTransaction().begin();
        daoSave.salvar(arquivoService.store(arquivoDto));
        em.getTransaction().commit();
        em.close();
        return null;
    }

    /**
     * Mapeamento do método HTTP "Get", quando chamado pelo caminho "endereco/".
     * <p>
     * Envia um JSON do objeto Arquivo encontrado pelo seu ID.
     *
     * @param id Identificador único do Arquivo a ser buscado no BD.
     * @return String Json para ser consumida pelo front-end
     */
    @GetMapping("/{id}")
    public String getArquivo(@PathVariable int id){
        EntityManager em = JPAUtil.getEntityManager();
        ArquivoDAO daoGet = new ArquivoDAO(em);
        em.getTransaction().begin();
        Arquivo arquivoDoBanco = daoGet.buscar(id);
        String jsonGetResult = arquivoDoBanco.getNomeArquivo();
        em.getTransaction().commit();
        em.close();


        return jsonGetResult;

    }




}
