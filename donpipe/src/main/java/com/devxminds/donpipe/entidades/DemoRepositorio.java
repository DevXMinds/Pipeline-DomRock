package com.devxminds.donpipe.entidades;

public interface DemoRepositorio<T> {
    public void save(T t);

    public T findArquivoById(Long id);
}
