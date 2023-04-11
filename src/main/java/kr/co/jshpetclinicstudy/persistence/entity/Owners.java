package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.jshpetclinicstudy.service.model.request.OwnersRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnersResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "owner_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name="tbl_owners")
public class Owners extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "telephone", length = 20, unique = true)
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


    public static Owners dtoToEntity(OwnersRequestDto.CREATE create){
        return Owners.builder()
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .address(create.getAddress())
                .city(create.getCity())
                .telephone(create.getTelephone())
                .build();
    }

    public static OwnersResponseDto.READ entityToDto(Owners owners){
        return OwnersResponseDto.READ.builder()
                .ownerId(owners.getId())
                .firstName(owners.getFirstName())
                .lastName(owners.getLastName())
                .address(owners.getAddress())
                .city(owners.getCity())
                .telephone(owners.getTelephone())
                .build();
    }

    public static OwnersResponseDto.DETAIL_READ entityToDetailDto(Owners owners){
        return OwnersResponseDto.DETAIL_READ.builder()
                .ownerId(owners.getId())
                .firstName(owners.getFirstName())
                .lastName(owners.getLastName())
                .address(owners.getAddress())
                .city(owners.getCity())
                .telephone(owners.getTelephone())
                .build();
    }

    public void changeOwnerAddress(String changeAddress) {
        this.address = changeAddress;
    }

    public void changeOwnerCity(String changeCity) {
        this.city = changeCity;
    }

    public void changeOwnerFirstName(String changeFirstName) {
        this.firstName = changeFirstName;
    }

    public void changeOwnerLastName(String changeLastName) {
        this.lastName = changeLastName;
    }

    public void changeOwnerTelephone(String changeOwnerTelephone) {
        this.telephone = changeOwnerTelephone;
    }
}
