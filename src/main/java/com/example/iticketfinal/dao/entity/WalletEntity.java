package com.example.iticketfinal.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wallets")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double balance;
}
