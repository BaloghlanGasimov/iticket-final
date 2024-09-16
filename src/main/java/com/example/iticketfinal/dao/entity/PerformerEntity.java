package com.example.iticketfinal.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "performers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    private String username;
    @ManyToMany(mappedBy = "performers", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    public List<EventEntity> events;

    @PreUpdate
    public void preUpdate() {
        if (name != null && surname != null) {
            this.username = (name + "_" + surname).toLowerCase();
        }
    }
}
