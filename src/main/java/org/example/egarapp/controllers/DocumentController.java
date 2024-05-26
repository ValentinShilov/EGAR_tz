package org.example.egarapp.controllers;

import org.example.egarapp.dto.DocumentDto;
import org.example.egarapp.services.CRUDService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/index")
public class DocumentController {
    private final CRUDService<DocumentDto> documentService;

    public DocumentController(CRUDService<DocumentDto> documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/id/{id}")
    public DocumentDto getById(@PathVariable int id) {
        return documentService.getById(id);
    }
    @GetMapping("/type/{type}")
    public Collection<DocumentDto> getByType(@PathVariable String type) {
        return documentService.getByType(type);
    }
    @GetMapping()
    public Collection<DocumentDto> getAll() {
        return documentService.getAll();
    }
    @PostMapping
    public void createDoc(@RequestBody DocumentDto doc){
        documentService.create(doc);
    }
    @PutMapping("/{id}")
    public void updateDoc(@PathVariable Integer id, @RequestBody DocumentDto doc){
        doc.setId(id);
        documentService.update(doc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        documentService.delete(id);
    }
}
