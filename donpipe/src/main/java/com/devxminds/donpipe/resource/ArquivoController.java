package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dto.ArquivoDto;
import com.devxminds.donpipe.entidade.Arquivo;
import com.devxminds.donpipe.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller Rest da aplicação. Aqui ficam os métodos HTTP para conversação do Front-end com o Back-end.
 * <p>
 * Define o comportamento da API.
 *
 * @Author Caue
 * @Version 1.1
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
     * [ATUALIZAÇÃO] Anteriormente na versão 1.0 era utilizado o EntityManager.
     * Agora na versão 1.1 foi implementado o Service corretamente.
     * @param arquivoDto Objeto '@RequestBody' ArquivoDTO transformado a partir do JSON recebido no Body da Requisição.
     * @return Retorna nada. Mas pode ser alterado para alguma mensagem devidamente estruturada. (Enviar String não funciona).
     */
    @PostMapping("/load")
    public ResponseEntity<Arquivo> register(@RequestBody ArquivoDto arquivoDto) {
        Arquivo arquivoCriado = arquivoService.store(arquivoDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(arquivoCriado);
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
    public ResponseEntity<ArquivoDto> findById(@PathVariable Long id) {
        ArquivoDto arquivoDto = arquivoService.findById(id);
        if (arquivoDto != null) {
            return ResponseEntity.ok(arquivoDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Envia um JSON do objeto Arquivo mais recente.
     *
     * @return String Json
     */
    @GetMapping("/latestArquivo")
    public ResponseEntity<Arquivo> getLatestArquivo(){
        Optional<Arquivo> arquivoOptional = arquivoService.getMostRecentArquivo();
        if (arquivoOptional.isPresent()) {
            return ResponseEntity.ok(arquivoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
