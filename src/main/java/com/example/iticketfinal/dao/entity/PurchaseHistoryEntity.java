package com.example.iticketfinal.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase_histories")
public class PurchaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    ......
}

