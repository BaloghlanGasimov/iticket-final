package com.example.iticketfinal.dao.entity;

import com.example.iticketfinal.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
    private String website;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageEntity logo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<EventEntity> events;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
