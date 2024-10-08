package com.example.iticketfinal.dto.payment;

import com.example.iticketfinal.dao.entity.EventEntity;
import com.example.iticketfinal.dao.entity.TicketEntity;
import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.enums.OperationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRespDto {
    private Double totalPayment;
    private Integer ticketCount;
    private LocalDateTime created;
    private OperationStatus status;
    private TicketEntity ticket;
    private EventEntity event;
}
