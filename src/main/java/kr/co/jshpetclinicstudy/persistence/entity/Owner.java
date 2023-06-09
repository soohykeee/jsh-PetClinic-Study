package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "owner_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name="tbl_owners")
public class Owner extends BaseEntity {

    @Column(name = "first_name", length = 30)
    @NotNull
    private String firstName;

    @Column(name = "last_name", length = 30)
    @NotNull
    private String lastName;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "city", length = 80)
    @NotNull
    private String city;

    @Column(name = "telephone", length = 20, unique = true)
    @NotNull
    private String telephone;

    @Builder
    public Owner(String firstName,
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

    public void updateOwner(OwnerRequestDto.UPDATE update) {
        this.address = update.getAddress();
        this.city = update.getCity();
        this.firstName = update.getFirstName();
        this.lastName = update.getLastName();
        this.telephone = update.getTelephone();
    }
}
