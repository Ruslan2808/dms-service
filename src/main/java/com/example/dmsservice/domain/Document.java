package com.example.dmsservice.domain;

import com.example.dmsservice.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "documents")
@SQLDelete(sql = "UPDATE documents SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Document {

    @Id
    @SequenceGenerator(
            name = "documents_id_seq",
            sequenceName = "documents_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "documents_id_seq")
    private Long id;
    private String title;
    private LocalDate createdAt;
    private LocalDate validAt;
    private String promisor;
    private String promisee;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean isDeleted;

}
