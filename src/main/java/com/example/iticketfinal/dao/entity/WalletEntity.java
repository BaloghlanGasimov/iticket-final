package com.example.iticketfinal.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double balance=0.0;
}
