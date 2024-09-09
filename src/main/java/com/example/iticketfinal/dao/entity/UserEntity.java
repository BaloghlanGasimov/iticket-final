package com.example.iticketfinal.dao.entity;

import com.example.iticketfinal.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<OrderEntity> orders;

    @OneToOne(cascade = CascadeType.ALL)
    private WalletEntity wallet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PurchaseHistoryEntity> purchaseHistory;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

}
