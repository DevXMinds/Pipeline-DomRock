package com.devxminds.donpipe.repositorio;

public interface DemoRepositorio<T> {
    public void save(T t);

    public T findArquivoById(Long id);
}
