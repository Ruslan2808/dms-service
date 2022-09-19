package com.example.dmsservice.dto.document;

import com.example.dmsservice.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRequest {

    @NotBlank(message = "Document title should not be empty")
    private String title;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotNull(message = "Document creation date should not be empty")
    private LocalDate createdAt;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotNull(message = "Document expiration date should not be empty")
    private LocalDate validAt;
    @NotBlank(message = "Document promisor should not be empty")
    private String promisor;
    @NotBlank(message = "Document promisee should not be empty")
    private String promisee;
    private Status status;

}
