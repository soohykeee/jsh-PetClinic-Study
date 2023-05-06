package kr.co.jshpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.jshpetclinicstudy.persistence.entity.QSpecialty;
import kr.co.jshpetclinicstudy.persistence.entity.QVet;
import kr.co.jshpetclinicstudy.persistence.entity.QVetSpecialty;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VetSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QVet vet = QVet.vet;

    private final QSpecialty specialty = QSpecialty.specialty;

    private final QVetSpecialty vetSpecialty = QVetSpecialty.vetSpecialty;

    public List<Vet> find(VetRequestDto.CONDITION condition) {
        return queryFactory
                .selectFrom(vet)
                .where(
                        vetIdIn(condition.getVetIds()),
                        vetFirstNameEq(condition.getFirstName()),
                        vetSpecialtyNameEq(condition.getSpecialtyName())
                )
                .fetch();
    }
/*
    public List<Vet> findBySpecialty(VetRequestDto.CONDITION condition) {
        return queryFactory
                .selectFrom(vet)
                .join(vet, vetSpecialty.vet).fetchJoin()
                .join(specialty, vetSpecialty.specialty).fetchJoin()
                .where(
                        vetSpecialtyNameEq(condition.getSpecialtyName())
                )
                .fetch();
    }*/

    private BooleanExpression vetIdIn(List<Long> vetIds) {
        if (CollectionUtils.isEmpty(vetIds)) {
            return null;
        }

        return vet.id.in(vetIds);
    }

    private BooleanExpression vetFirstNameEq(String firstName) {
        if (!StringUtils.hasText(firstName)) {
            return null;
        }

        return vet.firstName.eq(firstName);
    }

    private BooleanExpression vetSpecialtyNameEq(String specialtyName) {
        if (!StringUtils.hasText(specialtyName)) {
            return null;
        }

        return vet.vetSpecialties.any().specialty.specialtyName.eq(specialtyName);
    }

}
