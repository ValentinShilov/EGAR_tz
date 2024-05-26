package org.example.egarapp.repositories;

import org.example.egarapp.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findAllByDate(LocalDateTime localDateTime);
    List<Document> findAllByType(String type);

    Document findByNameAndType(String name, String type);
}
