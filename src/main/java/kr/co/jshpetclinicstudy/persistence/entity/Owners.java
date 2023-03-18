package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Owners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owners_id", length = 4)
    private Long ownersId;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Builder
    public Owners(String firstName,
                  String lastName,
                  String address,
                  String city,
                  String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}
