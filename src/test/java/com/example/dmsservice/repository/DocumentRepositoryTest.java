package com.example.dmsservice.repository;

import com.example.dmsservice.domain.Document;
import com.example.dmsservice.domain.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void fetchingNotNullDocumentIfItIsFoundByTitle() {
        //given
        String title = "Договор аренды дома";

        //when
        Document expectedDocument = documentRepository.findByTitle(title).get();

        //then
        assertNotNull(expectedDocument);
    }

    @Test
    void ifDocumentIsNotFoundByTitleThenItIsEmpty() {
        //given
        String title = "Договор аренды холодильника";

        //when
        Optional<Document> expectedDocument = documentRepository.findByTitle(title);

        //then
        assertTrue(expectedDocument.isEmpty());
    }

}