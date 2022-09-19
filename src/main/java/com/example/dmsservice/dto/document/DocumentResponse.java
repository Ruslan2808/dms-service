package com.example.dmsservice.dto.document;

import com.example.dmsservice.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentResponse {

    private Long id;
    private String title;
    private LocalDate createdAt;
    private LocalDate validAt;
    private String promisor;
    private String promisee;
    private Status status;

}
