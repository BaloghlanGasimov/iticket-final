package com.example.iticketfinal.dao.entity;

import com.example.iticketfinal.enums.OperationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPayment;
    private Integer ticketCount;
    private String errorMessage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private EventEntity event;
    @CreationTimestamp
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private OperationStatus status;

}

