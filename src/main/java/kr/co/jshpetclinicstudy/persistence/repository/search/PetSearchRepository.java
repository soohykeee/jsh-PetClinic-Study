package kr.co.jshpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.QOwner;
import kr.co.jshpetclinicstudy.persistence.entity.QPet;
import kr.co.jshpetclinicstudy.persistence.entity.enums.Type;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PetSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QPet pet = QPet.pet;

    private final QOwner owner = QOwner.owner;

    public List<Pet> find(PetRequestDto.CONDITION condition) {
        return queryFactory
                .selectFrom(pet)
                .join(owner).fetchJoin()
                .where(
                        petIdIn(condition.getPetIds()),
                        petNameEq(condition.getName()),
                        petBirthDateEq(condition.getBirthDate()),
                        petTypeEq(condition.getType()),
                        petOwnerFirstNameEq(condition.getOwnerFirstName())
                )
                .fetch();
    }

    private BooleanExpression petIdIn(List<Long> petIds) {
        if (CollectionUtils.isEmpty(petIds)) {
            return null;
        }

        return pet.id.in(petIds);
    }

    private BooleanExpression petNameEq(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return pet.name.eq(name);
    }

    private BooleanExpression petBirthDateEq(LocalDate birthDate) {
        if (birthDate == null) {
            return null;
        }

        return pet.birthDate.eq(birthDate);
    }

    private BooleanExpression petTypeEq(String type) {
        if (!StringUtils.hasText(type)) {
            return null;
        }

        return pet.type.eq(Type.valueOf(type));
    }

    private BooleanExpression petOwnerFirstNameEq(String ownerFirstName) {
        if (!StringUtils.hasText(ownerFirstName)) {
            return null;
        }

        return pet.owner.firstName.eq(ownerFirstName);
    }

}
