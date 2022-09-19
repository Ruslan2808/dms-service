package com.example.dmsservice.service;

import com.example.dmsservice.domain.Document;
import com.example.dmsservice.domain.enums.Status;
import com.example.dmsservice.dto.document.DocumentRequest;
import com.example.dmsservice.dto.document.DocumentResponse;
import com.example.dmsservice.exception.DocumentNotFoundException;
import com.example.dmsservice.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;


    @Test
    public void fetchingThrowsExceptionIfDocumentIsNotFoundById(){
        //given
        given(documentRepository.findById(6L)).willReturn(Optional.empty());

        //when
        assertThrows(DocumentNotFoundException.class, () -> {
            documentService.getDocument(6L);
        });

        //then
        verify(documentRepository, never()).save(any(Document.class));
    }

    @Test
    void fetchingNotNullDocumentIfItIsSaved() {
        //given
        DocumentRequest testDocument = DocumentRequest.builder()
                .title("Договор продажи дома")
                .createdAt(LocalDate.of(2021, 5, 15))
                .validAt(LocalDate.of(2021, 9, 15))
                .promisor("Алексеев")
                .promisee("Иванов")
                .status(Status.CREATED)
                .build();
        given(documentRepository.findByTitle(testDocument.getTitle())).willReturn(Optional.empty());

        //when
        DocumentResponse document = documentService.addDocument(testDocument);

        //then
        assertNotNull(document);
    }

}