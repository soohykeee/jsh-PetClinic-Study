package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.jshpetclinicstudy.service.model.dtos.OwnersResponseDto;
import kr.co.jshpetclinicstudy.service.model.dtos.PetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.dtos.PetsResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "pet_id", length = 4))
@NoArgsConstructor
@Getter
@Table(name="tbl_pets")
public class Pets extends BaseEntity{

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owners owners;

    @Enumerated(EnumType.STRING)
    @Column(name="pet_type")
    private Types types;

    @Builder
    public Pets(String name,
                LocalDate birthDate,
                Owners owners,
                Types types) {
        this.name = name;
        this.birthDate = birthDate;
        this.owners = owners;
        this.types = types;
    }

    public static Pets dtoToEntity(PetsRequestDto.CREATE create) {
        return Pets.builder()
                .name(create.getName())
                .birthDate(create.getBirthDate())
                .owners(create.getOwners())
                .types(Types.valueOf(create.getType()))
                .build();
    }

    public static PetsResponseDto.READ entityToDto(Pets pets) {
        return PetsResponseDto.READ.builder()
                .petId(pets.getId())
                .name(pets.getName())
                .birthDate(pets.getBirthDate())
                .ownerTelephone(pets.getOwners().getTelephone())
                .ownerFirstName(pets.getOwners().getFirstName())
                .type(pets.getTypes().toString())
                .build();
    }

    public static PetsResponseDto.DETAIL_READ entityToDetailDto(Pets pets) {
        return PetsResponseDto.DETAIL_READ.builder()
                .petId(pets.getId())
                .name(pets.getName())
                .birthDate(pets.getBirthDate())
                .ownerTelephone(pets.getOwners().getTelephone())
                .ownerFirstName(pets.getOwners().getFirstName())
                .type(pets.getTypes().toString())
                .build();
    }

    public void changePetName(String changeName) {
        this.name = changeName;
    }

    public void changePetBirtDate(LocalDate changeBirthDate) {
        this.birthDate = changeBirthDate;
    }

    public void changePetType(Types changeTypes) {
        this.types = changeTypes;
    }

}
