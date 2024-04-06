package com.devxminds.donpipe.entidades;

/**
 * A classe RepositorioArquivo implementa a interface DemoRepositorio para manipulação de objetos Arquivo.
 * Esta implementação utiliza um mapa para armazenar os arquivos em memória.
 */
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RepositorioArquivo implements DemoRepositorio<Arquivo> {
    private Map<Long, Arquivo> repositorio;
    public RepositorioArquivo(){
        this.repositorio = new HashMap<>();
    }

    /**
     * Salva o arquivo fornecido no repositório.
     *
     * @param arquivo o arquivo a ser salvo.
     */
    @Override
    public void save(Arquivo arquivo) {
        repositorio.put(arquivo.getId(), arquivo);
    }

    /**
     * Retorna o arquivo pelo ID fornecido.
     *
     * @param id o ID do arquivo a ser retornado.
     * @return o arquivo correspondente ao ID fornecido, ou null se não encontrado.
     */
    @Override
    public Arquivo findArquivoById(Long id) {
        return repositorio.get(id);
    }
}
