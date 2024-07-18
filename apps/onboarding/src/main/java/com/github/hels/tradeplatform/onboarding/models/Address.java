package com.github.hels.tradeplatform.onboarding.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address", schema = "onboarding")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name ="uf")
    private String uf;

    @Column(name ="city")
    private String city;

    @Column(name ="street_name")
    private String streetName;

    @Column(name ="district")
    private String district;

    @Column(name ="street_number")
    private String streetNumber;

    @Column(name ="complement")
    private String complement;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
