package fr.epita.services;

import org.hibernate.SessionFactory;

import java.util.List;

public interface IJPADAO<T> {
    void save(T t);
    void update(T t);
    T findById(Long id);
    List<T> findAll();
    void delete(T t);
}
