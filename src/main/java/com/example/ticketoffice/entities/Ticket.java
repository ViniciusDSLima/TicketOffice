package com.example.ticketoffice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiver;

    private String name;

    private String email;

    private String offerSpecial;

    private int fixeDescountPercent;

    private String code;

    private String uniqueCode;

    private LocalDateTime validateDate;

    private LocalDateTime useDate;
}
