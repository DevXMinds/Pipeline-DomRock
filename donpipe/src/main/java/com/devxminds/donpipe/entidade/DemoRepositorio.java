package com.devxminds.donpipe.entidades;

/**
 * A interface DemoRepositorio define operações básicas para manipulação de entidades genéricas.
 * Implementações desta interface devem fornecer métodos para salvar e recuperar entidades por ID.
 *
 * @param <T> o tipo da entidade a ser manipulada pelo repositório.
 */
public interface DemoRepositorio<T> {
    /**
     * Salva a entidade fornecida.
     *
     * @param t a entidade a ser salva.
     */
    public void save(T t);

    /**
     * Recupera a entidade pelo ID fornecido.
     *
     * @param id o ID da entidade a ser recuperada.
     * @return a entidade correspondente ao ID fornecido, ou null se não encontrada.
     */
    public T findArquivoById(Long id);
}
