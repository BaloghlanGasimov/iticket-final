package com.example.iticketfinal.dao.entity;

import com.example.iticketfinal.enums.EventCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventCategory category;
    private LocalDateTime eventDate;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private List<ImageEntity> images;

}
