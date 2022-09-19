package com.example.dmsservice.controller;

import com.example.dmsservice.dto.document.DocumentRequest;
import com.example.dmsservice.dto.document.DocumentResponse;
import com.example.dmsservice.service.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<DocumentResponse> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping(params = { "pageSize", "pageNumber", "sortBy" })
    public List<DocumentResponse> getAllDocuments(@RequestParam(defaultValue = "10") int pageSize,
                                                  @RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "id") String sortBy) {
        return documentService.getAllDocuments(pageSize, pageNumber, sortBy);
    }

    @GetMapping("/{id}")
    public DocumentResponse getDocument(@PathVariable Long id) {
        return documentService.getDocument(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentResponse addDocument(@RequestBody @Valid DocumentRequest documentRequest) {
        return documentService.addDocument(documentRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentResponse updateDocument(@RequestBody DocumentRequest documentRequest,
                               @PathVariable Long id) {
        return documentService.updateDocument(documentRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }

}
