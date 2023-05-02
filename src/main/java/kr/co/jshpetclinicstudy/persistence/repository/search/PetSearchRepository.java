package kr.co.jshpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.QPet;
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

    public List<Pet> find(PetRequestDto.CONDITION condition) {
        return queryFactory
                .selectFrom(pet)
                .where(
                        petIdIn(condition.getPetIds()),
                        petNameEq(condition.getName()),
                        petBirthDateEq(condition.getBirthDate())
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
        if (!StringUtils.hasText(birthDate.toString())) {
            return null;
        }

        return pet.birthDate.eq(birthDate);
    }


}
