package com.example.iticketfinal.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String logo;
    private String website;
    private String description;
    private boolean active;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    address aid olacaq restoranlara o da aid olacaq eventlere

    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EventEntity> events;

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
