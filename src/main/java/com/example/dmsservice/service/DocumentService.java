package com.example.dmsservice.service;

import com.example.dmsservice.domain.Document;
import com.example.dmsservice.dto.document.DocumentRequest;
import com.example.dmsservice.dto.document.DocumentResponse;
import com.example.dmsservice.exception.DocumentAlreadyExistsException;
import com.example.dmsservice.exception.DocumentNotFoundException;
import com.example.dmsservice.repository.DocumentRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentResponse> getAllDocuments() {
        log.info("Fetching documents");
        return documentRepository.findAll()
                .stream()
                .map(this::mapToDocumentResponse)
                .collect(Collectors.toList());
    }


    public List<DocumentResponse> getAllDocuments(int pageSize, int pageNumber, String sortBy) {
        log.info("Fetching {} documents on {} page sortBy {}", pageSize, pageNumber, sortBy);
        var page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return documentRepository.findAll(page)
                .stream()
                .map(this::mapToDocumentResponse)
                .collect(Collectors.toList());
    }

    public DocumentResponse getDocument(Long id) {
        log.info("Fetching a document with id = {}", id);
        var document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Document with id = " + id + " not found"));
        return mapToDocumentResponse(document);
    }

    public DocumentResponse addDocument(DocumentRequest documentRequest) {
        log.info("Adding a new document");
        Optional<Document> optionalDocument = documentRepository.findByTitle(documentRequest.getTitle());
        if (optionalDocument.isPresent()) {
            throw new DocumentAlreadyExistsException("Документ " + documentRequest.getTitle() + " уже существует");
        }
        Document document = mapToDocument(documentRequest);
        documentRepository.save(document);
        return mapToDocumentResponse(document);
    }

    public DocumentResponse updateDocument(DocumentRequest documentRequest, Long id) {
        log.info("Updating document with id = {}", id);
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Документ с id = " + id + " не найден"));
        Optional<Document> optionalDocument = documentRepository.findByTitle(documentRequest.getTitle());
        if (optionalDocument.isPresent()) {
            throw new DocumentAlreadyExistsException("Документ " + documentRequest.getTitle() + " уже существует");
        }
        document.setTitle(documentRequest.getTitle());
        document.setCreatedAt(documentRequest.getCreatedAt());
        document.setValidAt(documentRequest.getValidAt());
        document.setPromisor(documentRequest.getPromisor());
        document.setPromisee(documentRequest.getPromisee());
        document.setStatus(documentRequest.getStatus());
        documentRepository.save(document);
        return mapToDocumentResponse(document);
    }

    public void deleteDocument(Long id) {
        log.info("Deleting document with id = {}", id);
        documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document with id = " + id + " not found"));
        documentRepository.deleteById(id);
    }

    public DocumentResponse mapToDocumentResponse(Document document) {
        return DocumentResponse.builder()
                .id(document.getId())
                .title(document.getTitle())
                .createdAt(document.getCreatedAt())
                .validAt(document.getValidAt())
                .promisor(document.getPromisor())
                .promisee(document.getPromisee())
                .status(document.getStatus())
                .build();
    }

    public Document mapToDocument(DocumentRequest documentRequest) {
        return Document.builder()
                .title(documentRequest.getTitle())
                .createdAt(documentRequest.getCreatedAt())
                .validAt(documentRequest.getValidAt())
                .promisor(documentRequest.getPromisor())
                .promisee(documentRequest.getPromisee())
                .status(documentRequest.getStatus())
                .isDeleted(false)
                .build();
    }

}
