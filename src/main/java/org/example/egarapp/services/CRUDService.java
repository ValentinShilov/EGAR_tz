package org.example.egarapp.services;

import java.time.LocalDateTime;
import java.util.Collection;

public interface CRUDService<T> {
    T getById(Integer id);
    Collection<T> getByType(String type);
    Collection<T> getAll();
    void create(T value);
    void update(T value);
    void delete(Integer id);
}
