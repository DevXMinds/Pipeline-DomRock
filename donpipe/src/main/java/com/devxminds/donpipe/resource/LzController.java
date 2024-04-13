package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dao.LzDAO;
import com.devxminds.donpipe.dto.LzDto;
import com.devxminds.donpipe.entidade.Lz;
import com.devxminds.donpipe.service.LzService;
import com.devxminds.donpipe.util.JPAUtil;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/lz")
public class LzController {
    @Autowired
    private LzService lzService;

    /**
     * Mapeamento do método HTTP "Post", quando chamado pelo caminho "endereço/load".
     * <p>
     * Recebe uma String Serializada (JSON) via API Post, cria o Objeto Lz para tal Json e então persiste ele no BD.
     * <p>
     * 'EM' se refere a instância EM criada na classe JPAUtil a partir do EMF estático. Se refere ao persistence.xml
     * @param lzDto Objeto '@RequestBody' LzDTO transformado a partir do JSON recebido no Body da Requisição.
     * @return Retorna nada. Mas pode ser alterado para alguma mensagem devidamente estruturada. (Enviar String não funciona).
     * @throws IOException Tratamento da exceção de Body = null.
     */
    @PostMapping("/load")
    public ResponseEntity<LzDto> register(@RequestBody LzDto lzDto) throws IOException {
        EntityManager em = JPAUtil.getEntityManager();
        LzDAO daoSave = new LzDAO(em);
        em.getTransaction().begin();
        daoSave.salvar(lzService.store(lzDto));
        em.getTransaction().commit();
        em.close();
        return null;
    }

    /**
     * Mapeamento do método HTTP "Get", quando chamado pelo caminho "endereco/".
     * <p>
     * Envia um JSON do objeto Lz encontrado pelo seu ID.
     *
     * @param id Identificador único do Lz a ser buscado no BD.
     * @return String Json para ser consumida pelo front-end
     */
    @GetMapping("/{id}")
    public String getLz(@PathVariable int id){
        EntityManager em = JPAUtil.getEntityManager();
        LzDAO daoGet = new LzDAO(em);
        em.getTransaction().begin();
        Lz lzDoBanco = daoGet.buscar(id);
        String jsonGetResult = lzDoBanco.getColunaPk();
        em.getTransaction().commit();
        em.close();


        return jsonGetResult;

    }

}
