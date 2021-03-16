package ru.geekbrains.service;

import java.util.List;

public interface Service<T> {

    List<T> findAll();

    T findById(Long id);

    Long countAll();

    void saveOrUpdate(T t);

    void deleteById(Long id);
}