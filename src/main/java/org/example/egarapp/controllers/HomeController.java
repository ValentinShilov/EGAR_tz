package org.example.egarapp.controllers;

import org.example.egarapp.dto.DocumentDto;
import org.example.egarapp.services.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomeController {

    private final DocumentService documentService;

    public HomeController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/")
    public String getDocuments(Model model) {
        Collection<DocumentDto> documents = documentService.getAll();
        model.addAttribute("documents", documents);
        return "index";
    }
}