package org.example.egarapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.egarapp.dto.DocumentDto;
import org.example.egarapp.entity.Document;
import org.example.egarapp.repositories.DocumentRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService implements CRUDService<DocumentDto> {
    private final DocumentRepository repository;
    @Override
    public DocumentDto getById(Integer id) {
        log.info("Получение документа по id");
        Document document = repository.findById(id).orElse(null);
        if (document == null) {
            return null;
        }
        return mapToDto(document);
    }

    @Override
    public Collection<DocumentDto> getByType(String type) {
        log.info("Получение документов по типу");
        return repository.findAllByType(type).stream()
                .map(DocumentService::mapToDto)
               .toList();
    }

    @Override
    public Collection<DocumentDto> getAll() {
        log.info("Получение всех документов");
        return repository.findAll().stream()
                .map(DocumentService::mapToDto)
                .toList();
    }

    @Override
    public void create(DocumentDto value) {
        log.info("Создание документа");

        Document existingDocument = repository.findByNameAndType(value.getName(), value.getType());

        if (existingDocument != null) {
            log.error("Документ с таким именем и типом уже существует");
            throw new IllegalArgumentException("Документ с таким именем и типом уже существует");
        }

        Document document = mapToEntity(value);
        repository.save(document);
    }


    @Override
    public void update(DocumentDto value) {
        log.info("Обновление документа");
        Document document = mapToEntity(value);
        repository.save(document);
    }

    @Override
    public void delete(Integer id) {
        log.info("Удаление документа");
        repository.deleteById(id);
    }

    public static DocumentDto mapToDto(Document document) {
        return new DocumentDto(document.getId(), document.getName(), document.getType());
    }


    public static Document mapToEntity(DocumentDto documentDto){
        Document document =  new Document();
        document.setId(documentDto.getId());
        document.setName(documentDto.getName());
        document.setType(documentDto.getType());
        return document;
    }
}
