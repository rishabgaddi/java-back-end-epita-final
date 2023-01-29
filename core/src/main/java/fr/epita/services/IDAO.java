package fr.epita.services;

import java.util.List;

public interface IDAO<T> {
    void save(T t);

    void update(T t);

    T findById(Long id);

    List<T> findAll();

    void delete(T t);
}
