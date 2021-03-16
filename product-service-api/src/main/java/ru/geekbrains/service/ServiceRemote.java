package ru.geekbrains.service;

import java.util.List;

public interface ServiceRemote<T> {

    List<T> findAll();

    T findById(Long id);

    Long countAll();
}
